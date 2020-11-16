Feature: Cookie Picking management

  Background:
    Given a registered client named "Elena" with email "elena@gmail.com" and phone "1234"

  Scenario: the client wants to order and choose the pick up time
    When "Elena" place an order
    And she choose to pick her cookies at "15":"0":"0" on the same day
    And her order is sent to the store
    Then "Elena" comes on time and retrieve her order


  Scenario: the client wants to order and choose the pick up time
    When "Elena" place an order
    And she choose to pick her cookies at "15":"0":"0" on the same day
    And her order is sent to the store
    Then "Elena" comes hour earlier and she can't pick her order
