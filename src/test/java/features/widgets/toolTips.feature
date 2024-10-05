@tooltips
Feature: Tool Tips Interaction

  @smoke
  Scenario: Verifying tooltip on hover over the button
    Given the user is on the Tool Tips page
    When the user hovers over the Hover me to see button
    Then a tooltip should appear with the text "You hovered over the Button"

  @smoke
  Scenario: Verifying tooltip on hover over the text field
    Given the user is on the Tool Tips page
    When the user hovers over the Hover me to see text field
    Then a tooltip should appear with the text "You hovered over the text field"

  @smoke
  Scenario: Verifying tooltip on hover over the link
    Given the user is on the Tool Tips page
    When the user hovers over the Hover me to see link
    Then a tooltip should appear with the text "You hovered over the Contrary"
