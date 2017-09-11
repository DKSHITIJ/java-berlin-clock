package com.ubs.opsit.interviews;

public class BerlinClockTimeConverter implements TimeConverter {

	Clock berlinClock = null;

	/**
	 * This is the method to convert input into the Berlin clock format. 
	 * This method perform 4 steps 
	 * Step 1 : Validate input. 
	 * Step 2 : Initialize Berlin Clock with hours, minutes, seconds. 
	 * Step 3 : get the time from the clock. 
	 * @param time
	 * @return
	 */
	public String convertTime(String aTime) {
		Time time = null;
		try {
			// step 1 : validate
			InputTimeValidator inputTimeValidator = new BerlinInputTimeValidator();
			time = inputTimeValidator.validateInput(aTime);
			if (time == null) {
				return "Invalid Input";
			}
		} catch (NumberFormatException e) {
			return "Invalid Input : " + e.getMessage();
		}

		// Step 2 : Initialize clock
		if (berlinClock == null) {
			berlinClock = new BerlinClock();
		}

		// Step 3 : update clock based on the actual time
		return berlinClock.getTime(time);

	}
}