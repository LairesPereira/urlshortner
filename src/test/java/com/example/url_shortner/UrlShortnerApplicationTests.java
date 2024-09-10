package com.example.url_shortner;

import com.example.url_shortner.models.URL;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UrlShortnerApplicationTests {
	URL emptyUrl = new URL();
	URL url = new URL("Some random url");

	@Test
	void testEmptyUrlEncodedSizeMustBeZero() {
		String urlSize = emptyUrl.getEncodedUrl();
		assertNull(urlSize);
	}
	@Test
	void testEncodedUrlSize() {
		int urlSize = url.getEncodedUrl().length();
		assertEquals(4, urlSize);
	}
}
