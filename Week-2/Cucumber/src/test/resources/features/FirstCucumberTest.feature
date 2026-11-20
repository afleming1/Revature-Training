# All Cucumber tests are facilitated by feature files. These features utilize Gherkin syntax
# (Collection of key words) to control the flow of your test executions.Ability:

# Class
Feature: Example Feature
  # Features can have a description added after their declaration
  The Feature is the reason for your file's existence. It is the overall user story / feature you are actually testing.
  Every test scenario you add to this file should, in theory, relate the given feature.

  # Scenarios are your actual test cases. These resources are used to determine how many tests you run for each
  # Feature file you have. If you have one Scenario, you have one test case, two scenarios means 2 test cases, etc...

  # Test Case
  Scenario: Example Scenario
    # Cucumber scenarios are facilitated by their steps. These are typically mapped to acceptance criteria.

    # Test Criteria

    # Given - Meant to indicate a starting condition
    # When - Meant to indicate an action performed
    # Then - Meant meant to indicate an achieved result, the meaning behind the key words is meant for us: Cucumber only
    # uses these key words to know that a new step is reached
    Given some starting condition
    When  one or more actions are performed
    Then  some outcome should be achieved