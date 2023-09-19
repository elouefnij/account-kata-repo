package com.sfeir.bankaccount.business;

public class UnauthorizedWithdrawalException extends Exception {
	private static final long serialVersionUID = 1L;

	public UnauthorizedWithdrawalException(String message) {
		super("Unauthorized withdrawal : ".concat(message));
	}
}
