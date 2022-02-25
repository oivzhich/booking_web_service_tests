Feature: Booking a room

  Scenario: A user books a room
    When I book a room with following data:
      | numOfDays | checkInDate |
      | 1         | 2013-04-10  |
    Then the API should return status 200
    And the response content is json
    And a room with following data should be added:
      | checkInDate | checkOutDate | totalPrice |
      | 2013-04-10  | 2013-04-11   | 130        |
