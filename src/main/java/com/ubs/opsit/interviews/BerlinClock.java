package com.ubs.opsit.interviews;

import java.util.ArrayList;
import java.util.List;

public class BerlinClock implements Clock {

	int hrs, mins, secs = 0;
	private Lamp secondsLamp;
	private List<Lamp> firstHoursLamp = new ArrayList<>(BerlinClockConstants.FIRST_HOURS_ROW_SIZE);
	private List<Lamp> secondHoursLamp = new ArrayList<>(BerlinClockConstants.SECOND_HOURS_ROW_SIZE);
	private List<Lamp> firstMinsLamp = new ArrayList<>(BerlinClockConstants.FIRST_MINS_ROW_SIZE);
	private List<Lamp> secondMinsLamp = new ArrayList<>(BerlinClockConstants.SECOND_MINS_ROW_SIZE);

	public String getTime(Time time) {
		this.hrs = time.getHours();
		this.mins = time.getMinutes();
		this.secs = time.getSeconds();

		reset();
		updateSeconds();
		updateFirstHoursRowLamps();
		updateSecondHoursRowLamps();
		updateFirstMinsRowLamps();
		updateSecondMinsRowLamps();

		return getResults();
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

	/*
	 * Update the first row lamp with the seconds representation.
	 */
	public void updateSeconds() {
		if (secs % 2 == 0) {
			secondsLamp.setLampColor(LampColor.YELLOW_LIGHT);
		}
	}

	/*
	 * Update the seconds row lamps with the hours representation. Hours are
	 * represented by the lamps in two rows. This is the first row of it
	 */
	public void updateFirstHoursRowLamps() {
		updateLampForRow(hrs / 5, firstHoursLamp, LampColor.RED_LIGHT);
	}

	/*
	 * Update the seconds row lamps with the hours representation. Hours are
	 * represented by the lamps in two rows. This is the second row of it
	 */
	public void updateSecondHoursRowLamps() {
		updateLampForRow(hrs % 5, secondHoursLamp, LampColor.RED_LIGHT);
	}

	/*
	 * Update the seconds row lamps with the minutes representation. minutes are
	 * represented by the lamps in two rows. This is the first row of it
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
	 * Update the seconds row lamps with the minutes representation. minutes are
	 * represented by the lamps in two rows. This is the second row of it.
	 */
	public void updateSecondMinsRowLamps() {
		updateLampForRow(mins % 5, secondMinsLamp, LampColor.YELLOW_LIGHT);
	}

	private void reset() {
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

	private void updateLampForRow(int onLights, List<Lamp> lamp, LampColor lampColor) {
		for (int i = 0; i < onLights; i++) {
			lamp.get(i).setLampColor(lampColor);
		}
	}

	private String getResults() {
		StringBuilder time = new StringBuilder();

		time.append(secondsLamp.getLampColor().getValue());
		time.append(BerlinClockConstants.NEW_LINE);

		buildResultsForRow(firstHoursLamp, time, false);
		buildResultsForRow(secondHoursLamp, time, false);

		buildResultsForRow(firstMinsLamp, time, false);
		buildResultsForRow(secondMinsLamp, time, true);

		return time.toString();
	}

	private void buildResultsForRow(List<Lamp> lamps, StringBuilder berlinClockTime, boolean ignoreNewLine) {
		for (Lamp lamp : lamps) {
			berlinClockTime.append(lamp.getLampColor().getValue());
		}
		if (!ignoreNewLine) {
			berlinClockTime.append(BerlinClockConstants.NEW_LINE);
		}
	}

}
