@US3
Feature: Account statement
	In order to check my operations as a bank client I want to see the history (operation, date, amount, balance) of my operations

  Scenario: As a bank client I want to see the history (operation, date, amount, balance) of my operations
    Given Client have an account with "100.0" balance
    And  Client makes a deposit of "50.0"
    And  Client makes a withdrawal of "60.0"
    And  Client makes a deposit of "40.0"
    When Client checks operations
    Then The account statement should be shown
    
    |	Operation		|	Date				|	Amount	|	Balance	| 
    |	DEPOSIT			|	2023-09-01	|	100.00	|	100.00	|
    |	DEPOSIT			|	2023-09-01	|	50.00		|	150.00	|
    |	WITHDRAWAL	|	2023-09-04	|	60.00		|	90.00		|
    |	WITHDRAWAL	|	2023-09-11	|	100.00	|	90.00		|