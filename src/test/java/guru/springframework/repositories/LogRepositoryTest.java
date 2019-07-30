package guru.springframework.repositories;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
	public void readJsonFile2() throws ParseException, JSONException, IllegalAccessException {
		String resourceName = "C:\\Users\\YUCE\\Desktop\\VirtualMachineSharedFolder\\log2.json";

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(resourceName));
			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				// read next line
				line = reader.readLine();
				Log log = new Log();

				if (line.contains("id")) {
					Map<String, String> map = new HashMap<String, String>();
					String test = line;
					String[] test1 = test.split(",");

					for (String s : test1) {
						String[] t = s.split(":");
						if (t.length == 2)
							map.put(t[0].replace("{\"", "").replace("\"", "").trim(), t[1].replace("\"", "").trim());

					}

					Field[] fields = FieldUtils.getAllFields(log.getClass());
					for (Field field : fields) {
						String s = field.getName();
						if (s.equalsIgnoreCase("id")) {
							System.out.println(s + "  " + map.get(s));
							FieldUtils.writeField(log, s, new Random().nextInt(50000), true);
						} else if (s.equalsIgnoreCase("session_id")) {
							System.out.println(s + "  " + map.get(s));
							FieldUtils.writeField(log, s, new Random().nextInt(50000), true);
						} else {
							FieldUtils.writeField(log, s, "", true);
						}
					}
					for (String s : map.keySet()) {
						try {
							if (s.equalsIgnoreCase("id")) {
								System.out.println(s + "  " + map.get(s));
								FieldUtils.writeField(log, s, new Random().nextInt(50000), true);
							} else if (s.equalsIgnoreCase("session_id")) {
								System.out.println(s + "  " + map.get(s));
								FieldUtils.writeField(log, s, new Random().nextInt(50000), true);
							} else {
								FieldUtils.writeField(log, s, map.get(s), true);
							}
						} catch (Exception e) {
							// TODO: handle exception
						}

					}

					logRepository.save(log);
					System.out.println(log);
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
