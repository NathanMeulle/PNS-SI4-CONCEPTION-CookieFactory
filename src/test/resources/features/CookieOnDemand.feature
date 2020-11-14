Feature: Cookie Picking management

  Background:
    Given a client named "Elena" with email "elena@gmail.com"

  Scenario: the client wants to order and choose the pick up time
    When "Elena" place an order
    Then she choose to pick hers cookie at "1PM"
    And "1" order is sent to the store

  Scenario: the client wants to retrieve its order
    When "Elena" comes in time and retrieve her order
    Then there is "0" order pending
    And the previous order is added to the order History