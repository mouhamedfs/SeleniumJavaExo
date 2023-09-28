package pageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.support.ui.Select;

public class ContactForm{

    public WebDriver driver;

    public ContactForm(WebDriver driver){
        this.driver = driver;
    }

    private By genre  = By.id("gender");
    private By firstname = By.id("first-name");
    private By lastname = By.id("last-name");
    private By company = By.id("company");
    private By phone = By.id("phone");
    private By messagetitle = By.id("message-title");
    private By message = By.id("message");
    private By button = By.id("submit-button");

    private By popupmessage = By.id("popin-message");

    private Select findDropdownElement() {
        return new Select(driver.findElement(genre));
    }

    public void SelectOption(String option) {
        findDropdownElement().selectByVisibleText(option);
    }

    public List<String> getSelectedOption() {
        List<WebElement> selector = findDropdownElement().getAllSelectedOptions();
        return  selector.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void setFirstName(String firstName){
        driver.findElement(firstname).sendKeys(firstName);
    }

    public void setLastname(String name){
        driver.findElement(lastname).sendKeys(name);
    }

    public void setCompany(String society){
        driver.findElement(company).sendKeys(society);
    }

    public void setPhone(String tel){
        driver.findElement(phone).sendKeys(tel);
    }

    public void setMessageTitle(String messtitle){
        driver.findElement(messagetitle).sendKeys(messtitle);
    }

    public void setMessage(String mess){
        driver.findElement(message).sendKeys(mess);
    }

    public void clickButton(){
        driver.findElement(button).click();
    }

    public String getPopUpMessage(){
      return driver.findElement(popupmessage).getText();
    }



}
