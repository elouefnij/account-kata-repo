package com.sfeir.bankaccount.infrastructure;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import com.sfeir.bankaccount.business.Statement;
import com.sfeir.bankaccount.business.StatementPrinter;

public class ConsoleStatementPrinter implements StatementPrinter {
	private static final String LINE_FORMAT = "|%s|%s|%s|%s|\n";
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	private static final String HEADER = "|OPERATION|DATE|AMOUNT|BALANCE|\n";

	@Override
	public String print(Statement statement) {
		StringBuilder stringBuilder = new StringBuilder(HEADER);

		String formatedLines = statement.lines().stream().map(l -> String.format(LINE_FORMAT, l.operation().type(),
				l.operation().date().format(DATE_FORMATTER), l.operation().amount().value(), l.balance().value()))
				.collect(Collectors.joining());
		stringBuilder.append(formatedLines);

		return stringBuilder.toString();
	}
}
