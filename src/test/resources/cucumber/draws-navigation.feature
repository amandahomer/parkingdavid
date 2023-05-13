# language: es
Característica: Probamos la navegacion de sorteos

  Escenario: Mostrar todos los sorteos
    Dado un usuario esta en la pagina de inicio nuevamente
    Cuando el usuario hace click sobre el boton de Sorteos
    Entonces se muestran todos los sorteos

  Escenario: Mostrar crear sorteos
    Dado un usuario esta en la pagina Sorteos
    Cuando el usuario hace click sobre el boton Crear Sorteo
    Entonces se muestra un formulario para crear el sorteo