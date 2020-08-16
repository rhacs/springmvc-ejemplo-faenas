package io.github.rhacs.faenas.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet-context.xml",
        "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@WebAppConfiguration
class ItemsRestControllerTest {

    private MockMvc mvc;
    
    @BeforeEach
    void setUp(WebApplicationContext wac) throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void obtenerListadoShouldReturnAList() throws Exception {
        mvc
            // Emular una solicitud get a la API
            .perform(get("/api/items"))
            // Esperar que el estado de la respuesta sea HttpStatus.OK
            .andExpect(status().isOk())
            // Esperar que el tipo de contenido de la respuesta sea application/json
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            // Esperar que el json tenga un objeto en la primera posición con el atributo
            // "descripcion" y el valor "Enhanced coherent strategy"
            .andExpect(jsonPath("$[0].descripcion").value("Enhanced coherent strategy"));
    }

    @Test
    void obtenerItemShouldReturnAnItem() throws Exception {
        mvc
            // Emular una solicitud get a la API
            .perform(get("/api/items/{id}", 10))
            // Esperar que el estado de la respuesta sea HttpStatus.OK
            .andExpect(status().isOk())
            // Esperar que el tipo de contenido de la respuesta sea application/json
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            // Esperar que el objeto retornado tenga un atributo "id"
            .andExpect(jsonPath("$.id").exists())
            // Esperar que el objeto retornado tenga un atributo "proveedor", que a la vez
            // contiene un atributo "nombre" con el valor "Huel, Upton and Simonis"
            .andExpect(jsonPath("$.proveedor.nombre").value("Huel, Upton and Simonis"));
    }

}
