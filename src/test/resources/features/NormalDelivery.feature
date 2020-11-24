# new feature
# Tags: optional

Feature: Make a normal delivery

  Background:
    Given a customer
    And a store

  Scenario: successful delivery
    When a customer submit a delivery order
    And MarcelEat can deliver this one
    Then the order is well deliver by MarcelEat