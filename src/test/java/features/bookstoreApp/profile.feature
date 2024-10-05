@profile
Feature: User Profile Functionality

  @smoke
  Scenario: Going to bookstore page
    Given the user is on the Profile page
    When the user clicks the Go To Book Store Button
    Then the user should be directed to Book Store Page

  @smoke
  Scenario: Deleting all books in the profile page
    Given the user is on the Profile page
    When the user clicks the Delete All Books Button
    Then a modal should appear with a message "Do you want to delete all books?"
    When the user clicks the OK Button
    Then an alert should appear with a message "No books available in your's collection!"

  @smoke
  Scenario: Logging out from the profile page
    Given the user is on the Profile page
    When the user clicks the Logout button
    Then the user should be redirected to the login page