package com.uni.vvss.features.search;

import com.uni.vvss.steps.serenity.EndUserSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SerenityRunner.class)
public class SignInStory {

    @Managed(uniqueSession = true)
    private WebDriver webdriver;

    @Steps
    private EndUserSteps anna;

    @Test
    public void login_successfully() {
        anna.is_the_home_page();
        anna.go_to_sign_in_page();
        anna.login_into_user_account("tudortritean@gmail.com", "whatever1");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat("User should be redirected to the profile page after a successful login",
                webdriver.getCurrentUrl().equals("https://nullpointer.space/user/profile"));
    }
}
