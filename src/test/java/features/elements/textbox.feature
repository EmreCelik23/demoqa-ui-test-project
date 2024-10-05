@textbox
Feature: Text Box Form Validation

  @smoke
  Scenario Outline: Successful form submission with valid data
    Given the user is on the Text Box page
    When the user enters <fullName> in the Full Name field
    And the user enters <email> in the Email field
    And the user enters <curAddress> in the Current Address field
    And the user enters <permAddress> in the Permanent Address field
    And the user clicks the Submit button
    Then the form should display the entered details below the form

  Examples:
    | fullName   | email                | curAddress      | permAddress         |
    | Emre Celik | temrecelik@epias.com | Maslak,Sariyer  | Yamanevler,Umraniye |
    | John Doe   | john.doe@example.com | 123 Main Street | 456 Elm Street      |

  @negative
  Scenario Outline: Form submission with invalid email
    Given the user is on the Text Box page
    When the user enters <fullName> in the Full Name field
    And the user enters invalid-email in the E-mail field
    And the user enters <curAddress> in the Current Address field
    And the user enters <permAddress> in the Permanent Address field
    And the user clicks the Submit button
    Then an error message should be displayed for the Email field

  Examples:
    | fullName | curAddress      | permAddress    |
    | John Doe | 123 Main Street | 456 Elm Street |
