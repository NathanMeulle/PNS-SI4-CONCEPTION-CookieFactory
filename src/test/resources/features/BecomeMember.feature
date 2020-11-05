Feature: Becoming a member

  Background:
    Given a cookieFactory
    And A client named "Charlie" with a phoneNumber "0755555555" and with an email "charlie@gmail.com",

  Scenario: He is anonymous and wants to register
    When he fill a form in order to register and he submits it
    Then he becomes a member
    And there is "1" in the list of customers
    And with name : "Charlie"
    And a phoneNumber : "0755555555"
    And a email : "charlie@gmail.com"

  Scenario: He wants to register again while being a member
    When he fill a form in order to register and he submits it
    Then he becomes a member
    When A client named "Charlie" with a "0755555555" and with a "charlie@gmail.com" wants to register
    Then the email already exist is the database
    And there is "1" in the list of customers
    And register "failure"

  Scenario Outline: An other client can't register with same information
    When he fill a form in order to register and he submits it
    Then he becomes a member
    When A client named "<name>" with a "<phoneNumber>" and with a "<email>" wants to register
    Then register "<registerStatus>"
    And there is "<nbCustomer>" in the list of customers
    Examples:
      | name    | phoneNumber | email             | registerStatus | nbCustomer |
      | Charlie | 0755555555  | charlie@gmail.com |     failure    | 1          |
      | Charles | 0755555555  | charlie@gmail.com |     failure    | 1          |
      | Charlie | 0755555555  | charlo@gmail.com  |     failure    | 1          |
      | Charlie | 0766666666  | charlo@gmail.com  |     success    | 2          |



