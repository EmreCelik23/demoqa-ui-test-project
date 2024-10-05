@alerts
Feature: Handling Alerts

  @smoke
  Scenario: Handling simple alerts
    Given the user is on the Alerts page
    When the user clicks the Click me button to trigger a simple alert
    Then an alert should appear with the message You clicked a button
    And the user accepts the alert

    When the user clicks the Click me button to trigger a timed alert
    Then after 5 seconds an alert should appear with the message This alert appeared after 5 seconds
    And the user accepts the alert

    When the user clicks the Click me button to trigger a confirmation alert
    Then an alert should appear with the message Do you confirm action?
    When the user accepts the alert
    Then the result text should display You selected Ok

    When the user clicks the Click me button to trigger a confirmation alert
    Then an alert should appear with the message Do you confirm action?
    When the user dismisses the alert
    Then the result text should display You selected Cancel

    When the user clicks the Click me button to trigger a prompt alert
    Then an alert should appear with a prompt message Please enter your name
    When the user enters Emre Celik into the prompt
    And the user accepts the alert
    Then the result text should display You entered Emre Celik


