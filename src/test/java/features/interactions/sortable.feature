@sortable
Feature: Sortable List Interaction

  @smoke
  Scenario: Reordering items in a sortable list
    Given the user is on the Sortable page
    When the user drags Item 3 to the position of Item 1
    Then the order of items should be Item 3, Item 1, Item 2

  @smoke
  Scenario: Verifying the original order of items
    Given the user is on the Sortable page
    Then the items should be displayed in their original order Item 1, Item 2, Item 3

  @smoke
  Scenario: Reordering items in the grid layout
    Given the user is on the Sortable page
    When the user drags Item 4 to the position of Item 6
    Then the grid layout should reflect the new order with Item 6 now above Item 4

  @smoke
  Scenario: Dropping an item back to its original position
    Given the user is on the Sortable page
    When the user drags Item 3 to a new position and then drops it back
    Then the items should be displayed in their original order Item 1, Item 2, Item 3


