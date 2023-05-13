package com.hormigo.david.parkingmanager.bdd.steps;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.hormigo.david.parkingmanager.bdd.CucumberConfiguration;
import com.hormigo.david.parkingmanager.user.service.UserService;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
public class CucumberSteps extends CucumberConfiguration {

    @MockBean
    private UserService userService;
    @Value("${local.server.port}")
    private  int port;
    private static ChromeDriver driver;
    @BeforeAll
    public static void prepareWebDriver() {

        System.setProperty("webdriver.chrome.driver","C:\\ChromeDriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        
    }


//Escenario 1
    @Given("un usuario esta en la pagina inicial")
    public void home() {
        driver.get("http://localhost:" + port + "/");
    }

    @Then("tengo un titulo")
    public void title(){
        String title = driver.getTitle();
        assertEquals("CPIFP Los Camaleones",title);
    }

    @Then("tengo un boton Usuarios")
    public void usersButton(){
        WebElement button = driver.findElement(By.id("to-users-link"));
        assertNotNull(button);
    }

    @Then("tengo un boton Sorteos")
    public void drawsButton(){
        WebElement button = driver.findElement(By.id("to-draws-link"));
        assertNotNull(button);
    }

//Escenario 2
    @Given("un usuario esta en la pagina de Usuarios")
    public void users() {
        driver.get("http://localhost:" + port + "/users");
    }

    @Then("tengo un titulo usuarios")
    public void titleUsers(){
        String title = driver.getTitle();
        assertEquals("Usuarios",title);
    }

    @Then("tengo una tabla de los usuarios")
    public void userTable(){
        WebElement table = driver.findElement(By.id("table-create-user"));
        assertNotNull(table);
    }

    @Then("tengo un boton Crear Usuario")
    public void createUserButton(){
        WebElement button = driver.findElement(By.id("users-button-create"));
        assertNotNull(button);
    }

//Escenario 3
    @Given("un usuario esta en la pagina Crear Usuario")
    public void createUser() {
        driver.get("http://localhost:" + port + "/newUser");
    }

    @Then("tengo un formulario para crear usuario")
    public void userForm(){
        WebElement form = driver.findElement(By.id("create-user-form"));
        assertNotNull(form);
    }

    @Then("tengo un boton submit para crear el usuario")
    public void submitButton(){
        WebElement button = driver.findElement(By.id("user-create-button-submit"));
        assertNotNull(button);
    }

//Escenario 4
    @Given("un usuario esta en la pagina de Sorteos")
    public void draws() {
        driver.get("http://localhost:" + port + "/draws");
    }

    @Then("tengo un titulo Sorteos")
    public void titleDraws(){
        String title = driver.getTitle();
        assertEquals("Sorteos",title);
    }

    @Then("tengo una tabla de los sorteos")
    public void DrawTable(){
        WebElement table = driver.findElement(By.id("draw-list-table"));
        assertNotNull(table);
    }

    @Then("tengo un boton Crear Sorteo")
    public void createDrawButton(){
        WebElement button = driver.findElement(By.id("draws-button-create"));
        assertNotNull(button);
    }

//Escenario 5
    @Given("un usuario esta en la pagina Crear Sorteo")
    public void createDraw() {
        driver.get("http://localhost:" + port + "/newDraw");
    }

    @Then("tengo un formulario para crear un sorteo")
    public void drawForm(){
        WebElement form = driver.findElement(By.id("create-draw-form"));
        assertNotNull(form);
    }

    @Then("tengo un boton submit para crear el sorteo")
    public void submitButton2(){
        WebElement button = driver.findElement(By.id("draw-button-submit"));
        assertNotNull(button);
    }

//Test navegacion
//Escenario 6
    @Given("un usuario esta en la pagina de inicio")
    public void openHome() {
        driver.get("http://localhost:" + port + "/");
    }

    @When("el usuario hace click sobre el botón de Usuarios")
    public void clickUserButton(){
        driver.findElement(By.id("to-users-link")).click();
    }

    @Then("se muestran todos los usuarios del sistema")
    public void navigateToUsersList(){
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/users"));
    }

//Escenario 7
    @Given("un usuario esta en la pagina Usuarios")
    public void openUser() {
        driver.get("http://localhost:" + port + "/users");
    }

    @When("el usuario hace click sobre el boton Crear Usuarios")
    public void clickCreateUserButton(){
        driver.findElement(By.id("users-button-create")).click();
    }

    @Then("se muestra un formulario para crear el usuario")
    public void navigateToCreateUserForm(){
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/newUser"));
    }

//Escenario 8
    @Given("un usuario esta en la pagina de inicio nuevamente")
    public void openHome2() {
        driver.get("http://localhost:" + port + "/");
    }

    @When("el usuario hace click sobre el boton de Sorteos")
    public void clickDrawsButton(){
        driver.findElement(By.id("to-draws-link")).click();
    }

    @Then("se muestran todos los sorteos")
    public void navigateToDrawsList(){
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/draws"));
    }

//Escenario 9
    @Given("un usuario esta en la pagina Sorteos")
    public void openDraw() {
        driver.get("http://localhost:" + port + "/draws");
    }

    @When("el usuario hace click sobre el boton Crear Sorteo")
    public void clickCreateDrawButton(){
        driver.findElement(By.id("draws-button-create")).click();
    }

    @Then("se muestra un formulario para crear el sorteo")
    public void navigateToCreateDrawForm(){
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/newDraw"));
    }
}
