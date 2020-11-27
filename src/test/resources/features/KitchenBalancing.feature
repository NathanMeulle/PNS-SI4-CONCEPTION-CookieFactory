

Feature: Kitchen Balancing

  Background:
    Given An anonymous client called "Toto"
    And four stores, one store in "Paris", one store in "Toulouse", one store in "Lyon" and one store in "Nice"
    And an ingredient called Chocolate,costing 2
    And an ingredient called MnMs,costing 4
    And A recipe "Choco" with ingredient "Chocolate"
    And Another recipe "MnMChoco" with ingredients "Chocolate" and "MnM"


  Scenario: Toto can't make an order because out of stock but when he change store then his order is done
    When Add 1 Chocolate and 3 MnMs to the stock at store located Paris
    And Add 10 Chocolate and 10 MnMs to the stock at store located Lyon
    Then Toto try to create an order of 3 Choco and 1 MnMChoco at store located Paris
    And As the store does not have enough ingredients, the latter offers a list of stores in order of proximity that can meet the needs.
    And The closest store is in "Lyon"
    Then Toto choose a store located "Lyon"
    And Toto has 1 order
