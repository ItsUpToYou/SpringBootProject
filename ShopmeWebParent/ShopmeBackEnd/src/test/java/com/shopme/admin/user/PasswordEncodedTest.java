package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncodedTest {
	@Test
	public void testEncodePassword() {
		BCryptPasswordEncoder bcEncrypt = new BCryptPasswordEncoder();
		String rawPass = "test123";
		String encodedPass = bcEncrypt.encode(rawPass);

		boolean matches = bcEncrypt.matches(rawPass, encodedPass);
		assertThat(matches).isTrue();
	}
}