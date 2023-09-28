package api;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pageObject.ContactForm;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DummyApi {
// Set the base URI for the API
    private static String prenom;
    private static String lastName;
    private static String message;
    private static String genre;

    protected WebDriver driver;


    @BeforeMethod
    public void getApi(){
        RestAssured.baseURI = "https://dummyapi.io/data/v1";
        String baseUrl  = "https://testqa.purse.tech/fake-contact";
        System.setProperty("webdriver.chrome.driver","C:\\Users\\monsh\\Desktop\\Driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);


    Response response = RestAssured.given()
            .header("app-id", "61f4248c9d9bb038eaf0c6c0")
            .when()
            .get("/post/60d21bad67d0d8992e610daf");


        if (response.getStatusCode() == 200) {

        JsonPath jsonPath = response.jsonPath();
        setPrenom(jsonPath.getString("owner.firstName"));
        setText(jsonPath.getString("text"));
        setLastName(jsonPath.getString("owner.lastName"));
        setGender(jsonPath.getString("owner.title"));
    }


}
    public  String getPrenom() {
        return prenom;
    }

    public static void setPrenom(String prenom) {
        DummyApi.prenom = prenom;
    }
    public static void setGender(String genres) {
        DummyApi.genre = genres;
    }
    public  String getLastName() {
        return lastName;
    }
    public static void setLastName(String lastName) {
        DummyApi.lastName = lastName;
    }
    public  String getText() {
        return message;
    }

    public  String getGender() {
        if(Objects.equals(genre, "mr")){
            return "Homme";
        }
        else {
            return "Femme";
        }
    }
    public static void setText(String text) {
        DummyApi.message = text;
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}