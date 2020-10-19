Feature: Becoming a member

  Background:
    Given An client named "Charlie" with a phoneNumber "0755555555"
    And with a email "charlie@gmail.com",

  Scenario: He is anonymous and wants to register
    When "Charlie" wants to register
    Then he fill a form in order to register
    And he submits it
    Then he becomes a member
    And with name : "Charlie"
    And a phoneNumber : "0755555555"
    And a email : "charlie@gmail.com"

  Scenario: He is register and wants to re-register
    When "Charlie" wants to register
    Then he fill a form in order to register
    And the email already exist is the database
    Then register failure
