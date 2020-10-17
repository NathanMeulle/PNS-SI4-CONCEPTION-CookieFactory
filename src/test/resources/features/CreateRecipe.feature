# Created by nathan at 16/10/2020
Feature: Create Recipe

  Background:
    Given a factory with a store
    And a cookie of name "Choco"

  Scenario: No cookie by default
    When the factory requests his number of cookies
    Then There is 0 in his number of cookies

  Scenario: adding cookie
    When the factory adds the cookie "Choco"
    And the factory requests his number of cookies
    Then There is 1 in his number of cookies
    And The cookie "Choco" can't be add a second time

