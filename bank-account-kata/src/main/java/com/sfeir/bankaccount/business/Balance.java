package com.sfeir.bankaccount.business;

import java.math.BigDecimal;

public record Balance(BigDecimal value) {

	public static final Balance INITIAL_BALANCE = new Balance(BigDecimal.ZERO);

	public Balance(BigDecimal value) {
		if (value.signum() == -1)
			throw new IllegalArgumentException("Balance must not be negative");
		this.value = value;

	}

}
