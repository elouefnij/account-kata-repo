package com.sfeir.bankaccount;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import com.sfeir.bankaccount.business.Account;
import com.sfeir.bankaccount.business.Amount;
import com.sfeir.bankaccount.business.Balance;
import com.sfeir.bankaccount.business.UnauthorizedWithdrawalException;
import com.sfeir.bankaccount.infrastructure.ConsoleStatementPrinter;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountStepDefinitions {

	private Supplier<LocalDateTime> dateSupplier;
	private Account account;
	private Exception actualException;
	private String printedStatement;

	@Before
	public void setup() {
		dateSupplier = () -> {
			return LocalDateTime.of(2023, 9, 01, 10, 00);
		};
	}

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
		Balance expectedBalance = Balance.valueOf(balance);
		assertEquals(expectedBalance, account.balance());
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
		printedStatement = account.statement().print(new ConsoleStatementPrinter());
	}

	@Then("The account statement should be printed")
	public void the_account_statement_should_be_printed(DataTable dataTable) {
		assertEquals(dataTable.toString().replaceAll("\s", ""), printedStatement.replaceAll("\s", ""));
	}

}
