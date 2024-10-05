@selectMenu
Feature: Select Menu Functionality

  @smoke
  Scenario: Selecting an option from the dropdown
    Given the user is on the Select Menu page
    When the user selects Group 1, Option 1 from the Select Value dropdown
    Then the selected value should be Group 1, Option 1

  @smoke
  Scenario: Selecting an option from the old style select menu
    Given the user is on the Select Menu page
    When the user selects Green from the Old Style Select Menu
    Then the selected option should be Green

  @smoke
  Scenario: Selecting multiple options from the multi-select dropdown
    Given the user is on the Select Menu page
    When the user selects Red and Blue from the Multi Select Dropdown
    Then the selected options should include Red and Blue

  @smoke
  Scenario: Selecting a car from the standard dropdown
    Given the user is on the Select Menu page
    When the user selects Audi from the Standard Multi Select dropdown
    Then the selected car should be Audi

