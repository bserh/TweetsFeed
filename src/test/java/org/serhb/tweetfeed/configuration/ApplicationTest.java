package org.serhb.tweetfeed.configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;

/**
 * Provides both of the junit and integration tests to check the server's work
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
@DirtiesContext
public class ApplicationTest {
    @Value("${local.server.port}")
    private int port;

    /**
     * Tests connection to the server with simple head request
     *
     * @throws Exception
     */
    @Test
    public void testHeadRequestToServer() throws Exception {
        @SuppressWarnings("rawtypes")
        ResponseEntity<String> entity = new TestRestTemplate()
                .getForEntity(getTestURL(), String.class);

        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    /**
     * Returns test URL for requests
     *
     * @return test URL
     */
    public String getTestURL() {
        return "http://localhost:" + this.port;
    }
}
