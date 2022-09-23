package com.fabrick.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest
class TestApplicationTests {

	@SpyBean
	TestApplication testApplication;

	@Test
	void contextLoads() {
		testApplication.main(new String[]{});
	}

}
