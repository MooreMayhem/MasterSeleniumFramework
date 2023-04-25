package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.User;

public class CheckoutPage extends BasePage {

    private final By firstNameFld= By.id("billing_first_name");
    private final By lastNameFld = By.id("billing_last_name");
    private final By billingAddress1Fld = By.id("billing_address_1");
    private final By billingCityFld = By.id("billing_city");
    private final By billingPostCodeFld = By.id("billing_postcode");
    private final By billingEmailFld = By.id("billing_email");
    private final By placeOrderBtn = By.id("place_order");
    private final By successNotice = By.cssSelector(".woocommerce-notice");
    private final By showLoginBtn = By.className("showlogin");
    private final By usernameFld = By.id("username");
    private final By passwordFld = By.id("password");
    private final By loginBtn = By.name("login");
    private final By overlay = By.cssSelector(".blockUI.blockOverlay");

    private final By countryDropDown = By.id("billing_country");
    private final By stateDropDown = By.id("billing_state");
    private final By directBankTransferRadioBtn = By.id("payment_method_bacs");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage selectCountry(String countryName){
        Select select = new Select(driver.findElement(countryDropDown));
        select.selectByVisibleText(countryName);
        return this;
    }

    public CheckoutPage selectState(String stateName){
        Select select = new Select(driver.findElement(stateDropDown));
        select.selectByVisibleText(stateName);
        return this;
    }

    public CheckoutPage enterFirstName(String firstName){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameFld));
        e.clear();
        e.sendKeys(firstName);
//        driver.findElement(firstNameFld).clear();
//        driver.findElement(firstNameFld).sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastname){
        WebElement e =wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameFld));
        e.clear();
        e.sendKeys(lastname);
//        driver.findElement(lastNameFld).clear();
//        driver.findElement(lastNameFld).sendKeys(lastname);
        return this;
    }

    public CheckoutPage enterBillingAddress1(String billingAddress){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(billingAddress1Fld));
        e.clear();
        e.sendKeys(billingAddress);
//        driver.findElement(billingAddress1Fld).clear();
//        driver.findElement(billingAddress1Fld).sendKeys(billingAddress);
        return this;
    }

    public CheckoutPage enterBillingCity(String billingCity){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(billingCityFld));
        e.clear();
        e.sendKeys(billingCity);
//        driver.findElement(billingCityFld).clear();
//        driver.findElement(billingCityFld).sendKeys(billingCity);
        return this;
    }

    public CheckoutPage enterBillingPostCode(String billingPostCode){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(billingPostCodeFld));
        e.clear();
        e.sendKeys(billingPostCode);
//        driver.findElement(billingPostCodeFld).clear();
//        driver.findElement(billingPostCodeFld).sendKeys(billingPostCode);
        return this;
    }

    public CheckoutPage enterBillingEmail(String billingEmail){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(billingEmailFld));
        e.clear();
        e.sendKeys(billingEmail);
//        driver.findElement(billingEmailFld).clear();
//        driver.findElement(billingEmailFld).sendKeys(billingEmail);
        return this;
    }

    public CheckoutPage clickPlaceOrder(){
        waitForOverlaysToDisappear(overlay);
        driver.findElement(placeOrderBtn).click();
        return this;
    }

    public String getNotice(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successNotice)).getText();
//        return driver.findElement(successNotice).getText();
    }

    public CheckoutPage showLoginDetails(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(showLoginBtn)).click();
//        driver.findElement(showLoginBtn).click();
        return this;
    }

    public CheckoutPage enterUsername(String userName){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameFld));
        e.clear();
        e.sendKeys(userName);;
        return this;
    }

    public CheckoutPage enterPassword(String password){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordFld));
        e.clear();
        e.sendKeys(password);
        return this;
    }

    public CheckoutPage clickLoginBtn(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginBtn)).click();
//        driver.findElement(loginBtn).click();
        return this;
    }

    public CheckoutPage selectBankTransfer(){
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(directBankTransferRadioBtn));
        if(!e.isSelected()){
            e.click();
        }
        return this;
    }

    public CheckoutPage login(User user){
        return  enterUsername(user.getUsername()).enterPassword(user.getPassword()).clickLoginBtn();
    }

    public CheckoutPage setBillingAddress(BillingAddress billingAddress){
        return enterFirstName(billingAddress.getFirstName()).
                enterLastName(billingAddress.getLastName()).
                selectCountry(billingAddress.getCountry()).
                enterBillingAddress1(billingAddress.getAddressLineOne()).
                enterBillingCity(billingAddress.getCity()).
                selectState(billingAddress.getState()).
                enterBillingPostCode(billingAddress.getPostalCode()).
                enterBillingEmail(billingAddress.getEmail());
    }
}
