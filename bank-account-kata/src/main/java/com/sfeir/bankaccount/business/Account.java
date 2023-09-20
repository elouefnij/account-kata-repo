package com.sfeir.bankaccount.business;

import java.time.LocalDateTime;
import java.util.function.Supplier;

public class Account {
	private Balance balance = Balance.INITIAL_BALANCE;
	private Statement statement;

	public Account(Supplier<LocalDateTime> date_supplier) {
		this.statement = new Statement(date_supplier);
	}

	public Account(Supplier<LocalDateTime> date_supplier, Balance balance) {
		this(date_supplier);
		this.balance = balance;
	}

	public Balance balance() {
		return this.balance;
	}

	public void deposit(Amount amount) {
		balance = balance.add(amount.value());
		statement.deposit(amount, balance);
	}

	public void withdraw(Amount amount) throws UnauthorizedWithdrawalException {
		try {
			balance = balance.subtract(amount.value());
			statement.withdrawal(amount, balance);
		} catch (IllegalArgumentException e) {
			throw new UnauthorizedWithdrawalException(e.getMessage());
		}
	}

	public Statement statement() {
		return statement;
	}

}
