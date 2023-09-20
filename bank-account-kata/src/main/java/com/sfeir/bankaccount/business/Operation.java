package com.sfeir.bankaccount.business;

import java.time.LocalDateTime;

import com.sfeir.bankaccount.business.Operation.OperationType;

public record Operation(OperationType type, LocalDateTime date, Amount amount) {

	enum OperationType {
		DEPOSIT, WITHDRAWAL
	}
}
