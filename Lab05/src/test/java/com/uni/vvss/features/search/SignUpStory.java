package com.uni.vvss.features.search;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.uni.vvss.steps.serenity.EndUserSteps;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SerenityRunner.class)
public class SignUpStory {

    @Managed(uniqueSession = true)
    private WebDriver webdriver;

    @Steps
    private EndUserSteps anna;

    @Test
    public void create_an_account_successfully() {
        anna.is_the_home_page();
        anna.go_to_sign_up_page();
        anna.create_a_user_account("Tudor", "tudortritean@gmail.com", "whatever1");
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat("User should be redirected to the login page after a successfully creating account",
                webdriver.getCurrentUrl().equals("https://nullpointer.space/user/login"));
    }

    @Test
    public void try_creating_an_account_that_already_exists() {
        anna.is_the_home_page();
        anna.go_to_sign_up_page();
        anna.create_a_user_account("Tudor", "tudortritean@gmail.com", "whatever1");
        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat("User should not be redirected",
                webdriver.getCurrentUrl().equals("https://nullpointer.space/user/register"));

        anna.check_register_snackbar_error_message();
    }
} 
