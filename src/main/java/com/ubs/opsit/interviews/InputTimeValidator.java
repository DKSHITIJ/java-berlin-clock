package com.ubs.opsit.interviews;

public interface InputTimeValidator {

	/**
	 * This method checks the validness of the input and returns true if valid.
	 * 
	 * @param time
	 *            input to be validated
	 * @return true if input to the program provided is valid
	 * @throws NumberFormatException
	 *             if input string can not be parsed to string
	 */
	public Time validateInput(String time);
}
