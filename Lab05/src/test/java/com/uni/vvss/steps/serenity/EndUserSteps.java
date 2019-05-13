package com.uni.vvss.steps.serenity;

import com.uni.vvss.pages.NullPointerPage;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;

import static org.hamcrest.MatcherAssert.assertThat;

public class EndUserSteps {

    private NullPointerPage nullPointerPage;

    @Step
    public void is_the_home_page() {
        nullPointerPage.open();
    }

    @Step
    public void go_to_sign_in_page() {
        nullPointerPage.show_user_menu();
        nullPointerPage.navigate_to_sign_in_page();
    }

    @Step
    public void go_to_sign_up_page() {
        nullPointerPage.show_user_menu();
        nullPointerPage.navigate_to_sign_up_page();
    }

    @Step
    public void login_into_user_account(String email, String password) {
        nullPointerPage.enter_login_info(email, password);
        nullPointerPage.submit_login_form();
        assertThat("A loading spinner should be showing", nullPointerPage.getLoadingSpinner() != null);
    }

    @Step
    public void create_a_user_account(String name, String email, String password) {
        nullPointerPage.enter_register_info(name, email, password);
        nullPointerPage.submit_register_form();
        assertThat("A loading spinner should be showing", nullPointerPage.getLoadingSpinner() != null);
    }

    @Step
    public void check_login_snackbar_error_message() {
        assertThat("A snackbar should show up with an error message saying that the credentials are invalid",
                nullPointerPage.getSnackBar().containsText("Invalid credentials."));
    }

    @Step
    public void check_register_snackbar_error_message() {
        assertThat("A snackbar should show up with an error message saying that the e-mail is unavailable",
                nullPointerPage.getSnackBar().containsText("This email address is not available."));
    }

    @Step
    public void switch_to_dark_theme() {
        nullPointerPage.show_color_menu();
        nullPointerPage.switch_theme();
        nullPointerPage.show_color_menu();
        assertThat("The selected theme should now be the dark theme so the dark theme button should be selected",
                nullPointerPage.getDarkThemeButton().containsElements(By.tagName("mat-icon")));
    }

    @Step
    public void switch_to_light_theme() {
        nullPointerPage.show_color_menu();
        nullPointerPage.switch_theme();
        nullPointerPage.show_color_menu();
        assertThat("The selected theme should still be the initial light theme an the selected icon should not change",
                !nullPointerPage.getDarkThemeButton().containsElements(By.tagName("mat-icon")));
    }
}
