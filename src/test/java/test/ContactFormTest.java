package test;

import api.DummyApi;
import org.testng.annotations.Test;
import pageObject.ContactForm;

import static org.testng.AssertJUnit.*;

public class ContactFormTest extends DummyApi {
    @Test
    public void testPassant(){
        ContactForm form = new ContactForm(driver);
        DummyApi api = new DummyApi();
        String option1 =api.getGender();
        System.out.println(option1);
        form.SelectOption(option1);
        var selectedChoice = form.getSelectedOption();
        form.setFirstName(api.getPrenom());
        form.setLastname(api.getLastName());
        form.setMessage(api.getText());
        form.setCompany("Agiltoo");
        form.setPhone("0758889843");
        form.setMessageTitle("Message from everyone");
        form.clickButton();
        assertEquals(selectedChoice.size(),1);
        assertTrue(selectedChoice.contains(option1));
        assertEquals(option1,"Homme","Homme");
        assertEquals("Le message a été envoyé.",form.getPopUpMessage());
    }
    @Test
    public void testPrenom(){
        ContactForm form = new ContactForm(driver);
        DummyApi api = new DummyApi();
        String option1 = "Homme";
        form.SelectOption(option1);
        form.setLastname(api.getLastName());
        form.setMessage(api.getText());
        form.setCompany("Agiltoo");
        form.setPhone("0758889843");
        form.setMessageTitle("Message from everyone");
        form.clickButton();
        assertEquals("Veuillez remplir tous les champs obligatoires.",form.getPopUpMessage());
    }

    @Test
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
}
