package com.sfeir.bankaccount.business;

import java.math.BigDecimal;

public record Balance(BigDecimal value) {
	public static final Balance INITIAL_BALANCE = new Balance(BigDecimal.ZERO);

}
