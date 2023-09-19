package com.sfeir.bankaccount.business;

import java.math.BigDecimal;

public class Account {
	private Balance balance = Balance.INITIAL_BALANCE;

	public Account() {
	}

	public Account(Balance balance) {
		this.balance = balance;
	}

	public Balance balance() {
		return this.balance;
	}

	public void deposit(BigDecimal deposit_value) {
		balance = balance.add(deposit_value);
	}

}
