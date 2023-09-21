package com.sfeir.bankaccount.business;

import java.math.BigDecimal;

public record Balance(BigDecimal value) {

	public static final Balance INITIAL_BALANCE = new Balance(BigDecimal.ZERO);

	public Balance {
		if (value.signum() == -1)
			throw new IllegalArgumentException("Balance must not be negative");
	}

	public Balance add(BigDecimal value) {
		return new Balance(this.value.add(value));
	}

	public Balance subtract(BigDecimal value) {
		return new Balance(this.value.subtract(value));
	}

	public static Balance valueOf(String value) {
		return new Balance(new BigDecimal(value));
	}

}
