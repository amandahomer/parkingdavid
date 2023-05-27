package com.hormigo.david.parkingmanager.user.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hormigo.david.parkingmanager.core.exceptions.UserExistsException;
import com.hormigo.david.parkingmanager.user.domain.Role;
import com.hormigo.david.parkingmanager.user.domain.User;
import com.hormigo.david.parkingmanager.user.domain.UserDao;
import com.hormigo.david.parkingmanager.user.service.UserService;
import com.hormigo.david.parkingmanager.user.service.UserServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @Test
    public void testPositive() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UserDao dao = new UserDao("da@correo.es", "David", "Hormigo", "Ramírez", Role.PROFESSOR);
        String json = mapper.writeValueAsString(dao);
        when(this.userService.register(any(UserDao.class))).thenReturn(new User("da@correo.es","David","Hormigo","Ramírez",Role.PROFESSOR));
        this.mockMvc.perform(post("/api/users")
                    .contentType("application/json").content(json))
                    .andDo(print())
                    .andExpect(status().isCreated());
    }

    @Test
    public void testSingleUserRead() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        User user = new User("dhorram948@g.educaand.es","David","Hormigo","Ramírez",Role.PROFESSOR);
        ArrayList<User> usuariso = new ArrayList<>();
        usuariso.add(user);
        String json = mapper.writeValueAsString(usuariso);
        json = "{ \"_embedded\": {\"userList\":" + json + "}}";
        when(userService.getAll()).thenReturn(usuariso);
        this.mockMvc.perform(get("/api/users"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(json));
                    

    }

    @Test
    public void testLeerUsuarios() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<User> listaUsusarios = new ArrayList<>();
        listaUsusarios.add(new User("amanda@lamejor.es","Amanda","Navas","Rodriguez",Role.PROFESSOR));
        listaUsusarios.add(new User("homer@jsimpson.es","Homer","J","Simpson",Role.STUDENT));
        String json = mapper.writeValueAsString(listaUsusarios);
        json = "{ \"_embedded\": {\"userList\":" + json + "}}";
        when(this.userService.getAll()).thenReturn(listaUsusarios);
        this.mockMvc.perform(get("/api/users"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(json));
    }

    @Test
    public void borrarUsuario() throws Exception{
        User usuario = new User("amanda@lamejor.es","Amanda","Navas","Rodriguez",Role.PROFESSOR);
        when(userService.getUser(3)).thenReturn(Optional.of(usuario));
        this.mockMvc.perform(delete("/api/users/3"))
                    .andDo(print())
                    .andExpect(status().is(204));
    }

    @Test
    public void crearUsuarioEmailVacio() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        User usuario = new User("","Amanda","Navas","Rodriguez",Role.PROFESSOR);        
        String json = mapper.writeValueAsString(usuario);
        when(userService.register(any(UserDao.class))).thenReturn(null);
        this.mockMvc.perform(post("/api/users")
                    .contentType("application/json").content(json))
                    .andDo(print())
                    .andExpect(status().is(422))
                    .andExpect(content().string("El correo es obligatorio\n"));
    }

    @Test
    public void crearUsuarioEmailRepetido() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        User usuario = new User("amanda@lamejor.es","Amanda","Navas","Rodriguez",Role.PROFESSOR);        
        String json = mapper.writeValueAsString(usuario);
        //No se como poner que devuelva que ya existe un usuario con ese correo
        when(userService.register(any(UserDao.class))).thenThrow(UserExistsException.class);
        this.mockMvc.perform(post("/api/users")
                    .contentType("application/json").content(json))
                    .andDo(print())
                    .andExpect(status().is(406))
                    .andExpect(content().string("Ya existe un usuario con el correo"));
    }               

    @Test
    public void crearUsuarioNombreVacio() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        User usuario = new User("amanda@lamejor.es","","Navas","Rodriguez",Role.PROFESSOR);        
        String json = mapper.writeValueAsString(usuario);
        when(this.userService.register(any(UserDao.class))).thenReturn(null);
        this.mockMvc.perform(post("/api/users")
                    .contentType("application/json").content(json))
                    .andDo(print())
                    .andExpect(status().is(422))
                    .andExpect(content().string("El nombre es obligatorio\n"));
    }

    @Test
    public void crearUsuario1ApellidoVacio() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        User usuario = new User("amanda@lamejor.es","Amanda","","Rodriguez",Role.PROFESSOR);        
        String json = mapper.writeValueAsString(usuario);
        when(this.userService.register(any(UserDao.class))).thenReturn(null);
        this.mockMvc.perform(post("/api/users")
                    .contentType("application/json").content(json))
                    .andDo(print())
                    .andExpect(status().is(422))
                    .andExpect(content().string("El primer apellido es obligatorio\n"));
    }

    @Test
    public void modificarUsuario() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        User usuario = new User("amanda@lamejor.es","Amanda","Navas","Rodriguez",Role.PROFESSOR);        
        Map<String, Object> usuarioModificado = new HashMap<>();
        usuarioModificado.put("email", "homer@jsimpson.es");
        usuario.setEmail("homer@jsimpson.es");
        String json = mapper.writeValueAsString(usuario);
        String jsonModificado = mapper.writeValueAsString(usuarioModificado);
        when(userService.updateUser(3, usuarioModificado) ).thenReturn(usuario);
        this.mockMvc.perform(post("/api/users")
                    .contentType("application/json").content(jsonModificado))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(json));
    }

}
