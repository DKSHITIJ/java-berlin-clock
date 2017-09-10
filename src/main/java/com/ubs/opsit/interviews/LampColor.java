package com.ubs.opsit.interviews;

public enum LampColor {
	RED_LIGHT('R'), YELLOW_LIGHT('Y'), OFF('O');

	private char value;

	private LampColor(char s) {
		value = s;
	}

	public char getValue() {
		return value;
	}
}
