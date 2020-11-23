# Created by nathan at 08/11/2020
Feature: Pay Order

  Background:
    Given a new cookieFactory
    And a store

  Scenario: A registered client wants to use the loyalty program : Cookie counter
    When a client subscribe to the loyalty program
    And he makes an order of 4 cookies
    Then there is 4 in the cookie counter
    And he makes an order of 3 cookies
    Then there is 7 in the cookie counter

  Scenario: A registered client wants to use the loyalty program : counter reinitialization
    When a client subscribe to the loyalty program
    And he makes an order of 29 cookies
    Then there is 29 in the cookie counter
    Then he makes an order of 1 cookies
    Then there is 0 in the cookie counter
    # le nombre de cookie est réinitialisé après le discount

  Scenario: A registered client wants to use the loyalty program : counter reinitialization
    When a client subscribe to the loyalty program
    And he makes an order of 29 cookies
    Then there is 29 in the cookie counter
    Then he makes an order of 3 cookies
    Then there is 0 in the cookie counter

  Scenario: A registered client wants to use the loyalty program : counter reinitialization
    When a client subscribe to the loyalty program
    And he makes an order of 35 cookies
    Then there is 0 in the cookie counter

  Scenario Outline: A registered client wants to use the loyalty program : discount
    When a client subscribe or not to the "<loyaltyProgram>"
    And he makes an order of "<nbCookie>" cookie costing "<price>" at a store with "<tax>"
    Then he must pay "<TotalTTC>"
    And there is "<cookieCounter>" cookies
    Examples:
      | nbCookie | price | loyaltyProgram | tax | TotalTTC | cookieCounter |
      | 1        | 2     | no             | 1   | 2        | 0             |
      | 2        | 2     | no             | 1   | 4        | 0             |
      | 2        | 2     | yes            | 1   | 4        | 2             |
      | 30       | 2     | no             | 1   | 60       | 0             |
      | 30       | 2     | yes            | 1   | 54       | 0             |
      | 31       | 2     | yes            | 1   | 55.8     | 0             |
      | 30       | 2     | yes            | 1.4 | 75.6     | 0             |
      | 31       | 2     | yes            | 1.4 | 78.12    | 0             |


    Scenario: Order with a best of store cookie
      When an order of 3 cookie choco and 2 cookie vanilla
      Then the customer pay 10.0 euros
      And the cookiFactory update the bestOf
      Then cookie choco is the bestOfCookie

      When an order of 3 cookie choco and 2 cookie vanilla
      Then the customer pay 9.4 euros

  Scenario: Order a personnalized cookie
    When an order of 5 cookie personnalized
    Then the customer pay 12.5 euros
    And the cookiFactory update the bestOf
    Then cookie personnalized is the bestOfCookie

    When an order of 5 cookie personnalized
    Then the customer pay 11.25 euros




