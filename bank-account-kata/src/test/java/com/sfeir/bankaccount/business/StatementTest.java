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
import com.sfeir.bankaccount.infrastructure.ConsoleStatementPrinter;

public class StatementTest {

	Supplier<LocalDateTime> dateSupplier;

	@Before
	public void setup() {
		dateSupplier = () -> {
			return LocalDateTime.of(2023, 9, 01, 10, 00);
		};
	}

	@Test
	public void should_new_operation_be_added_when_deposit() {
		//
		Account account = new Account(dateSupplier);
		//
		account.deposit(Amount.valueOf("100"));
		//
		assertStatementLine(0, OperationType.DEPOSIT, dateSupplier.get(), "100", "100", account);
	}

	@Test
	public void should_new_operation_be_added_when_withdrawal() throws UnauthorizedWithdrawalException {
		Account account = new Account(dateSupplier, Balance.valueOf("150"));
		//
		account.withdraw(Amount.valueOf("20"));
		//
		assertStatementLine(1, OperationType.WITHDRAWAL, dateSupplier.get(), "20", "130", account);
	}

	@Test
	public void should_no_operation_be_added_when_rejected_withdrawal() {
		var account = new Account(dateSupplier, Balance.valueOf("150"));
		//
		assertThrows(UnauthorizedWithdrawalException.class, () -> {
			new Account(dateSupplier, Balance.valueOf("150")).withdraw(Amount.valueOf("170"));
		});
		//
		assertStatementLinesNumber(1, account);
	}

	@Test
	public void should_no_operation_be_added_when_rejected_deposit() {
		//
		Account account = new Account(dateSupplier);
		//
		assertThrows(IllegalArgumentException.class, () -> {
			account.deposit(Amount.valueOf("-20"));
		});
		assertTrue(account.statement().lines().isEmpty());
	}

	@Test
	public void should_all_operations_be_added_when_multiple_transactions() throws UnauthorizedWithdrawalException {
		Account account = new Account(dateSupplier, Balance.valueOf("150")).withdraw(Amount.valueOf("20"))
				.deposit(Amount.valueOf("200")).deposit(Amount.valueOf("200"));
		//
		assertStatementLinesNumber(4, account);
		assertStatementLine(3, OperationType.DEPOSIT, dateSupplier.get(), "200", "530", account);
	}

	@Test
	public void should_print_statement() throws UnauthorizedWithdrawalException {
		//
		String expectedStatement = "|OPERATION|DATE|AMOUNT|BALANCE|\n" + "|DEPOSIT|2023-09-01 10:00|150|150|\n"
				+ "|WITHDRAWAL|2023-09-01 10:00|20|130|\n" + "|DEPOSIT|2023-09-01 10:00|200|330|\n"
				+ "|DEPOSIT|2023-09-01 10:00|200|530|\n";
		Account account = new Account(dateSupplier, Balance.valueOf("150")).withdraw(Amount.valueOf("20"))
				.deposit(Amount.valueOf("200")).deposit(Amount.valueOf("200"));
		//
		String statement = account.statement().print(new ConsoleStatementPrinter());
		//
		assertEquals(expectedStatement, statement);
	}

	private void assertStatementLinesNumber(int expectedSize, Account account) {
		assertEquals(expectedSize, account.statement().lines().size());
	}

	private void assertStatementLine(int expectedPosition, OperationType expectedOperationType,
			LocalDateTime expectedDate, String expectedOperationAmount, String expectedBalance, Account account) {
		//
		StatementLine expectedLine = new StatementLine(
				new Operation(expectedOperationType, expectedDate, Amount.valueOf(expectedOperationAmount)),
				Balance.valueOf(expectedBalance));
		//
		assertNotNull(account.statement().lines().get(expectedPosition));
		assertEquals(expectedLine.balance(), account.statement().lines().get(expectedPosition).balance());
		assertEquals(expectedLine.operation(),
				account.statement().lines().get(expectedPosition).operation());
	}

}