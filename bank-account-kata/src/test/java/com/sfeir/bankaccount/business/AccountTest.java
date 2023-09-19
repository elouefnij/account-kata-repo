package com.sfeir.bankaccount.business;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class AccountTest {

	@Test
	public void should_balance_be_zero_when_new_account_is_created() {
		Balance expectedBalance = new Balance(BigDecimal.ZERO);
		//
		Account account = new Account();
		//
		assertEquals(expectedBalance, account.balance());
	}

	@Test
	public void should_balance_be_updated_when_new_account_is_created_with_initial_value() {
		Balance expectedBalance = new Balance(BigDecimal.TEN);
		//
		Account account = new Account(expectedBalance);
		//
		assertEquals(expectedBalance, account.balance());
	}
}
