Feature: Cookie Picking management

  Background:
    Given a registered client named "Elena" with email "elena@gmail.com" and phone "1234"

  Scenario: the client wants to order and choose the pick up time : come on time
    When "Elena" place an order
    And she chooses to pick her cookies at "15":"0":"0" on the same day
    And her order is sent to the store
    Then "Elena" comes at "15":"0":"0" and retrieve her order


  Scenario: the client wants to order and choose the pick up time : come to early
    When "Elena" place an order
    And she chooses to pick her cookies at "15":"0":"0" on the same day
    And her order is sent to the store
    Then "Elena" comes at "14":"0":"0" and she can't pick her order

  Scenario: the client wants to order and choose a wrong pick up time : store closed
    When "Elena" place an order
    And she chooses to pick her cookies when the store is closed
    Then her order is not sent to the store

  Scenario Outline: the client wants to order and choose a pick up time
    When "Elena" place an order
    And she chooses to pick her cookies at "<hour>":"<min>":"<sec>"
    Then her order is sent to the store : "<storeReceived>"
    And "Elena" comes at "<commingHour>":"<commingMin>":"<commingSec>"
    Then she can pick her order : "<yes/no>"

    Examples:
      | hour | min | sec | storeReceived | commingHour | commingMin | commingSec | yes/no |
      | 14   | 00  | 00  | yes           | 14          | 30         | 00         | yes    |
      | 14   | 00  | 00  | yes           | 13          | 59         | 59         | no     |
      | 14   | 00  | 00  | yes           | 14          | 00         | 00         | yes    |
      | 23   | 00  | 00  | no            | 14          | 00         | 00         | _      |


