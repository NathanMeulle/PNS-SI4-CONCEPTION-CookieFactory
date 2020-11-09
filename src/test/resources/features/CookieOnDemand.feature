Feature: Cookie Picking management

  Background:
    Given a registered client named "Elena" with email "elena@gmail.com" and phone "1234"

  Scenario: the client wants to order and choose the pick up time
    When "Elena" place an order
    Then she choose to pick her cookies at "3":"0":"0"PM on the same day
    And an order is sent to the store
    Then "Elena" comes at "15":"0":"0" and retrieve her order
    And there is no order pending


