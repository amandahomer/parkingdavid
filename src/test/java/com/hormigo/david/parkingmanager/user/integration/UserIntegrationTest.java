package com.hormigo.david.parkingmanager.user.integration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.hormigo.david.parkingmanager.user.service.UserService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIntegrationTest {
    @MockBean
    private UserService userService;
    @Value("${local.server.port}")
    private  int port;
    private static ChromeDriver chromeDriver;
    
    @BeforeAll
    public static void prepareWebDriver() {
        System.setProperty("webdriver.chrome.driver","C:\\ChromeDriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        //Instancio chromeDriver
        chromeDriver = new ChromeDriver(options);
    }
    
    /*Test que comprueba que la pagina localhost:+puerto es la pagina home que queremos
    indicando que que el titulo de la pestaña es "CPIFP Los Camaleones" y tres botones*/
    @Test
    public void testIndex(){
        chromeDriver.get("http://localhost:"+ port +"/");
        String title = chromeDriver.getTitle();
        assertEquals("CPIFP Los Camaleones", title);
        WebElement button1 = chromeDriver.findElement(By.id("to-home-link"));
        button1.click();
        WebElement button2 = chromeDriver.findElement(By.id("to-users-link"));
        button2.click();
        WebElement button3 = chromeDriver.findElement(By.id("to-draws-link"));
        button3.click();
    }

    /*Test que comprueba que la pag locaalhost:+puerto+/users es la pagina
    que queremos indicando que que el titulo de la pestaña es "Usuarios" y un boton y una tabla*/
    @Test
    public void testUsers(){
        chromeDriver.get("http://localhost:"+ port +"/users");
        String title = chromeDriver.getTitle();
        assertEquals("Usuarios", title);
        WebElement table = chromeDriver.findElement(By.className("table"));
        WebElement button = chromeDriver.findElement(By.id("users-button-create"));
        //button.click();
    }

    /*Test que comprueba que la pag locaalhost:+puerto+/newUser es la pagina
    que queremos indicando que el titulo de la pestaña es "Crear nuevosuarios" y 
    un formulario y un boton submit*/
    @Test
    public void testNewUser(){
        chromeDriver.get("http://localhost:"+ port +"/newUser");
        String title = chromeDriver.getTitle();
        assertEquals("Crear nuevo usuario", title);
        WebElement form = chromeDriver.findElement(By.className("form"));
        WebElement buttonSubmit = chromeDriver.findElement(By.id("user-create-button-submit"));
        //buttonSubmit.click();
    }

    //Mismo test que testUsers para Sorteos
    @Test
    public void testDraws(){
        chromeDriver.get("http://localhost:"+ port +"/draws");
        String title = chromeDriver.getTitle();
        assertEquals("Sorteos", title);
        WebElement table = chromeDriver.findElement(By.className("table"));
        WebElement button = chromeDriver.findElement(By.id("create-draw-button"));
        //button.click();
    }

    //Mismo test que testNewUser para Crear sorteos
    @Test
    public void testNewDraw(){
        chromeDriver.get("http://localhost:"+ port +"/newDraw");
        String title = chromeDriver.getTitle();
        assertEquals("Crear nuevo sorteo", title);
        WebElement form = chromeDriver.findElement(By.className("form"));
        WebElement buttonSubmit = chromeDriver.findElement(By.id("draw-button-submit"));
        //buttonSubmit.click();
    }

    /*Test para verificar que desde la pagina principal hacemos click
    en el boton usuarios y muestra la pagina usuarios.*/
    @Test
    public void checkUsersOnHomeClick(){
        String urlHome = "http://localhost:"+ port +"/";
        chromeDriver.get(urlHome);
        chromeDriver.findElement(By.id("to-users-link")).click();
        String title = chromeDriver.getTitle();
        WebElement button = chromeDriver.findElement(By.id("users-button-create"));

        assertAll("Verificar que se muestra la pagina de usuarios",
            () -> {assertEquals("Usuarios", title);},
            () -> {assertNotNull(button);}
        );
        chromeDriver.quit();
    }

    /*Test para verificar que desde la pagina usuarios hacemos click
    en el boton crear usuario y muestra la pagina crear usuario.*/
    @Test
    public void checkCreateUserOnUsersClick(){
        String urlUsers = "http://localhost:"+ port +"/users";
        chromeDriver.get(urlUsers);
        chromeDriver.findElement(By.id("users-button-create")).click();
        String title = chromeDriver.getTitle();
        WebElement buttonSubmit = chromeDriver.findElement(By.id("user-create-button-submit"));

        assertAll("Verificar que se muestra la pagina de crear usuario",
            () -> {assertEquals("Crear nuevo usuario", title);},
            () -> {assertNotNull(buttonSubmit);}
        );
        chromeDriver.quit();
    }

    /*Test para verificar que desde la pagina principal hacemos click
    en el boton sorteos y muestra la pagina sorteos.*/
    @Test
    public void checkDrawsOnHomeClick(){
        String urlHome = "http://localhost:"+ port +"/";
        chromeDriver.get(urlHome);
        chromeDriver.findElement(By.id("to-draws-link")).click();
        String title = chromeDriver.getTitle();
        WebElement button = chromeDriver.findElement(By.id("create-draw-button"));

        assertAll("Verificar que se muestra la pagina de sorteos",
            () -> {assertEquals("Sorteos", title);},
            () -> {assertNotNull(button);}
        );
        chromeDriver.quit();
    }

    /*Test para verificar que desde la pagina usuarios hacemos click
    en el boton crear sorteo y muestra la pagina crear sorteo.*/
    @Test
    public void checkCreateDrawOnDrawsClick(){
        String urlDraws = "http://localhost:"+ port +"/draws";
        chromeDriver.get(urlDraws);
        chromeDriver.findElement(By.id("create-draw-button")).click();
        String title = chromeDriver.getTitle();
        WebElement buttonSubmit = chromeDriver.findElement(By.id("draw-button-submit"));

        assertAll("Verificar que se muestra la pagina de crear sorteo",
            () -> {assertEquals("Crear nuevo sorteo", title);},
            () -> {assertNotNull(buttonSubmit);}
        );
        chromeDriver.quit();
    }


}
