package com.sfeir.bankaccount.business;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.sfeir.bankaccount.business.Operation.OperationType;

public class Statement {

	private Supplier<LocalDateTime> dateSupplier;
	private List<StatementLine> lines = new ArrayList<StatementLine>();

	public Statement(Supplier<LocalDateTime> dateSupplier) {
		this.dateSupplier = dateSupplier;
	}

	public void deposit(Amount amount, Balance balance) {
		lines.add(new StatementLine(new Operation(OperationType.DEPOSIT, dateSupplier.get(), amount), balance));
	}

	public void withdrawal(Amount amount, Balance balance) {
		lines.add(new StatementLine(new Operation(OperationType.WITHDRAWAL, dateSupplier.get(), amount), balance));
	}

	public List<StatementLine> lines() {
		return lines;
	}

	public Supplier<LocalDateTime> dateSupplier() {
		return dateSupplier;
	}

}
