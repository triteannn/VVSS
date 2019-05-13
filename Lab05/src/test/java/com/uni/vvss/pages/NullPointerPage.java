package com.uni.vvss.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("https://nullpointer.space")
public class NullPointerPage extends PageObject {

    @FindBy(jquery = "[mattooltip='User']")
    private WebElementFacade userButton;

    @FindBy(jquery = "[mattooltip='Choose theme']")
    private WebElementFacade colorButton;

    @FindBy(jquery = "button:contains('Log In')")
    private WebElementFacade signInButton;

    @FindBy(jquery = "button:contains('Sign Up')")
    private WebElementFacade signUpButton;

    @FindBy(jquery = "[formcontrolname='displayName']")
    private WebElementFacade nameInput;

    @FindBy(jquery = "[formcontrolname='email']")
    private WebElementFacade emailInput;

    @FindBy(jquery = "[formcontrolname='password']")
    private WebElementFacade passwordInput;

    @FindBy(jquery = "button:contains('Log In')")
    private WebElementFacade submitLogInButton;

    @FindBy(jquery = "button:contains('Sign Up')")
    private WebElementFacade submitSignUpButton;

    @FindBy(tagName = "mat-spinner")
    private WebElementFacade loadingSpinner;

    @FindBy(tagName = "simple-snack-bar")
    private WebElementFacade snackBar;

    @FindBy(jquery = "button.light")
    private WebElementFacade lightThemeButton;

    @FindBy(jquery = "button.dark")
    private WebElementFacade darkThemeButton;

    public void show_user_menu() {
        userButton.click();
    }

    public void show_color_menu() {
        colorButton.click();
    }

    public void switch_theme() { lightThemeButton.click(); }

    public void navigate_to_sign_in_page() {
        signInButton.click();
    }

    public void navigate_to_sign_up_page() {
        signUpButton.click();
    }

    public void submit_login_form() {
        submitLogInButton.click();
    }

    public void submit_register_form() {
        submitSignUpButton.click();
    }

    public void enter_login_info(String email, String password) {
        emailInput.type(email);
        passwordInput.type(password);
    }

    public void enter_register_info(String name, String email, String password) {
        nameInput.type(name);
        emailInput.type(email);
        passwordInput.type(password);
    }

    public WebElementFacade getLoadingSpinner() {
        return this.loadingSpinner;
    }

    public WebElementFacade getLightThemeButton() {
        return this.lightThemeButton;
    }

    public WebElementFacade getDarkThemeButton() {
        return this.darkThemeButton;
    }

    public WebElementFacade getSnackBar() {
        return this.snackBar;
    }
}
