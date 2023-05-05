package com.hormigo.david.parkingmanager.user.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        WebElement button = chromeDriver.findElement(By.id("users-button-create"));
        button.click();
        WebElement table = chromeDriver.findElement(By.className("table"));
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
        buttonSubmit.click();
    }

    //Mismo test que testUsers para Sorteos
    @Test
    public void testDraws(){
        chromeDriver.get("http://localhost:"+ port +"/draws");
        String title = chromeDriver.getTitle();
        assertEquals("Sorteos", title);
        WebElement button = chromeDriver.findElement(By.className("button is-primary"));
        button.click();
        WebElement table = chromeDriver.findElement(By.id("draw-list-table"));
    }

    //Mismo test que testNewUser para Crear sorteos
    @Test
    public void testNewDraw(){
        chromeDriver.get("http://localhost:"+ port +"/newDraw");
        String title = chromeDriver.getTitle();
        assertEquals("Crear nuevo sorteo", title);
        WebElement form = chromeDriver.findElement(By.className("form"));
        WebElement buttonSubmit = chromeDriver.findElement(By.id("draw-button-submit"));
        buttonSubmit.click();
    }

    
}
