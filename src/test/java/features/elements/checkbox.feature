@checkbox
Feature: Checkbox Selection

  @smoke
  Scenario: Selecting a single checkbox
    Given the user is on the Checkbox page
    When the user expands the Home checkbox
    And the user selects the Desktop checkbox
    Then the Desktop checkbox should be checked
    And the result should display You have selected : Desktop

  @smoke
  Scenario: Unchecking a checkbox
    Given the user is on the Checkbox page
    When the user expands the Home checkbox
    And the user selects the Downloads checkbox
    And the user unselects the Downloads checkbox
    Then the Downloads checkbox should be unchecked

