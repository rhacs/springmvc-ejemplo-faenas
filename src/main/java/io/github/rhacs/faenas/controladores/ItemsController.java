package io.github.rhacs.faenas.controladores;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

        // Buscar todos los items almacenados en el repositorio, ordenados por la
        // cantidad del stock y la descripción
        List<Item> items = itemsRepositorio.findAll(Sort.by(Direction.DESC, "stock", "descripcion"));

        // Depuración
        logger.info("Listado de Items: {}", items);

        // Agregar listado al modelo
        modelo.addAttribute("items", items);

        // Devolver vista
        return "items";
    }

}
