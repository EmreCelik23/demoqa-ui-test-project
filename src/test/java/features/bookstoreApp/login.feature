@login
Feature: User Login Functionality

  @smoke
  Scenario: Successful login with valid credentials
    Given the user is on the Login page
    When the user enters a valid username
    And the user enters a valid password
    And the user clicks the Login button
    Then the user should be redirected to the dashboard

  @negative
  Scenario: Unsuccessful login with invalid credentials
    Given the user is on the Login page
    When the user enters an invalid username
    And the user enters an invalid password
    And the user clicks the Login button
    Then an error message should be displayed indicating Invalid credentials

  @negative
  Scenario: Login attempt with empty fields
    Given the user is on the Login page
    When the user clicks the Login button without entering credentials
    Then an error message should be displayed indicating Username is required and Password is required

