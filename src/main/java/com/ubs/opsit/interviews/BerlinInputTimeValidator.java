package com.ubs.opsit.interviews;

public class BerlinInputTimeValidator implements InputTimeValidator {

	/**
	 * This method checks the validness of the input and returns true if valid.
	 * 
	 * @param time
	 *            input to be validated
	 * @return true if input to the program provided is valid
	 * @throws NumberFormatException
	 *             if input string can not be parsed to string
	 */
	@Override
	public Time validateInput(String time) throws NumberFormatException {
		int hrs, mins, secs;
		if (time != null) {
			String[] times = time.split(":", 3);

			hrs = Integer.parseInt(times[0]);
			mins = Integer.parseInt(times[1]);
			secs = Integer.parseInt(times[2]);

			if ((hrs >= BerlinClockConstants.HRS_LOWER_BOUND && hrs <= BerlinClockConstants.HRS_UPPER_BOUND)
					&& (mins >= BerlinClockConstants.MINS_LOWER_BOUND && mins <= BerlinClockConstants.MINS_UPPER_BOUND)
					&& (secs >= BerlinClockConstants.SECS_LOWER_BOUND
							&& secs <= BerlinClockConstants.SECS_UPPER_BOUND)) {
				return new Time(hrs, mins, secs);
			}
		}
		return null;

	}

}
