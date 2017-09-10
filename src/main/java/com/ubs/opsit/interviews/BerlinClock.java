package com.ubs.opsit.interviews;

import java.util.ArrayList;
import java.util.List;

public class BerlinClock implements TimeConverter {

	int hrs, mins, secs = 0;
	private Lamp secondsLamp;
	private List<Lamp> firstHoursLamp = new ArrayList<>(BerlinClockConstants.FIRST_HOURS_ROW_SIZE);
	private List<Lamp> secondHoursLamp = new ArrayList<>(BerlinClockConstants.SECOND_HOURS_ROW_SIZE);
	private List<Lamp> firstMinsLamp = new ArrayList<>(BerlinClockConstants.FIRST_MINS_ROW_SIZE);
	private List<Lamp> secondMinsLamp = new ArrayList<>(BerlinClockConstants.SECOND_MINS_ROW_SIZE);

	/**
	 * This is the method to convert input into the Berlin clock format. 
	 * This method perform 4 steps 
	 * Step 1 : Validate input 
	 * Step 2 : Initialize all the lamps of the Berlin clock with off state. 
	 * Step 3 : Update individual row with the lamp color light based on the time. 
	 * Step 4 : build the results and return.
	 * 
	 * @param time
	 * @return
	 */
	public String convertTime(String aTime) {
		try {
			// step 1 : validate
			if (!isValidInput(aTime)) {
				return "Invalid Input";
			}
		} catch (NumberFormatException e) {
			return "Invalid Input : " + e.getMessage();
		}

		// Step 2 : Initialize clock
		initializeLampsForClock();

		// Step 3 : update Berlin Clock
		updateSeconds();
		updateFirstHoursRowLamps();
		updateSecondHoursRowLamps();
		updateFirstMinsRowLamps();
		updatetSecondMinsRowLamps();

		// Step 4 : get results
		return getResults();
	}

	/**
	 * This method checks the validness of the input and returns true if valid.
	 * 
	 * @param time input to be validated
	 * @return true if input to the program provided is valid
	 * @throws NumberFormatException if input string can not be parsed to string
	 */
	public boolean isValidInput(String time) throws NumberFormatException {
		if (time != null) {
			String[] times = time.split(":", 3);

			hrs = Integer.parseInt(times[0]);
			mins = Integer.parseInt(times[1]);
			secs = Integer.parseInt(times[2]);

			if ((hrs >= BerlinClockConstants.HRS_LOWER_BOUND && hrs <= BerlinClockConstants.HRS_UPPER_BOUND)
					&& (mins >= BerlinClockConstants.MINS_LOWER_BOUND && mins <= BerlinClockConstants.MINS_UPPER_BOUND)
					&& (secs >= BerlinClockConstants.SECS_LOWER_BOUND
							&& secs <= BerlinClockConstants.SECS_UPPER_BOUND)) {
				return true;
			}
		}
		return false;
	}

	// clock is initialized with all its lamp with off state, ie with no light color
	public void initializeLampsForClock() {
		secondsLamp = new Lamp(0, 0);

		firstHoursLamp.clear();
		for (int i = 0; i < BerlinClockConstants.FIRST_HOURS_ROW_SIZE; i++) {
			firstHoursLamp.add(new Lamp(1, i));
		}

		secondHoursLamp.clear();
		for (int i = 0; i < BerlinClockConstants.SECOND_HOURS_ROW_SIZE; i++) {
			secondHoursLamp.add(new Lamp(2, i));
		}

		firstMinsLamp.clear();
		for (int i = 0; i < BerlinClockConstants.FIRST_MINS_ROW_SIZE; i++) {
			firstMinsLamp.add(new Lamp(3, i));
		}

		secondMinsLamp.clear();
		for (int i = 0; i < BerlinClockConstants.SECOND_MINS_ROW_SIZE; i++) {
			secondMinsLamp.add(new Lamp(4, i));
		}
	}

	/*
	 * Update the first row lamp with the seconds representation.
	 */
	public void updateSeconds() {
		if (secs % 2 == 0) {
			secondsLamp.setLampColor(LampColor.YELLOW_LIGHT);
		}
	}

	/*
	 * Update the seconds row lamps with the hours representation.
	 * Hours are represented by the lamps in two rows.
	 * This is the first row of it
	 */
	public void updateFirstHoursRowLamps() {
		updateLampForRow(hrs / 5, firstHoursLamp, LampColor.RED_LIGHT);
	}

	/*
	 * Update the seconds row lamps with the hours representation.
	 * Hours are represented by the lamps in two rows.
	 * This is the second row of it
	 */
	public void updateSecondHoursRowLamps() {
		updateLampForRow(hrs % 5, secondHoursLamp, LampColor.RED_LIGHT);
	}

	/*
	 * Update the seconds row lamps with the minutes representation.
	 * minutes are represented by the lamps in two rows.
	 * This is the first row of it
	 */
	public void updateFirstMinsRowLamps() {
		updateLampForRow(mins / 5, firstMinsLamp, LampColor.YELLOW_LIGHT);

		if (mins >= BerlinClockConstants.FIRST_QUARTER) {
			firstMinsLamp.get((BerlinClockConstants.FIRST_QUARTER / 5) - 1).setLampColor(LampColor.RED_LIGHT);
		}
		if (mins >= BerlinClockConstants.SECOND_QUARTER) {
			firstMinsLamp.get((BerlinClockConstants.SECOND_QUARTER / 5) - 1).setLampColor(LampColor.RED_LIGHT);
		}
		if (mins >= BerlinClockConstants.THIRD_QUARTER) {
			firstMinsLamp.get((BerlinClockConstants.THIRD_QUARTER / 5) - 1).setLampColor(LampColor.RED_LIGHT);
		}
	}

	/*
	 * Update the seconds row lamps with the minutes representation.
	 * minutes are represented by the lamps in two rows.
	 * This is the second row of it.
	 */
	public void updatetSecondMinsRowLamps() {
		updateLampForRow(mins % 5, secondMinsLamp, LampColor.YELLOW_LIGHT);
	}

	private void updateLampForRow(int onLights, List<Lamp> lamp, LampColor lampColor) {
		for (int i = 0; i < onLights; i++) {
			lamp.get(i).setLampColor(lampColor);
		}
	}

	private String getResults() {
		StringBuilder berlinClockTime = new StringBuilder();

		berlinClockTime.append(secondsLamp.getLampColor().getValue());
		berlinClockTime.append(BerlinClockConstants.NEW_LINE);

		buildResultsForRow(firstHoursLamp, berlinClockTime, false);
		buildResultsForRow(secondHoursLamp, berlinClockTime, false);
		buildResultsForRow(firstMinsLamp, berlinClockTime, false);
		
		buildResultsForRow(secondMinsLamp, berlinClockTime, true);

		return berlinClockTime.toString();
	}

	private void buildResultsForRow(List<Lamp> lamps, StringBuilder berlinClockTime, boolean ignoreNewLine) {
		for (Lamp lamp : lamps) {
			berlinClockTime.append(lamp.getLampColor().getValue());
		}
		if (!ignoreNewLine) {
			berlinClockTime.append(BerlinClockConstants.NEW_LINE);
		}
	}

	public Lamp getSecondsLamp() {
		return secondsLamp;
	}

	public List<Lamp> getFirstHoursLamp() {
		return firstHoursLamp;
	}

	public List<Lamp> getSecondHoursLamp() {
		return secondHoursLamp;
	}

	public List<Lamp> getFirstMinsLamp() {
		return firstMinsLamp;
	}

	public List<Lamp> getSecondMinsLamp() {
		return secondMinsLamp;
	}
}