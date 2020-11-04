# new feature
# Tags: optional
# @author Delmotte Vincent

Feature: Pick up an order

  Background:
    Given an order and an anonymous customer

  Scenario: pick up an order already cooked
    When an anonymous client pick up his order
    And his order is ready
    Then order is well picked up and put in the history

  Scenario: pick up an order not cooked yet
    When an anonymous client pick up his order
    And his order isn't ready yet
    Then order raise an exception