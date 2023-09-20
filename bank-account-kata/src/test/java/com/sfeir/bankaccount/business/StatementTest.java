package com.sfeir.bankaccount.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import org.junit.Before;
import org.junit.Test;

import com.sfeir.bankaccount.business.Operation.OperationType;

public class StatementTest {

	Supplier<LocalDateTime> date_supplier;

	@Before
	public void setup() {
		date_supplier = () -> {
			return LocalDateTime.of(2023, 9, 01, 10, 00);
		};
	}

	@Test
	public void should_new_operation_be_added_when_deposit() {
		//
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

	@Test
	public void should_new_operation_be_added_when_withdrawal() throws UnauthorizedWithdrawalException {
		//
		StatementLine expected_sl = new StatementLine(
				new Operation(OperationType.WITHDRAWAL, date_supplier.get(), Amount.valueOf("20")),
				Balance.valueOf("130"));
		//
		Account account = new Account(date_supplier, Balance.valueOf("150"));
		account.withdraw(Amount.valueOf("20"));
		//
		assertNotNull(account.statement().lines().get(0));
		assertEquals(expected_sl.balance(), account.statement().lines().get(0).balance());
		assertEquals(expected_sl.operation(), account.statement().lines().get(0).operation());
	}
}