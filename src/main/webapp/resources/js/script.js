// Esperar a que termine de cargar la página
$(function() {
    // Asignar a todos los elementos marcados con data-action el evento click
    $("[data-action]").on('click', function(event) {
        // Prefenir acción por defecto
        event.preventDefault();

        // Obtener valores
        let accion = $(this).data('action');
        let miembro = $(this).data('member');
        let id = $(this).data('id');

        // Filtrar acción
        if (accion === 'add') {
            // Redireccionar
            $(location).attr('href', '/faenas/' + miembro + '/add');
        } else if (accion === 'cancel') {
            // Volver atrás
            history.back();
        } else if (accion === 'edit') {
            // Redireccionar
            $(location).attr('href', '/faenas/' + miembro + '/' + id + '/edit');
        }
    });
});
