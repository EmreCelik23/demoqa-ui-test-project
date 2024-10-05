@modals
Feature: Handling Modal Dialogs

  @smoke
  Scenario: Interacting with the small modal
    Given the user is on the Modal Dialogs page
    When the user clicks the Small Modal button
    Then a small modal should appear with the message This is a small modal. It has very less content
    When the user clicks the Close button on the small modal
    Then the small modal should be closed

  @smoke
  Scenario: Interacting with the large modal
    Given the user is on the Modal Dialogs page
    When the user clicks the Large Modal button
    Then a large modal should appear with a message containing more text
    When the user clicks the Close button on the large modal
    Then the large modal should be closed

  @smoke
  Scenario: Closing a modal by clicking outside the modal
    Given the user is on the Modal Dialogs page
    When the user clicks the Small Modal button
    And the user clicks outside the modal dialog
    Then the small modal should be closed
