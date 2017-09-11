package com.ubs.opsit.interviews;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BerlinClockTimeConverterTest {

	BerlinClockTimeConverter berlinClockConverter;
	
	@Before
	public void setUp() throws Exception {
		berlinClockConverter = new BerlinClockTimeConverter();
	}
	
	@Test
	public void testConvertTimeAtNoon() {
		String result = "Y\r\n"
				+ "RROO\r\n"
				+ "RROO\r\n"
				+ "OOOOOOOOOOO\r\n"
				+ "OOOO";
		Assert.assertEquals(result, berlinClockConverter.convertTime("12:00:00"));
	}
	
	@Test
	public void testConvertTimeAtMorning() {
		String result = "Y\r\n"
				+ "ROOO\r\n"
				+ "RRRR\r\n"
				+ "YYRYYRYYROO\r\n"
				+ "OOOO";
		Assert.assertEquals(result, berlinClockConverter.convertTime("09:45:00"));
	}
	
	@Test
	public void testConvertTimeAtMidNight() {
		String result = "O\r\n"
				+ "OOOO\r\n"
				+ "RROO\r\n"
				+ "YOOOOOOOOOO\r\n"
				+ "OOOO";
		Assert.assertEquals(result, berlinClockConverter.convertTime("02:05:01"));
	}
}
