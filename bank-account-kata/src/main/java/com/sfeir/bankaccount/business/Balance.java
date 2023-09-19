package com.sfeir.bankaccount.business;

import java.math.BigDecimal;

public record Balance(BigDecimal value) {

	public static final Balance INITIAL_BALANCE = new Balance(BigDecimal.ZERO);

	public Balance {
		if (value.signum() == -1)
			throw new IllegalArgumentException("Balance must not be negative");
	}

	public Balance add(BigDecimal value_to_add) {
		return new Balance(value.add(value_to_add));
	}

	public Balance subtract(BigDecimal value_to_substract) {
		return new Balance(value.subtract(value_to_substract));
	}

	public static Balance valueOf(String value) {
		return new Balance(new BigDecimal(value));
	}

}
