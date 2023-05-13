# language: es
Característica: Probamos la navegacion de usuarios

  Escenario: Mostrar todos los usuarios
    Dado un usuario esta en la pagina de inicio
    Cuando el usuario hace click sobre el botón de Usuarios
    Entonces se muestran todos los usuarios del sistema

  Escenario: Mostrar crear usuario
    Dado un usuario esta en la pagina Usuarios
    Cuando el usuario hace click sobre el boton Crear Usuarios
    Entonces se muestra un formulario para crear el usuario