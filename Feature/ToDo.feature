Feature: Feature to verify the different ToDo functionalities

  Scenario: TS01 - Add to-do is working. (Add the below items, verify if the items are added and also the counter on the bottom left)
    When I add all four items to the todo list
    Then I should see all four items in the list
    And I should see the number of items in the left bottom counter

  Scenario: TS02 - Mark Completed (Verify if the items are crossed out and verify the counter on the bottom-left)
    When I complete the todos
    Then those todos should be striked out
    And I should see the remaining number of items in the left bottom counter

  Scenario: TS03 - Delete the item [Clean the house] and verify if the item is removed from the list.
    When I delete Clean the house
    Then the item should be removed

  Scenario: TS04: Filtering a to-do (Click on the Active button and verify if it shows the Active items. Click on Completed and verify if it shows the completed items)
    When I click Active button, only active items should displayed
    When I click Completed button, only completed items should displayed