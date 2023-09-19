package com.sfeir.bankaccount.business;

public class Account {
	private Balance balance;

	public Account() {
		this.balance = Balance.INITIAL_BALANCE;
	}

	public Account(Balance balance) {
		this.balance = balance;
	}

	public Balance balance() {
		return this.balance;
	}

}
