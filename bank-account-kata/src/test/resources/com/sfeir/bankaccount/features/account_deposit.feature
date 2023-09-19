@US1
Feature: Account deposit
In order to save money as a bank client I want to make a deposit in my account

  Scenario: As a bank client I want to make a deposit in my account
    Given Client have an account with "100.0" balance
    When Client make a deposit of "200.0"
    Then The account balance shoud be "300.0"