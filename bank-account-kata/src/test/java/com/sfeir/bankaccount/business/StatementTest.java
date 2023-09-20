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
		Account account = new Account(date_supplier);
		//
		account.deposit(Amount.valueOf("100"));
		//
		assertStatementLine(0, OperationType.DEPOSIT, date_supplier.get(), "100", "100", account);
	}

	@Test
	public void should_new_operation_be_added_when_withdrawal() throws UnauthorizedWithdrawalException {
		Account account = new Account(date_supplier, Balance.valueOf("150"));
		//
		account.withdraw(Amount.valueOf("20"));
		//
		assertStatementLine(1, OperationType.WITHDRAWAL, date_supplier.get(), "20", "130", account);
	}

	@Test
	public void should_no_operation_be_added_when_rejected_withdrawal() {
		var account = new Account(date_supplier, Balance.valueOf("150"));
		//
		assertThrows(UnauthorizedWithdrawalException.class, () -> {
			new Account(date_supplier, Balance.valueOf("150")).withdraw(Amount.valueOf("170"));
		});
		//
		assertStatementLinesNumber(1, account);
	}

	@Test
	public void should_no_operation_be_added_when_rejected_deposit() {
		//
		Account account = new Account(date_supplier);
		//
		assertThrows(IllegalArgumentException.class, () -> {
			account.deposit(Amount.valueOf("-20"));
		});
		assertTrue(account.statement().lines().isEmpty());
	}

	@Test
	public void should_all_operations_be_added_when_multiple_transactions() throws UnauthorizedWithdrawalException {
		Account account = new Account(date_supplier, Balance.valueOf("150")).withdraw(Amount.valueOf("20"))
				.deposit(Amount.valueOf("200")).deposit(Amount.valueOf("200"));
		//
		assertStatementLinesNumber(4, account);
		assertStatementLine(3, OperationType.DEPOSIT, date_supplier.get(), "200", "530", account);

	}

	private void assertStatementLinesNumber(int expectedSize, Account account) {
		assertEquals(expectedSize, account.statement().lines().size());
	}

	private void assertStatementLine(int expectedPosition, OperationType expectedOperationType,
			LocalDateTime expectedDate, String expectedOperationAmount, String expectedBalance, Account account) {
		//
		StatementLine expected_statement_line = new StatementLine(
				new Operation(expectedOperationType, expectedDate, Amount.valueOf(expectedOperationAmount)),
				Balance.valueOf(expectedBalance));
		//
		assertNotNull(account.statement().lines().get(expectedPosition));
		assertEquals(expected_statement_line.balance(), account.statement().lines().get(expectedPosition).balance());
		assertEquals(expected_statement_line.operation(),
				account.statement().lines().get(expectedPosition).operation());
	}

}