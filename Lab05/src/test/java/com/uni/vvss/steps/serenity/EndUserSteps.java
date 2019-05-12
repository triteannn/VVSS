package com.uni.vvss.steps.serenity;

import com.uni.vvss.pages.NullPointerPage;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;

import static org.hamcrest.MatcherAssert.assertThat;

public class EndUserSteps {

    private NullPointerPage nullPointerPage;

    @Step
    private void enters(String keyword) {
        nullPointerPage.enter_keywords(keyword);
    }

    @Step
    public void starts_search() {
        nullPointerPage.show_search_bar();
    }

    @Step
    public void is_the_home_page() {
        nullPointerPage.open();
    }

    @Step
    public void search_for(String term) {
        enters(term);
    }

    @Step
    public void clear_search() {
        nullPointerPage.clear_keywords();
        assertThat("Search bar should be empty", nullPointerPage.getSearchBar().getText().length() == 0);
    }

    @Step
    public void go_to_sign_in_page() {
        nullPointerPage.show_user_menu();
        nullPointerPage.navigate_to_sign_in_page();
    }

    @Step
    public void login_into_user_account(String email, String password) {
        nullPointerPage.enter_user_info(email, password);
        nullPointerPage.submit_login_form();
        assertThat("A loading spinner should be showing", nullPointerPage.getLoadingSpinner() != null);
    }

    @Step
    public void switch_theme() {
        nullPointerPage.show_color_menu();
        nullPointerPage.switch_theme();
        nullPointerPage.show_color_menu();
        assertThat("The selected theme should now be the dark theme so the dark theme button should be selected",
                nullPointerPage.getDarkThemeButton().containsElements(By.tagName("mat-icon")));
    }
}
