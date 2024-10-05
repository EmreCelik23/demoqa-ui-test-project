@progressBar
Feature: Progress Bar Interaction

  @smoke
  Scenario: Starting and verifying the progress bar
    Given the user is on the Progress Bar page
    When the user clicks the Start button
    And the user waits until the end
    Then the progress bar text should be 100%

  @smoke
  Scenario: Pausing the progress bar at 50%
    Given the user is on the Progress Bar page
    When the user clicks the Start button
    And the user clicks the Stop button when the progress bar reaches 50%
    Then the progress bar text should be 50%

  @smoke
  Scenario: Restarting the progress bar after stopping
    Given the user is on the Progress Bar page
    When the user clicks the Start button
    And the user clicks the Stop button when the progress bar reaches 70%
    Then the progress bar text should be 70%
    And the user clicks the Start button
    And the user waits until the end
    Then the progress bar text should be 100%

  @smoke
  Scenario: Resetting the progress bar
    Given the user is on the Progress Bar page
    When the user clicks the Start button
    And the user waits until the end
    And the user clicks the Reset button
    Then the progress bar text should be 0%

