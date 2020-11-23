# Created by Komlan Lemuel at 23/11/2020

  Feature: Create Personalized Recipe From Another Recipe
    All step for a client to create his own recipe from another recipe

  Background:
    Given Client named "Bob" with a phone number "0760156692" and with mail "bobo@gmail.com"
    And store located in "Antibes" with a tax of "1.05"
    And an ingredient "Vanilla" and an ingredient "Chocolate" and another ingredient "Milk Chocolate"
    And a recipe of cookie named "ChocoCookie" composed by "Vanilla" and "Chocolate"


    Scenario: Bob create his recipe
      When Bob create his recipe named "BobChocoCookie" from the recipe "ChocoCookie"
      And the cookie "BobChocoCookie" is composed by same ingredients of "ChocoCookie"
      And Bob can't retire the ingredient "Milk" to the recipe "BobChocoCookie"
      Then Bob add the ingredient "Milk" to "BobChocoCookie"
      And "BobChocoCookie" contains "Milk"
      Then Bob retire the ingredient "Vanilla" from "BobChocoCookie"

