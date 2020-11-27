Feature: Modify the schedule of a store

  Background:
    Given a store openning at 9am each day and closing at 11pm

  Scenario: Change Openning Hours
    When the manager sets the openning hours to 8am on Monday
    Then the store opens at 8am on Monday
    And the store opens at 9am on Tuesday

  Scenario: Add a day off
    When the manager sets a day of on Sunday
    Then the store is not opened on Sunday
    And the store opens at 9am on Tuesday




