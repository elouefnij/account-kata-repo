package com.sfeir.bankaccount.business;

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

	public void deposit(Amount amount) {
		balance = balance.add(amount.value());
	}

	public void withdraw(Amount amount) {
		balance = balance.subtract(amount.value());
	}

}
