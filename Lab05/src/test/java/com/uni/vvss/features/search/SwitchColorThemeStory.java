package com.uni.vvss.features.search;

import com.uni.vvss.steps.serenity.EndUserSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class SwitchColorThemeStory {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    private EndUserSteps anna;

    @Test
    public void switch_to_dark_theme() {
        anna.is_the_home_page();
        anna.switch_to_dark_theme();
    }

    @Test
    public void try_switching_to_the_already_applied_theme() {
        anna.is_the_home_page();
        anna.switch_to_light_theme();
    }
}
