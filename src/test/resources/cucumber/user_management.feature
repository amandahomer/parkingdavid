# language: es
Característica: Gestion de usuarios
  
  Escenario: Navegación a la lista de usuarios
    Dado un usuario esta en la pagina inicial
    Cuando el usuario hace click sobre el botón de Usuarios
    Entonces esta en la pagina de lista de usuarios

  
  Escenario: Comprobar que el formulario de creación de usuarios tiene todos los elementos
    Dado un usuario esta en la pagina creación de usuarios
    Entonces se muestra un campo de correo electrónico
    Y se muestra un campo de nombre
    Y se muestra un campo de primer apellido
    Y se muestra un campo de segundo apellido

  Escenario: Crear un usuario correctamente
    Dado un usuario esta en la pagina creación de usuarios
    Y el correo usuario@correo.com no esta asignado a otro usuario
    Cuando relleno el campo correo electrónico con usuario@correo.com
    Y relleno el campo nombre con David
    Y relleno el campo primer apellido con Hormigo
    Y el usuario hace click sobre el botón de crear usuario
    Entonces esta en la pagina de lista de usuarios
    Y si se ha persistido el usuario en la base de datos

  #Casos negativos crear usuario

  Escenario: El email ya existe
    Dado un usuario esta en la pagina creación de usuarios
    Y el correo usuario1@correo.com si esta asignado a otro usuario
    Cuando relleno el campo correo electrónico con usuario@correo.com
    Y relleno el campo nombre con Amanda
    Y relleno el campo primer apellido con Navas
    Y el usuario hace click sobre el botón de crear usuario
    Entonces no se ha persistido el usuario en la base de datos
    Y no se ha navegado

  Escenario: El campo email esta vacio
    Dado un usuario esta en la pagina creación de usuarios
    Cuando dejo en blanco el campo correo electrónico
    Y relleno el campo nombre con Amanda
    Y relleno el campo primer apellido con Navas
    Y el usuario hace click sobre el botón de crear usuario
    Entonces no se ha persistido el usuario en la base de datos
    Y se muestra un mensaje de error para correo
    Y no se ha navegado

  Escenario: El campo nombre esta vacio
    Dado un usuario esta en la pagina creación de usuarios
    Cuando relleno el campo correo electrónico con usuario@correo.com
    Y dejo en blanco el campo nombre
    Y relleno el campo primer apellido con Navas
    Y el usuario hace click sobre el botón de crear usuario
    Entonces no se ha persistido el usuario en la base de datos
    Y se muestra un mensaje de error para nombre
    Y no se ha navegado

  Escenario: El campo primer apellido esta vacio
    Dado un usuario esta en la pagina creación de usuarios
    Cuando relleno el campo correo electrónico con usuario@correo.com
    Y relleno el campo nombre con Amanda
    Y dejo en blanco el campo primer apellido
    Y el usuario hace click sobre el botón de crear usuario
    Entonces no se ha persistido el usuario en la base de datos
    Y se muestra un mensaje de error para primer apellido
    Y no se ha navegado
