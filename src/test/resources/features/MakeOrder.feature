#Created by Lemuel at 18/10/2020
  Feature: Make Order

    Background:
      Given a manager, a factory with a store and a anonymous client
      And a cookie named "Choco" with ingredient "Chocolate"


    Scenario: Adding Ingredient to Stock
      When the manager adds 5 "Chocolate" to stock
      And the manager asks for the number of ingredients in kitchen stock
      Then There are 1 Ingredient in the stock
      And the manager asks for the number of "Chocolate" in stock
      Then There are 5 "Chocolate" in stock
      When the factory adds the new recipe "Choco"
      And the factory asks for the number of recipe
      Then There are 1 in his number of recipe
      When the client create an order
      And the client add ten cookies to his order
      Then There are 10 in his number of cookies
      And the client valid the order
      Then the kitchen does not have enough ingredients in stock to prepare the order
      And manager adds 5 "Chocolate"
      Then There are 10 "Chocolate"
      And the client valid the order again
      Then the kitchen has enough ingredients in stock to prepare the order
      Then the order is done


