package guru.springframework.repositories;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import guru.springframework.domain.Log;
import guru.springframework.domain.MyTestDummy;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LogRepositoryTest {

	@Autowired
	private LogRepository logRepository;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void readJsonFile2() {
		String resourceName = "C:\\Users\\YUCE\\Desktop\\VirtualMachineSharedFolder\\log2.json";
		String content;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			content = new String(Files.readAllBytes(Paths.get(resourceName)));
			MyTestDummy car = objectMapper.readValue(content, MyTestDummy.class);
			for (Log l : car.getLog()) {
				l.setTO("");
			}
			logRepository.saveAll(car.getLog());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void readJsonFile() {
		String resourceName = "C:\\Users\\YUCE\\Desktop\\VirtualMachineSharedFolder\\log2.json";
		Gson gsonRead = new Gson();
		Reader reader = null;
		try {
			reader = new FileReader(resourceName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Gson gson = new GsonBuilder().registerTypeAdapter(Double.class, new BadDoubleDeserializer()).create();
		MyTestDummy asiento = gson.fromJson(reader, MyTestDummy.class);

	}

	public static class BadDoubleDeserializer implements JsonDeserializer<Double> {

		@Override
		public Double deserialize(JsonElement element, Type type, JsonDeserializationContext context)
				throws JsonParseException {
			try {
				return Double.parseDouble(element.getAsString().replace(',', '.'));
			} catch (NumberFormatException e) {
				throw new JsonParseException(e);
			}
		}

	}
}
