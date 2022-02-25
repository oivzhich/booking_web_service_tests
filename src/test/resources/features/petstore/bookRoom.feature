Feature: Booking a room

  Scenario: A user books 1 a room
    When I book a room with following data:
      | numOfDays | checkInDate |
      | 1         | 2013-04-10  |
    Then the API should return status 200
    And the response content is json
    And a room with following data should be added:
      | checkInDate | checkOutDate | totalPrice |
      | 2013-04-10  | 2013-04-11   | 130        |

  Scenario Outline: A user books 1 a room
    When I book a room starting "<checkInDate>" for "<numOfDays>"
    Then the API should return status 200
    And the response content is json
    And a room booking was successfully added starting "<checkInDate>" for "<numOfDays>"

    Examples:
      | numOfDays | checkInDate |
      | 2         | 2022-04-10  |
      | 1         | 2022-04-10  |
      | 1         | 2013-04-10  |
