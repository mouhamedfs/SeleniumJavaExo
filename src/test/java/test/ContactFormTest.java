package test;

import api.DummyApi;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import pageObject.ContactForm;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class ContactFormTest extends DummyApi {

    ExtentReports extent = new ExtentReports();
    ExtentSparkReporter spark = new ExtentSparkReporter("Spark.html");


    @Test
    @Description("Test Description: Cas passant pour la validation du formulaire)")
    public void testPassant(){
        //Configuration
        spark.config().setTheme(Theme.DARK);
        extent.attachReporter(spark);
        ContactForm form = new ContactForm(driver);
        DummyApi api = new DummyApi();
        ExtentTest extentTest = extent.createTest("Validation du formulaire");

        //Gender
        String option1 =api.getGender();
        System.out.println(option1);
        form.SelectOption(option1);
        var selectedChoice = form.getSelectedOption();
        extentTest.pass("Renseigner le champs genre");

        form.setFirstName(api.getPrenom());
        extentTest.pass("Renseigner le champs prenom");

        form.setLastname(api.getLastName());
        extentTest.pass("Renseigner le champs nom");

        form.setMessage(api.getText());
        extentTest.pass("Renseigner le champs Message");

        form.setCompany("Agiltoo");
        form.setPhone("0758889843");
        form.setMessageTitle("Message from everyone");

        form.clickButton();
        extentTest.pass("Cliquer sur valider");

        assertEquals(selectedChoice.size(),1);
        assertTrue(selectedChoice.contains(option1));
        assertEquals(option1,"Homme","Homme");
        assertEquals("Le message a été envoyé.",form.getPopUpMessage());
        extent.flush();
    }
    @Test
    @Description("Test Description: Cas non passant pour la validation du champs obligatoire Prenom)")
    public void testPrenom(){
        spark.config().setTheme(Theme.DARK);
        extent.attachReporter(spark);
        ContactForm form = new ContactForm(driver);
        DummyApi api = new DummyApi();
        ExtentTest extentTest1 = extent.createTest("Validation du formulaire");
        String option1 = "Homme";
        form.SelectOption(option1);
        extentTest1.pass("Renseigner le champs genre");
        form.setLastname(api.getLastName());
        extentTest1.pass("Renseigner le champs nom");
        form.setMessage(api.getText());
        form.setCompany("Agiltoo");
        form.setPhone("0758889843");
        form.setMessageTitle("Message from everyone");
        form.clickButton();
        assertEquals("Veuillez remplir tous les champs obligatoires.",form.getPopUpMessage());
    }

    /*
    @Test
    @Description("Test Description: Cas non passant pour la validation du champs obligatoire Nom)")
    public void testNom(){
        ContactForm form = new ContactForm(driver);
        DummyApi api = new DummyApi();
        String option1 = "Homme";
        form.SelectOption(option1);
        form.setFirstName(api.getPrenom());
        form.setMessage(api.getText());
        form.setCompany("Agiltoo");
        form.setPhone("0758889843");
        form.setMessageTitle("Message from everyone");
        form.clickButton();
        assertEquals("Veuillez remplir tous les champs obligatoires.",form.getPopUpMessage());
    }

     */

}
