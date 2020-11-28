# Created by nathan at 07/11/2020
Feature: Ordering
  All steps for a client to order

  Background:
    Given An anonymous client called "Vincent" with the phoneNumber "0612345678"
    Given A client called "Gustave" with the phoneNumber "0612345678" and mail "g@mail.com"
    And a store located in "Paris" with a tax of "1.2"
    And an ingredient called Chocolate, costing 2
    And an ingredient called MnMs, costing 4
    And A recipe "Choco" with ingredients "Chocolate"
    And A recipe "MnMChoco" with ingredients "Chocolate" and "MnM"


  Scenario: Anonymous makes an order
    When Add 5 Chocolate and 3 MnMs to the stock at store "Paris"
    And The Anonymous client "Vincent" makes an order of 3 "Choco" and 1 "MnMChoco" at store "Paris"
    Then The Anonymous client "Vincent" has 1 order
    And the total price of this order is now 19.2 TTC for Anonymous client "Vincent"
    #3 Choco (3 * 2) +3 + 1 CookieChoco (1 * 2+4) +1 = 16 HT * tax (1.2) = 19.4


  Scenario: Anonymous can't make an order because out of stock
    When Add 5 Chocolate and 3 MnMs to the stock at store "Paris"
    And The Anonymous client "Vincent" try to create an order of 7 "Choco" and 1 "MnMChoco" at store "Paris"
    Then The Anonymous client "Vincent" has 0 order

  Scenario: Client makes an order
    When Add 5 Chocolate and 3 MnMs to the stock at store "Paris"
    And The client "Gustave" makes an order of 3 "Choco" and 1 "MnMChoco" at store "Paris"
    Then The client "Gustave" has 1 order
    And the total price of this order is now 19.2 TTC for client "Gustave"
    #3 Choco (3 * 2) +3 + 1 CookieChoco (1 * 2+4) +1 = 16 HT * tax (1.2) = 19.4


  Scenario: Client can't make an order because out of stock
    When Add 5 Chocolate and 3 MnMs to the stock at store "Paris"
    And The client "Gustave" try to create an order of 7 "Choco" and 1 "MnMChoco" at store "Paris"
    Then The client "Gustave" has 0 order


  Scenario: Make order of custom cookies
    When the user creates his custom recipe composed of "nutella", "pistachio" and "peanut" and is TOPPED and CRUNCHY
    Then the user makes the order of his custom cookies
    And the store starts making the order



