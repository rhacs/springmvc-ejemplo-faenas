package io.github.rhacs.faenas.api;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.rhacs.faenas.excepciones.ElementNotFoundException;
import io.github.rhacs.faenas.modelos.ErrorResponse;
import io.github.rhacs.faenas.modelos.Item;
import io.github.rhacs.faenas.repositorios.ItemsRepositorio;

@RestController
@RequestMapping(path = "/api/items", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemsRestController {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /**
     * Objeto {@link Logger} que contiene los métodos de depuración
     */
    private static final Logger logger = LogManager.getLogger(ItemsRestController.class);

    // Atributos
    // -----------------------------------------------------------------------------------------

    @Autowired
    private ItemsRepositorio itemsRepositorio;

    // Solicitudes GET
    // -----------------------------------------------------------------------------------------

    /**
     * Muestra el listado de {@link Item}s que se encuentra en el repositorio
     * 
     * @param request objeto {@link HttpServletRequest} que contiene la información
     *                de la solicitud que le hace el cliente al servlet
     * @return un objeto {@link List} con la respuesta
     */
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<Item> obtenerListado(HttpServletRequest request) {
        logger.info("[API] Solicitud POST: {}", request.getRequestURI());

        // Obtener listado de items
        List<Item> items = itemsRepositorio.findAll();

        logger.info("[API] Listado de Items: {}", items);

        // Devolver listado
        return items;
    }

    /**
     * Muestra la información del {@link Item} solicitado por URI
     * 
     * @param id      identificador numérico del {@link Item}
     * @param request objeto {@link HttpServletRequest} que contiene la información
     *                de la solicitud que le hace el cliente al servlet
     * @return un objeto {@link Item} con la respuesta
     */
    @GetMapping(path = "/{id:^[0-9]+$}")
    @ResponseStatus(code = HttpStatus.OK)
    public Item obtenerItem(@PathVariable Long id, HttpServletRequest request) {
        logger.info("[API] Solicitud POST: {}", request.getRequestURI());

        // Obtener información del Item solicitado, lanzar excepción
        Optional<Item> item = itemsRepositorio.findById(id);

        // Verificar si existe
        if (item.isPresent()) {
            logger.info("[API] Mostrando información del Item solicitado: {}", item.get());

            // Devolver objeto
            return item.get();
        }

        logger.info("[API] Objeto no encontrado para el identificador {}", id);

        // Crear respuesta de error
        ErrorResponse response = new ErrorResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage("El objeto con el identificador " + id + " no existe");
        response.addError("id", "El objeto no existe", id);

        // Lanzar excepción
        throw new ElementNotFoundException(response);
    }

}
