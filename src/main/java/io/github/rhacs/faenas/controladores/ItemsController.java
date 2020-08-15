package io.github.rhacs.faenas.controladores;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.rhacs.faenas.modelos.Item;
import io.github.rhacs.faenas.modelos.Proveedor;
import io.github.rhacs.faenas.repositorios.ItemsRepositorio;
import io.github.rhacs.faenas.repositorios.ProveedoresRepositorio;

@Controller
@RequestMapping(path = "/items")
public class ItemsController {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /**
     * Objeto {@link Logger} que contiene los métodos de depuración
     */
    private static final Logger logger = LogManager.getLogger(ItemsController.class);

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Objeto {@link ItemsRepositorio} que contiene los métodos de manipulación para
     * la tabla de {@link Item}s
     */
    @Autowired
    private ItemsRepositorio itemsRepositorio;

    /**
     * Objeto {@link ProveedoresRepositorio} que contiene los métodos de
     * manipulación para la tabla de {@link Proveedor}es
     */
    @Autowired
    private ProveedoresRepositorio proveedoresRepositorio;

    // Solicitudes GET
    // -----------------------------------------------------------------------------------------

    /**
     * Muestra el listado de {@link Item}s almacenados en el repositorio
     *
     * @param modelo objeto {@link Model} que contiene el modelo de la vista
     * @return un objeto {@link String} con la respuesta a la solicitud
     */
    @GetMapping
    public String verListado(Model modelo) {
        // Depuración
        logger.info("Solicitud GET: /items");

        // Buscar todos los items almacenados en el repositorio, ordenados por el precio
        // unitario de forma descendente
        List<Item> items = itemsRepositorio.findAll(Sort.by(Direction.DESC, "precioUnitario"));

        // Depuración
        logger.info("Listado de Items: {}", items);

        // Agregar listado al modelo
        modelo.addAttribute("items", items);

        // Devolver vista
        return "items";
    }

    /**
     * Muestra el formulario para agregar o editar un registro
     *
     * @param itemid  identificador numérico del {@link Item}
     * @param request objeto {@link HttpServletRequest} que contiene la información
     *                de la solicitud que le envía el cliente al servlet
     * @param modelo  objeto {@link Model} que contiene el modelo de la vista
     * @return un objeto {@link String} con la respuesta a la solicitud
     */
    @GetMapping(path = { "/add", "/{itemid:^[0-9]+$}/edit" })
    public String verFormulario(@PathVariable Optional<Long> itemid, HttpServletRequest request, Model modelo) {
        logger.info("Solicitud GET: {}", request.getRequestURI());

        // Inicializar un nuevo item
        Item item = new Item();

        // Verificar si el itemid está presente en la URI
        if (itemid.isPresent()) {
            logger.info("Buscando información del Item con el id: {}", itemid.get());

            // Buscar información del Item
            Optional<Item> itemExistente = itemsRepositorio.findById(itemid.get());

            // Verificar si el item no existe
            if (!itemExistente.isPresent()) {
                logger.info("Item con el id {} no existe", itemid.get());

                // Redireccionar
                return "redirect:/items?wrongid=" + itemid.get();
            }

            // Reemplazar el item
            item = itemExistente.get();
        }

        logger.info("Mostrando el formulario para el Item: {}", item);

        // Buscar todos los proveedores
        List<Proveedor> proveedores = proveedoresRepositorio.findAll(Sort.by("nombre"));

        // Agregar objetos al modelo
        modelo.addAttribute("item", item);
        modelo.addAttribute("proveedores", proveedores);

        // Devolver vista
        return "items.form";
    }

    // Solicitudes POST
    // -----------------------------------------------------------------------------------------

    /**
     * Procesa el formulario para agregar/editar un registro
     * 
     * @param itemid        identificador numérico del {@link Item}
     * @param item          objeto {@link Item} con la información a agregar/editar
     * @param request       objeto {@link HttpServletRequest} que contiene la
     *                      información de la solicitud que le envía el cliente al
     *                      servlet
     * @param bindingResult objeto {@link BindingResult} que contiene los errores de
     *                      validación
     * @param modelo        objeto {@link Model} que contiene el modelo de la vista
     * @return un objeto {@link String} con la respuesta a la solicitud
     */
    @PostMapping(path = { "/add", "/{itemid:^[0-9]+$/edit" })
    public String procesarFormulario(@PathVariable Optional<Long> itemid, @Valid Item item, HttpServletRequest request,
            BindingResult bindingResult, Model modelo) {
        logger.info("Solicitud POST: {}", request.getRequestURI());

        // Verificar si hay errores en el formulario
        if (bindingResult.hasErrors()) {
            logger.info("El formulario contiene errores: {}", bindingResult.getAllErrors());

            // Buscar listado de proveedores
            List<Proveedor> proveedores = proveedoresRepositorio.findAll(Sort.by("nombre"));

            // Agregar proveedores al modelo
            modelo.addAttribute("proveedores", proveedores);

            // Mostrar vista
            return "items.form";
        }

        // Guardar cambios/agregar registro
        item = itemsRepositorio.save(item);

        logger.info("Se {} el registro Item: {}", itemid.isPresent() ? "editó" : "agregó", item);

        // Redireccionar al listado
        return "redirect:/items?action=" + (itemid.isPresent() ? "edit" : "add");
    }

    /**
     * Elimina un registro del repositorio, si existe
     * 
     * @param id      identificador numérico del {@link Item}
     * @param request objeto {@link HttpServletRequest} que contiene la información
     *                de la solicitud que envía el cliente al servlet
     * @return un objeto {@link String} con la respuesta a la solicitud
     */
    @PostMapping(path = "/{id:^[0-9]$}/del")
    public String eliminarRegistro(@PathVariable Long id, HttpServletRequest request) {
        logger.info("Solicitud POST: {}", request.getRequestURI());

        // Obtener información del registro
        Optional<Item> item = itemsRepositorio.findById(id);

        logger.info("Buscando información del Item {}", id);

        // Verificar si existe
        if (item.isPresent()) {
            // Eliminar el registro
            itemsRepositorio.delete(item.get());

            logger.info("Registro eliminado: {}", item.get());

            // Redireccionar
            return "redirect:/items?remid=" + id;
        }

        logger.info("No se encontró el Item con el identificador {}", id);

        // Redireccionar
        return "redirect:/items?wrongid=" + id;
    }

}
