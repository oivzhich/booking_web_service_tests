Feature: Petstore features

  Scenario: The user checks available room for today
    When the user gets available room for today
    Then the API should return status 200
    And the response content is json
    And the API should return availability with following data:
      | date  | price |
      | today | 120   |
    And room_available field should be grater than 0

  Scenario Outline: The user checks available room for specific date
    When the user gets available room for "<date>"
    Then the API should return status 200
    And the response content is json
    And the API should return availability with following data:
      | date   | rooms_available   | price   |
      | <date> | <rooms_available> | <price> |

    Examples:
      | date       | rooms_available | price |
      | 2013-04-10 | 10              | 140   |
      | 2013-04-11 | 10              | 150   |
      | 2022-11-11 | 10              | 120   |
