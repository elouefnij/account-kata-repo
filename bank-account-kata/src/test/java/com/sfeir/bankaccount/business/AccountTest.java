package com.sfeir.bankaccount.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

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

	@Test
	public void should_throw_exception_when_new_account_is_created_with_negative_value() {
		BigDecimal negative_value = BigDecimal.valueOf(-100.0);
		//
		assertThrows("Balance cannot be negative", IllegalArgumentException.class, () -> {
			new Account(new Balance(negative_value));
		});
	}

}
