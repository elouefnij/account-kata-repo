package com.sfeir.bankaccount;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import com.sfeir.bankaccount.business.Account;
import com.sfeir.bankaccount.business.Amount;
import com.sfeir.bankaccount.business.Balance;
import com.sfeir.bankaccount.business.UnauthorizedWithdrawalException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountStepDefinitions {
	Account account;
	Exception actualException;
	Supplier<LocalDateTime> dateSupplier = () -> {
		return LocalDateTime.now();
	};

	@Given("Client have an account with {string} balance")
	public void client_have_an_account_with_balance(String balance) {
		account = new Account(dateSupplier, Balance.valueOf(balance));
	}

	@When("Client makes a deposit of {string}")
	public void client_makes_a_deposit_of(String amount) {
		account.deposit(Amount.valueOf(amount));
	}

	@Then("The account balance shoud be {string}")
	public void the_account_balance_shoud_be(String balance) {
		Balance expected_balance = Balance.valueOf(balance);
		assertEquals(expected_balance, account.balance());
	}

	@Then("The error message {string} should be displayed")
	public void the_error_message_should_be_displayed(String expectedMessage) {
		assertEquals(expectedMessage, actualException.getMessage());
	}

	@When("Client makes a withdrawal of {string}")
	public void client_makes_a_withdrawal_of(String amount) {
		try {
			account.withdraw(Amount.valueOf(amount));
		} catch (UnauthorizedWithdrawalException e) {
			actualException = e;
		}
	}

	@When("Client checks operations")
	public void client_checks_operations() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("The account statement should be shown")
	public void the_account_statement_should_be_shown(io.cucumber.datatable.DataTable dataTable) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.
		throw new io.cucumber.java.PendingException();
	}

}
