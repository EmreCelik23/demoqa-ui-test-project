@bookstore
Feature: Book Management Functionality

  @smoke
  Scenario: Checking books in bookstore
    Given the user is on the Login page to enter profile page
    When the user enters a valid username in usernameBox
    And the user enters a valid password in passwordBox
    And the user clicks Login button
    Then the user should be directed to the Profile Page
    When the user clicks the Go To Book Store Button in Profile Page
    Then the user must be directed to Book Store Page
    And the contents of the books must be equal with the CSV File
