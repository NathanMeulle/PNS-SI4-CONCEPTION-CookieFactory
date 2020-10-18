#Created by Lemuel at 18/10/2020
  Feature: Manage Stock

    Background:
      Given  a manager
      And a factory with a store with his kitchen
      And an ingredient called "Chocolate"

    Scenario: By default, no ingredients in stock
      When the manager asks for the number of ingredients in stock
      Then There are 0 Ingredient in the kitchen stock

    Scenario: Adding Ingredient with its number
      When the manager adds 5 "Chocolate"
      And the manager asks for the number of ingredients in stock
      Then There are 1 Ingredient in the kitchen stock
      And the manager asks for the number of "Chocolate"
      Then There are 5 in his number of "Chocolate"
      And the manager adds 4 "Chocolate"
      And the manager asks for the number of "Chocolate"
      Then There are 9 in his number of "Chocolate"

