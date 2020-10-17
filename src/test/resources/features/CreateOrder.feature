# Created by nathan at 17/10/2020
Feature: Create Order

  Background:
    Given a store and an anonymous client
    And a cookie of name "Choco"

  Scenario: No cookie by default
    When the anonymous client create an order
    Then There is 0 in his number of cookies

  Scenario: add a cookie
    When the anonymous client create an order
    And the anonymous client add one cookie to his order
    Then There is 1 in his number of cookies

  Scenario: add same cookie
    When the anonymous client create an order
    And the anonymous client add one cookie to his order
    And the anonymous client add one cookie to his order
    Then There is 2 in his number of cookies