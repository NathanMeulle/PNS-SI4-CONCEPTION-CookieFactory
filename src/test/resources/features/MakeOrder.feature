#Created by Lemuel at 18/10/2020
  Feature: Make Order

    Background:
      Given a manager, a factory with a store and a anonymous client
      And a cookie named "Choco" with ingredient "Chocolate"


    Scenario: Make Order Step By Step
      When the manager adds 5 "Chocolate" to stock
      And the manager asks for the number of ingredients in kitchen stock
      Then There are 1 Ingredient in the stock
      And the manager asks for the number of "Chocolate" in stock
      Then There are 5 "Chocolate" in stock

    # Adding New Recipe
      When the factory adds the new recipe "Choco"
      And the factory asks for the number of recipe
      Then There are 1 in his number of recipe

    # Create New Order
      When the client create an order
      Then the order state is "CHOICE"
      And the client add 10 cookies to his order
      Then There are 10 in his number of cookies

    # Submit an order with out enough ingredients
      When the client valid the order but the kitchen does not have enough ingredients in stock to prepare the order
      Then the order state is "REFUSED"
      And manager adds 5 "Chocolate"
      Then There are 10 "Chocolate"

    # Submit an order with enough ingredients
      And the client valid the order again and the kitchen has enough ingredients in stock to prepare the order
      Then the order is done
      And the order state is "COOKED"





