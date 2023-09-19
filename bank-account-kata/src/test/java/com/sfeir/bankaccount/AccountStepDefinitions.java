package com.sfeir.bankaccount;

import static org.junit.Assert.assertEquals;

import com.sfeir.bankaccount.business.Account;
import com.sfeir.bankaccount.business.Amount;
import com.sfeir.bankaccount.business.Balance;
import com.sfeir.bankaccount.business.UnauthorizedWithdrawalException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountStepDefinitions {
	Account account;

	@Given("Client have an account with {string} balance")
	public void client_have_an_account_with_balance(String balance) {
		account = new Account(Balance.valueOf(balance));
	}

	@When("Client make a deposit of {string}")
	public void client_make_a_deposit_of(String amount) {
		account.deposit(Amount.valueOf(amount));
	}

	@Then("The account balance shoud be {string}")
	public void the_account_balance_shoud_be(String balance) {
		Balance expected_balance = Balance.valueOf(balance);
		assertEquals(expected_balance, account.balance());
	}

	@When("Client makes a withdrawal of {string}")
	public void client_makes_a_withdrawal_of(String amount) {
		try {
			account.withdraw(Amount.valueOf(amount));
		} catch (UnauthorizedWithdrawalException e) {
			e.printStackTrace();
		}
	}

}
