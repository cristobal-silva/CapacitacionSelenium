package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.DemoTsPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class DemoTsTest {
    private WebDriver driver;
    private static DemoTsPage demoTsPage;
    private Connection connection;
    private Properties properties;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        demoTsPage = new DemoTsPage(driver);

        // Cargamos propiedades desde el archivo db.properties
        properties = new Properties();
        try (FileInputStream input = new FileInputStream("src/main/resources/db.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar el archivo de propiedades");
        }

        // Establecemos conexión con la base de datos utilizando las propiedades cargadas
        try {
            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al conectar con la base de datos");
        }
    }

    @Test
    @Parameters({"texto"})
    public void abrirGoogleTest(String texto) throws InterruptedException {
        demoTsPage.abrirGoogle();
        Assert.assertTrue(driver.getTitle().contains("Google"));

        // Realizar un SELECT en la base de datos después de abrir Google
        selectBaseDatos();
        demoTsPage.rellenarInputGoogle(texto);
        Thread.sleep(20000);
    }

    public void selectBaseDatos() {
        // Consulta SQL para seleccionar datos de la tabla cliente (puedes personalizarla)
        String query = "SELECT * FROM cliente LIMIT 5"; // Ejemplo de SELECT a la tabla cliente
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                // Obtener los valores de las columnas (por ejemplo, id_cliente, nombre, apellido)
                int idCliente = rs.getInt("id_cliente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                System.out.println("ID: " + idCliente + ", Nombre: " + nombre + ", Apellido: " + apellido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al ejecutar la consulta SELECT");
        }
    }

    @AfterClass
    public void tearDown() {
        // Cerrar la conexión a la base de datos si está abierta
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Cerrar el navegador
        if (driver != null) {
            driver.quit();
        }
    }
}
