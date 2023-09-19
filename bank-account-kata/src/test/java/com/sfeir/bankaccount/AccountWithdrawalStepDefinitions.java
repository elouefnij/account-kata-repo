package com.sfeir.bankaccount;

import com.sfeir.bankaccount.business.Account;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountWithdrawalStepDefinitions {
	Account account;

	@Given("Client have an account with {string} in balance")
	public void client_have_an_account_with_in_balance(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When("Client makes a withdrawal of {string}")
	public void client_makes_a_withdrawal_of(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("The account balance shoud be {int}")
	public void the_account_balance_shoud_be(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
}
