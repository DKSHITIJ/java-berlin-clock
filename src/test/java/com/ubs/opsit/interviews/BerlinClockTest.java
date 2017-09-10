package com.ubs.opsit.interviews;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

public class BerlinClockTest {

	BerlinClock berlinClock;
	@Before
	public void setUp() throws Exception {
		berlinClock = new BerlinClock();
	}

	@Test
	public void testIsValidInput() {
		Assert.assertFalse(berlinClock.isValidInput("25:00:00"));
		Assert.assertFalse(berlinClock.isValidInput("23:65:00"));
		Assert.assertFalse(berlinClock.isValidInput("22:00:65"));
		Assert.assertTrue(berlinClock.isValidInput("22:00:00"));
	}

	@Test
	public void testUpdateSeconds_YellowLamp() {
		berlinClock.convertTime("10:10:10");
		berlinClock.getSecondsLamp();
		Assert.assertEquals(new Lamp(0, 0, LampColor.YELLOW_LIGHT), berlinClock.getSecondsLamp());
	}
	
	@Test
	public void testUpdateSeconds_NoLightLamp() {
		berlinClock.convertTime("10:10:11");
		Assert.assertEquals(new Lamp(0, 0, LampColor.OFF), berlinClock.getSecondsLamp());
	}

	@Test
	public void testUpdateFirstHoursRowLamps_WithSomeRed() {
		berlinClock.convertTime("10:10:11");
		Assert.assertEquals(new Lamp(1, 0, LampColor.RED_LIGHT), berlinClock.getFirstHoursLamp().get(0));
		Assert.assertEquals(new Lamp(1, 1, LampColor.RED_LIGHT), berlinClock.getFirstHoursLamp().get(1));
		Assert.assertEquals(new Lamp(1, 2, LampColor.OFF), berlinClock.getFirstHoursLamp().get(2));
		Assert.assertEquals(new Lamp(1, 3, LampColor.OFF), berlinClock.getFirstHoursLamp().get(3));
	}
	
	@Test
	public void testUpdateFirstHoursRowLamps_WithAllRed() {
		berlinClock.convertTime("24:10:11");
		int i =0;
		for(Lamp lamp : berlinClock.getFirstHoursLamp()) {
			Assert.assertEquals(new Lamp(1, i++, LampColor.RED_LIGHT), lamp);
		}
	}
	
	@Test
	public void testUpdateFirstHoursRowLamps_WithAllOff() {
		berlinClock.convertTime("00:10:11");
		int i =0;
		for(Lamp lamp : berlinClock.getFirstHoursLamp()) {
			Assert.assertEquals(new Lamp(1, i++, LampColor.OFF), lamp);
		}
	}

	@Test
	public void testUpdateSecondHoursRowLamps_WithSomeRed() {
		berlinClock.convertTime("12:10:11");
		Assert.assertEquals(new Lamp(2, 0, LampColor.RED_LIGHT), berlinClock.getSecondHoursLamp().get(0));
		Assert.assertEquals(new Lamp(2, 1, LampColor.RED_LIGHT), berlinClock.getSecondHoursLamp().get(1));
		Assert.assertEquals(new Lamp(2, 2, LampColor.OFF), berlinClock.getSecondHoursLamp().get(2));
		Assert.assertEquals(new Lamp(2, 3, LampColor.OFF), berlinClock.getSecondHoursLamp().get(3));
	}

	@Test
	public void testUpdateSecondHoursRowLamps_WithAllRed() {
		berlinClock.convertTime("14:10:11");
		int i =0;
		for(Lamp lamp : berlinClock.getSecondHoursLamp()) {
			Assert.assertEquals(new Lamp(2, i++, LampColor.RED_LIGHT), lamp);
		}
	}
	
	@Test
	public void testUpdateSecondHoursRowLamps_WithAllOff() {
		berlinClock.convertTime("00:10:11");
		int i =0;
		for(Lamp lamp : berlinClock.getFirstHoursLamp()) {
			Assert.assertEquals(new Lamp(1, i++, LampColor.OFF), lamp);
		}
	}
	
	@Test
	public void testUpdateFirstMinsRowLamps_WithYellow() {
		berlinClock.convertTime("14:10:11");
		Assert.assertEquals(new Lamp(3, 0, LampColor.YELLOW_LIGHT), berlinClock.getFirstMinsLamp().get(0));
		Assert.assertEquals(new Lamp(3, 1, LampColor.YELLOW_LIGHT), berlinClock.getFirstMinsLamp().get(1));

		for (int i = 0; i < berlinClock.getFirstMinsLamp().size(); i++) {
			if (i >= 2) {
				Assert.assertEquals(new Lamp(3, i, LampColor.OFF), berlinClock.getFirstMinsLamp().get(i));
			}
		}
	}
	
	@Test
	public void testUpdateFirstMinsRowLamps_WithRed() {
		berlinClock.convertTime("14:46:11");
		Assert.assertEquals(new Lamp(3, 2, LampColor.RED_LIGHT), berlinClock.getFirstMinsLamp().get(2));
		Assert.assertEquals(new Lamp(3, 5, LampColor.RED_LIGHT), berlinClock.getFirstMinsLamp().get(5));
		Assert.assertEquals(new Lamp(3, 8, LampColor.RED_LIGHT), berlinClock.getFirstMinsLamp().get(8));
	}

	@Test
	public void testUpdatetSecondMinsRowLamps() {
		berlinClock.convertTime("14:48:11");
		Assert.assertEquals(new Lamp(4, 0, LampColor.YELLOW_LIGHT), berlinClock.getSecondMinsLamp().get(0));
		Assert.assertEquals(new Lamp(4, 1, LampColor.YELLOW_LIGHT), berlinClock.getSecondMinsLamp().get(1));
		Assert.assertEquals(new Lamp(4, 2, LampColor.YELLOW_LIGHT), berlinClock.getSecondMinsLamp().get(2));
	}
}
