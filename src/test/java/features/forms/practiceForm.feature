@practiceForm
Feature: Practice Form Submission

  @smoke
  Scenario: Successfully submitting the form with valid data
    Given the user is on the Practice Form page
    When the user enters all the required fields
    And the user clicks the Submit button in the form
    Then a confirmation dialog should appear with the submitted information

  @negative
  Scenario: Submitting the form with missing required fields
    Given the user is on the Practice Form page
    When the user leaves the First Name field empty and enters all other required fields
    And the user clicks the Submit button in the form
    And an error message should appear for the First Name field

  @negative
  Scenario: Submitting the form with an invalid email format
    Given the user is on the Practice Form page
    When the user enters all the required fields and invalid-email in the Email field in the form
    And the user clicks the Submit button in the form
    And an error message should appear for the Email field

  @negative
  Scenario: Submitting the form without selecting a gender
    Given the user is on the Practice Form page
    When the user fills out all the required fields except the Gender
    And the user clicks the Submit button in the form
    And an error message should appear for the Gender field
