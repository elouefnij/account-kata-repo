package com.sfeir.bankaccount.business;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class AccountTest {

	@Test
	public void should_balance_be_zero_when_new_account_is_created() {
		BigDecimal expectedBalance = BigDecimal.ZERO;
		Account account;
		//
		account = new Account();
		//
		assertEquals(expectedBalance, account.balance());
	}
}
