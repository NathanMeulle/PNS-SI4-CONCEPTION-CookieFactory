# Created by vincent at 17/10/2020
Feature: Create Order

  Background:
    Given a store and an anonymous customer
    And a cookie of name "Choco"

  Scenario: No cookie by default in the order
    When the anonymous client create an order
    Then There is 0 in his number of cookies

  Scenario: add a choco cookie in the order
    When the anonymous client create an order
    And the anonymous client add one cookie to his order
    Then There is 1 in his number of cookies
    And the anonymous client add one cookie to his order
    Then There is 2 in his number of cookies
