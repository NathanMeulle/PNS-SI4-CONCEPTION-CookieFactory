# Created by nathan at 16/10/2020
Feature: Create Recipe

  Background:
    Given a factory with a store
    And a recipe of a cookie named "Choco"

  Scenario: No cookie by default
    When the factory requests his number of recipe
    Then There is 0 in his number of cookies

  Scenario: adding cookie
    When the factory adds the recipe "Choco"
    And the factory requests his number of recipe
    Then There is 1 in his number of recipe
    And The cookie "Choco" can't be add a second time

