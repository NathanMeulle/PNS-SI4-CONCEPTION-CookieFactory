# Created by nathan at 07/11/2020
Feature: Ordering
  All steps for a client to order
## TODO Rename

  Background:
    Given An anonymous client called "Vincent" with the phoneNumber "0612345678"
    And a store located in "Paris" with a tax of "1.2"
    And an ingredient called Chocolate, costing 2
    And an ingredient called MnMs, costing 4
    And A recipe "Choco" with ingredients "Chocolate"
    And A recipe "MnMChoco" with ingredients "Chocolate" and "MnM"



  Scenario: Vincent make an order
    When Add 5 Chocolate and 3 MnMs to the stock at store "Paris"
    And The client "Vincent" makes an order of 3 "Choco" and 1 "MnMChoco" at store "Paris"
    Then The client "Vincent" has 1 order
    And the total price of this order is now 19.2 TTC for client "Vincent"
    #3 Choco (3 * 2) +3 + 1 CookieChoco (1 * 2+4) +1 = 16 HT * tax (1.2) = 19.4


  Scenario: Vincent can't make an order because out of stock
    When Add 5 Chocolate and 3 MnMs to the stock at store "Paris"
    And The client "Vincent" try to create an order of 7 "Choco" and 1 "MnMChoco" at store "Paris"
    Then The client "Vincent" has 0 order


  Scenario: Make order of custom cookies
    When the user creates his custom recipe composed of "nutella", "pistachio" and "peanut" and is TOPPED and CRUNCHY
    Then the user makes the order of his custom cookies
    And the store starts making the order



