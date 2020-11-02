Feature: Becoming a member

  Background:
    Given An client named "Charlie" with a phoneNumber "0755555555"
    And with a email "charlie@gmail.com",

  Scenario: He is anonymous and wants to register
    When he fill a form in order to register and he submits it
    Then he becomes a member
    And there is "1" in the list of customers
    And with name : "Charlie"
    And a phoneNumber : "0755555555"
    And a email : "charlie@gmail.com"

  Scenario: He wants to register again while being a member
    When "Charlie" wants to register
    Then he fill a form in order to register and he submits it
    And the email already exist is the database
    Then register failure
