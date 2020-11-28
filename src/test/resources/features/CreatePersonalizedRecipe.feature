# Created by Esteve Thibaut at 08/11/2020


Feature: Create Personalized Recipe
  All step for a client to create his own recipe

  Background:
    Given Client named "Jhon" with a phone number "0658601237" and with a mail "jhon@gmail.com"
    And a store, located in "Avignon" with a tax of "2.5"
    And an ingredient called "vanilla" which is a "FLAVOR", costing 4
    And an ingredient called "chocolate" which is a "TOPPING", costing 4
    And another ingredient called "wholemeal flour" which is a "DOUGH", costing 2

  Scenario: Jhon create his recipe
    When We add 8 vanilla and 10 wholemeal flour to the stock at store "Avignon"
    And Jhon create his recipe named "HeavyVanilla" with "CHEWY" cooking, a "MIXED" mix and the ingredients vanilla and wholemeal flour
    Then the cookie "HeavyVanilla" is composed by the ingredients vanilla and wholemeal flour


  Scenario: Jhon modify a cookie : adding an ingredient
    When We add 8 vanilla and 10 wholemeal flour to the stock at store "Avignon"
    And Jhon select the cookie named "HeavyVanilla" with "CHEWY" cooking, a "MIXED" mix and the ingredients vanilla and wholemeal flour
    Then the cookie is a default Cookie

    And Jhon add the ingredient called "chocolate"
    And the cookie "HeavyVanilla" is composed by the ingredients vanilla, chocolate and wholemeal flour
    Then the cookie is a personnalized Cookie

  Scenario: Jhon modify a cookie : removing an ingredient
    When We add 8 vanilla and 10 wholemeal flour to the stock at store "Avignon"
    And Jhon select the cookie named "HeavyVanilla" with "CHEWY" cooking, a "MIXED" mix and the ingredients vanilla, chocolate and wholemeal flour
    Then the cookie is a default Cookie

    And Jhon remove the ingredient called "chocolate"
    And the cookie "HeavyVanilla" is composed by the ingredients vanilla and wholemeal flour
    Then the cookie is a personnalized Cookie

  Scenario: Jhon modify a cookie : non valid
    When We add 8 vanilla and 10 wholemeal flour to the stock at store "Avignon"
    And Jhon select the cookie named "HeavyVanilla" with "CHEWY" cooking, a "MIXED" mix and the ingredients vanilla and wholemeal flour
    Then the cookie is a default Cookie

    Then Jhon add the ingredient called "banana" and an error occured





