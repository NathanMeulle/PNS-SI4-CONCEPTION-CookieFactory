# new feature
# Tags: optional
# @author Delmotte Vincent

Feature: Make order

  Background:
    Given an order with 3 "Chocolate" cookies, an anonymous customer, a kitchen

    Scenario: Make an order with enough ingredients
      When an anonymous customer submit his order and 4 "Chocolate" in the kitchen
      Then order is done

    Scenario: Make an order without enough ingredients
      When an anonymous customer submit his order and 2 "Chocolate" in the kitchen
      Then order is cancel

