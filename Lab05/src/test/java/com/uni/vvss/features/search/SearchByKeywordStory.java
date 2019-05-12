package com.uni.vvss.features.search;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.uni.vvss.steps.serenity.EndUserSteps;

@RunWith(SerenityRunner.class)
public class SearchByKeywordStory {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    private EndUserSteps anna;

    @Test
    public void search_for_something_and_then_clear() {
        anna.is_the_home_page();
        anna.starts_search();
        anna.search_for("Why do I get NullPointerException?");
        anna.clear_search();
        anna.starts_search();
    }
} 
