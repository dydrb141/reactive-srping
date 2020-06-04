package com.reacitve.chapter2.template;

import lombok.Getter;

@Getter
public final class Temperature {
	private final double value;

	public Temperature(double value) {
		this.value = value;
	}
}
