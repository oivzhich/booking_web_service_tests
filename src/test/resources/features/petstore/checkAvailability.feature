Feature: Petstore features

  Scenario: The user checks available room for today
    When the user gets available room for today
    Then the API should return status 200
    And the response content is json
    And the API should return availability with following data:
      | date  | rooms_available | price |
      | today | 10              | 120   |

  Scenario: The user checks available room for specific date
    When the user gets available room for "2013-04-10"
    Then the API should return status 200
    And the response content is json
    And the API should return availability with following data:
      | date       | rooms_available | price |
      | 2013-04-10 | 10              | 140   |

