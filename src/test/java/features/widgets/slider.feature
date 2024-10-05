@slider
Feature: Slider Interaction

  @smoke
  Scenario: Verifying the default slider value
    Given the user is on the Slider page
    Then the slider should be set to the default value 25

  @smoke
  Scenario: Moving the slider to a specific value
    Given the user is on the Slider page
    When the user moves the slider to the value 50
    Then the slider value should be displayed as 50

  @smoke
  Scenario: Moving the slider to the minimum value
    Given the user is on the Slider page
    When the user moves the slider to the value 0
    Then the slider value should be displayed as 0

  @smoke
  Scenario: Moving the slider to the maximum value
    Given the user is on the Slider page
    When the user moves the slider to the value 100
    Then the slider value should be displayed as 100

