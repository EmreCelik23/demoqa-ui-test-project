@droppable
Feature: Drag and Drop Interaction

  @smoke
  Scenario: Successfully dragging and dropping an element
    Given the user is on the Droppable page
    When the user drags the Drag me box
    And the user drops it into the Drop here area
    Then the Drop here area should display Dropped!

  @smoke
  Scenario: Verifying the original state of the droppable area
    Given the user is on the Droppable page
    Then the Drop here area should initially display Drop here

  @negative
  Scenario: Attempting to drop an element outside the droppable area
    Given the user is on the Droppable page
    When the user drags the Drag me box
    And the user drops it outside the Drop here area
    Then the Drop here area should still display Drop here