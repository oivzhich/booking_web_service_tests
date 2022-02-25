Feature: Petstore features

  Scenario: The user checks available room for today
    When the user gets available room for today
    Then the API should return status 200
    And the response content is json
    And the API should return availability with following data:
      | date  | rooms_available | price |
      | today | 10              | 120   |

  Scenario: The user checks available room for tomorrow
    When the user gets available room for "2022-04-25"
    Then the API should return status 200
    And the response content is json
    And the API should return availability with following data:
      | date       | rooms_available | price |
      | 2022-04-25 | 10              | 120   |

