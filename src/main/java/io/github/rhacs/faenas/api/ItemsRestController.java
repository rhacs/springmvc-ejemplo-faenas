package io.github.rhacs.faenas.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Item> obtenerListado(HttpServletRequest request) {
        logger.trace("Solicitud GET: {}", request.getRequestURI());
        logger.trace("Obteniendo el listado de items");

        // Obtener listado de items
        List<Item> items = itemsRepositorio.findAll();

        logger.trace("Listado de Items: {}", items);

        // Devolver listado
        return items;
    }

}
