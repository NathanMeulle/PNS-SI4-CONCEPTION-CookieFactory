# new feature
# Tags: optional

Feature: Make a normal delivery

  Background:
    Given a customer, an order and a store


  Scenario: successful delivery
    When a customer submit a delivery order
    And MarcelEat can deliver this one
    Then the order is well prepare for delivery
    And a delivery man pick up the order
    Then the order is well deliver by MarcelEat
    Then the store pay MarcelEat