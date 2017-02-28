package app.common;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = { PageableHandlerMethodArgumentResolver.class, MockServletContext.class })
public abstract class AbstractRestControllerTest {

	@Autowired
	protected WebApplicationContext webApplicationContext;

	protected MockMvc mockMvc;

	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	protected byte[] convertObjectToJsonBytes(final Object object) throws IOException {
		final ObjectMapper mapper = new ObjectMapper();
		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
		mapper.registerModule(new JavaTimeModule());
		mapper.setDateFormat(dateFormat);
		return mapper.writeValueAsBytes(object);
	}

}

