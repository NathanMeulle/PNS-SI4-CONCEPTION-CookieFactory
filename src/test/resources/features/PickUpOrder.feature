# new feature
# Tags: optional
# @author Delmotte Vincent

Feature: Pick up an order

  Background:
    Given an order and an anonymous customer

  Scenario: pick up an order already cooked
    When an anonymous client pick up his order at "15":"0":"0"
    And his order is ready
    And the order is paid
    Then order is well picked up and put in the history

  Scenario: can't pick up if not paid
    When an anonymous client pick up his order at "15":"0":"0"
    And his order is ready
    And the order is not paid
    Then order is not picked up

  Scenario: pick up an order not cooked yet
    When an anonymous client pick up his order at "15":"0":"0"
    And his order isn't ready yet
    And the order is paid
    Then order raise an exception

    ## TODO pick up when the store is closed...
