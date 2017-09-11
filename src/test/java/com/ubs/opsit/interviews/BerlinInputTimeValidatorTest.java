package com.ubs.opsit.interviews;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BerlinInputTimeValidatorTest {

	BerlinInputTimeValidator berlinInputTimeValidator = null;

	@Before
	public void setUp() throws Exception {
		berlinInputTimeValidator = new BerlinInputTimeValidator();
	}

	@Test
	public void testValidateInputHighHours() {
		Assert.assertNull(berlinInputTimeValidator.validateInput("25:00:00"));
	}

	@Test
	public void testValidateInputHighSecs() {
		Assert.assertNull(berlinInputTimeValidator.validateInput("23:00:65"));
	}

	@Test
	public void testValidateInputHighMins() {
		Assert.assertNull(berlinInputTimeValidator.validateInput("23:65:00"));
	}

	@Test
	public void testValidateInputWithSuccess() {
		Assert.assertEquals(new Time(22, 00, 00), berlinInputTimeValidator.validateInput("22:00:00"));
	}

}
