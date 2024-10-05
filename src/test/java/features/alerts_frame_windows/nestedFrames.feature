@nestedFrames
Feature: Interaction with Nested Frames

  @smoke
  Scenario: Accessing parent frame content
    Given the user is on the Nested Frames page
    When the user switches to the parent frame
    Then the user should see the text Parent frame

  @smoke
  Scenario: Accessing child frame content
    Given the user is on the Nested Frames page
    When the user switches to the parent frame
    And the user switches to the child frame within the parent frame
    Then the user should see the text Child Iframe

