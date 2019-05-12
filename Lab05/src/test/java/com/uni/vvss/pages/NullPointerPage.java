package com.uni.vvss.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("https://nullpointer.space")
public class NullPointerPage extends PageObject {

    @FindBy(jquery = "[mattooltip='Search']")
    private WebElementFacade searchButton;

    @FindBy(id = "search-bar")
    private WebElementFacade searchBar;

    @FindBy(jquery = "[mattooltip='Clear']")
    private WebElementFacade clearButton;

    @FindBy(jquery = "[mattooltip='User']")
    private WebElementFacade userButton;

    @FindBy(jquery = "[mattooltip='Choose theme']")
    private WebElementFacade colorButton;

    @FindBy(jquery = "button:contains('Log In')")
    private WebElementFacade signInButton;

    @FindBy(jquery = "[formcontrolname='email']")
    private WebElementFacade emailInput;

    @FindBy(jquery = "[formcontrolname='password']")
    private WebElementFacade passwordInput;

    @FindBy(jquery = "button:contains('Log In')")
    private WebElementFacade submitButton;

    @FindBy(tagName = "mat-spinner")
    private WebElementFacade loadingSpinner;

    @FindBy(jquery = "button.dark")
    private WebElementFacade darkThemeButton;

    public void show_search_bar() {
        searchButton.click();
    }

    public void show_user_menu() {
        userButton.click();
    }

    public void show_color_menu() {
        colorButton.click();
    }

    public void switch_theme() {
        darkThemeButton.click();
    }

    public void navigate_to_sign_in_page() {
        signInButton.click();
    }

    public void submit_login_form() {
        submitButton.click();
    }

    public void enter_keywords(String keyword) {
        searchBar.type(keyword);
    }

    public void enter_user_info(String email, String password) {
        emailInput.type(email);
        passwordInput.type(password);
    }

    public void clear_keywords() {
        clearButton.click();
    }

    public WebElementFacade getSearchBar() {
        return this.searchBar;
    }

    public WebElementFacade getLoadingSpinner() {
        return this.loadingSpinner;
    }

    public WebElementFacade getDarkThemeButton() {
        return this.darkThemeButton;
    }
}
