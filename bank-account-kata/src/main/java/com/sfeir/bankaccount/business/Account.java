package com.sfeir.bankaccount.business;

import java.time.LocalDateTime;
import java.util.function.Supplier;

public class Account {
	private Balance balance = Balance.INITIAL_BALANCE;
	private Statement statement;

	public Account(Supplier<LocalDateTime> dateSupplier) {
		this.statement = new Statement(dateSupplier);
	}

	public Account(Supplier<LocalDateTime> dateSupplier, Balance balance) {
		this(dateSupplier);
		initializeAccount(balance);
	}

	private void initializeAccount(Balance balance) {
		this.balance = balance;
		statement.deposit(new Amount(balance.value()), balance);
	}

	public Account deposit(Amount amount) {
		this.balance = balance().add(amount.value());
		statement.deposit(amount, balance);
		return this;
	}

	public Account withdraw(Amount amount) throws UnauthorizedWithdrawalException {
		try {
			this.balance = balance().subtract(amount.value());
			statement.withdrawal(amount, balance);
		} catch (IllegalArgumentException e) {
			throw new UnauthorizedWithdrawalException(e.getMessage());
		}
		return this;
	}

	public Balance balance() {
		return this.balance;
	}

	public Statement statement() {
		return statement;
	}

}
