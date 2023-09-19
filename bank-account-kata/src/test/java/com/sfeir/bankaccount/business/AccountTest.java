package com.sfeir.bankaccount.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class AccountTest {

	@Test
	public void should_balance_be_zero_when_new_account_is_created() {
		Balance expectedBalance = Balance.valueOf("0");
		//
		Account account = new Account();
		//
		assertEquals(expectedBalance, account.balance());
	}

	@Test
	public void should_balance_be_updated_when_new_account_is_created_with_initial_value() {
		Balance expectedBalance = Balance.valueOf("100");
		//
		Account account = new Account(Balance.valueOf("100"));
		//
		assertEquals(expectedBalance, account.balance());
	}

	@Test
	public void should_throw_exception_when_new_account_is_created_with_negative_value() {
		assertThrows("Balance cannot be negative", IllegalArgumentException.class, () -> {
			new Account(Balance.valueOf("-100"));
		});
	}

	@Test
	public void should_balance_be_updated_when_deposit() {
		Balance expectedBalance = Balance.valueOf("110");
		Account account = new Account(Balance.valueOf("100"));
		//
		account.deposit(Amount.valueOf("10"));
		//
		assertEquals(expectedBalance, account.balance());
	}

	@Test
	public void should_throw_exception_when_negative_deposit() {
		//
		assertThrows("Amount must not be negative", IllegalArgumentException.class, () -> {
			new Account(Balance.valueOf("100")).deposit(Amount.valueOf("-100"));
		});
	}

	@Test
	public void should_balance_be_updated_when_withdrawal_of_positive_amount() {
		Balance expectedBalance = Balance.valueOf("90");
		Account account = new Account(Balance.valueOf("100"));
		//
		account.withdraw(Amount.valueOf("10"));
		//
		assertEquals(expectedBalance, account.balance());
	}

}