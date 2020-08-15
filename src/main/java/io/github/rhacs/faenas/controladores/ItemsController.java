package io.github.rhacs.faenas.controladores;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.rhacs.faenas.modelos.Item;
import io.github.rhacs.faenas.repositorios.ItemsRepositorio;

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
     * @param itemid identificador numérico del {@link Item}
     * @param modelo objeto {@link Model} que contiene el modelo de la vista
     * @return un objeto {@link String} con la respuesta a la solicitud
     */
    @GetMapping(path = { "/add", "/{itemid:^[0-9]+$}/edit" })
    public String verFormulario(@PathVariable Optional<Long> itemid, Model modelo) {
        // Inicializar un nuevo item
        Item item = new Item();

        // Verificar si el itemid está presente en la URI
        if (itemid.isPresent()) {
            // Buscar información del Item
            Optional<Item> itemExistente = itemsRepositorio.findById(itemid.get());

            // Verificar si el item no existe
            if (!itemExistente.isPresent()) {
                // Redireccionar
                return "redirect:/items?wrongid=" + itemid.get();
            }

            // Reemplazar el item
            item = itemExistente.get();
        }

        // Agregar item al modelo
        modelo.addAttribute("item", item);

        // Devolver vista
        return "items.form";
    }

}
