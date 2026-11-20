package com.revature.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FirstCucumberTestSteps {
    /*
        Cucumber uses annotations to map code execution with the steps of a scenario in a feature file. This code is
        called "glue" code, the methods inside "glue steps".

        Method names can be anything. Annotations must match feature files.

        It's worth knowing: the key word of the glue annotation does not need to match the key word in the feature file.
     */

    @Given("some starting condition")
    public void thisCan() {
        System.out.println("Given statement executed");
    }

    @When("one or more actions are performed")
    public void beCalled() {
        System.out.println("When statement executed");
    }

    @Then("some outcome should be achieved")
    public void whatever() {
        System.out.println("Then statement executed");
    }
}
