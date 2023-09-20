@US2
Feature: Account withdrawal
	In order to retrieve some or all of my savings as a bank client I want to make a withdrawal from my account

  Scenario: As a bank client I can retrieve some or all of my savings
    Given Client have an account with "100.0" balance
    When Client makes a withdrawal of "60.0"
    Then The account balance shoud be "40.0"
      

 Scenario: As a bank client i cannot not retrieve more then my savings
    Given Client have an account with "100.0" balance
    And Client makes a withdrawal of "60.0"
    When Client makes a withdrawal of "60.0"
   	Then The error message "Unauthorized withdrawal : Balance must not be negative" should be displayed
    And The account balance shoud be "40.0"