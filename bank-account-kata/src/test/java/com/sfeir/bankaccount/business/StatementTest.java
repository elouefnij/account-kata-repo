package com.sfeir.bankaccount.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import org.junit.Test;

import com.sfeir.bankaccount.business.Operation.OperationType;

public class StatementTest {

	@Test
	public void should_new_operation_be_added_when_deposit() {
		//
		Supplier<LocalDateTime> date_supplier = () -> {
			return LocalDateTime.of(2023, 9, 01, 10, 00);
		};
		StatementLine expected_sl = new StatementLine(
				new Operation(OperationType.DEPOSIT, date_supplier.get(), Amount.valueOf("100")),
				Balance.valueOf("100"));
		//
		Account account = new Account(date_supplier);
		account.deposit(Amount.valueOf("100"));
		//
		assertNotNull(account.statement().lines().get(0));
		assertEquals(expected_sl.balance(), account.statement().lines().get(0).balance());
		assertEquals(expected_sl.operation(), account.statement().lines().get(0).operation());
	}

}