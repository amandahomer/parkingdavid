# language: es
Característica: Gestion de usuarios
  
  Escenario: Se muestra la pagina Usuarios
    Dado un usuario esta en la pagina de Usuarios
    Entonces tengo un titulo usuarios
    Y tengo una tabla de los usuarios
    Y tengo un boton Crear Usuario

  Escenario: Se muestra la pagina Crear Usuario
    Dado un usuario esta en la pagina Crear Usuario
    Entonces tengo un formulario para crear usuario
    Y tengo un boton submit para crear el usuario