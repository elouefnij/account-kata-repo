@US3
Feature: Account statement
	In order to check my operations as a bank client I want to see the history (operation, date, amount, balance) of my operations

  Scenario: As a bank client I want to see the history (operation, date, amount, balance) of my operations
    Given Client have an account with "100.0" balance
    And  Client makes a deposit of "50.0"
    And  Client makes a withdrawal of "60.0"
    And  Client makes a deposit of "40.0"
    When Client checks operations
    Then The account statement should be printed
    
    | OPERATION  | DATE             | AMOUNT | BALANCE |
    | DEPOSIT    | 2023-09-01 10:00 | 100.0  | 100.0   |
    | DEPOSIT    | 2023-09-01 10:00 | 50.0   | 150.0   |
    | WITHDRAWAL | 2023-09-01 10:00 | 60.0   | 90.0    |
    | DEPOSIT    | 2023-09-01 10:00 | 40.0   | 130.0   |