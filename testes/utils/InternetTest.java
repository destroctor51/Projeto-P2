package utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class InternetTest {

	@Test
	public void test() {
		assertTrue(Internet.isEmailValido("abc@def.ghi"));
		assertTrue(Internet.isEmailValido("email@mail.com"));
		assertTrue(Internet.isEmailValido("admin@domain.com"));
		assertTrue(Internet.isEmailValido("teste@gmail.net"));
		assertTrue(Internet.isEmailValido("gmail@outlook.net"));

		assertFalse(Internet.isEmailValido("abc@def@ghi"));
		assertFalse(Internet.isEmailValido("a@b.c"));
		assertFalse(Internet.isEmailValido("email.invalido"));
		assertFalse(Internet.isEmailValido("admin@senha"));
		assertFalse(Internet.isEmailValido("!#%$@*(!*.)()"));
	}

}
