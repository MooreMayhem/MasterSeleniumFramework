package org.selenium.pom.tests;

import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyFirstTestCase extends BaseTest {

    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws IOException {
        String searchFor = "Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);

        Product product = new Product(1215);
//        BillingAddress billingAddress = new BillingAddress().
//                setFirstName("demo").
//                setLastName("user").
//                setAddressLineOne("San Francisco").
//                setCity("San Francisco").
//                setPostalCode("94188").
//                setEmail("askomdch@gmail.com");
//
//        BillingAddress billingAddress = new BillingAddress
//                ("demo", "user", "San Francisco", "San Francisco", "94188", "askomdch@gmail.com"); Started using Json file

        HomePage homePage = new HomePage(driver).load();
        StorePage storePage = homePage.navigateToStoreUsingMenu();
        storePage.enterTextInSearchFld(searchFor).clickSearchBtn();
//        storePage.enterTextInSearchField("Blue").clickSearchBtn(); used functional page object coding to create line 24
//        storePage.clickSearchBtn(); changed using Builder Pattern to make chaining methods possible on line 25

        Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchFor + "”");

        storePage.clickAddToCartBtn(product.getName());
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
//
//        Thread.sleep(5000);
//        driver.findElement(By.cssSelector(".checkout-button")).click();

        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.setBillingAddress(billingAddress).selectBankTransfer().
                clickPlaceOrder();

        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws IOException {
        String searchFor = "Blue";
        User user = new User("demouser22", "password");
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);

        HomePage homePage = new HomePage(driver).load();

        StorePage storePage = homePage.navigateToStoreUsingMenu();
        storePage.enterTextInSearchFld(searchFor).clickSearchBtn();
        Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchFor + "”");

        storePage.clickAddToCartBtn(product.getName());
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());

        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.showLoginDetails();
        //login and enter billing details
        checkoutPage.login(user).
                setBillingAddress(billingAddress).selectBankTransfer().
                clickPlaceOrder();

        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");

    }
}
