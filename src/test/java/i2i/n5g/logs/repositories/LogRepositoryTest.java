package i2i.n5g.logs.repositories;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import i2i.n5g.logs.domain.Log;
import i2i.n5g.logs.utils.DateTimeUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LogRepositoryTest {

	@Autowired
	private LogRepository logRepository;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void parseDate() throws ParseException, JSONException, IllegalAccessException, java.text.ParseException {
		String strDate = "2019-08-06 17:13:28";
		System.out.println(DateTimeUtils.getDateTimeFormatted(strDate));

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		Date dateStr = formatter.parse(strDate);
		String formattedDate = formatter.format(dateStr);
		System.out.println("yyyy-MM-dd date is ==>" + formattedDate);
		Date date1 = formatter.parse(formattedDate);
//Cannot convert string '20190802063652' to java.sql.Date value
		formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		formattedDate = formatter.format(date1);
		System.out.println("dd-MMM-yyyy date is ==>" + formattedDate);

		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(formatter.format(date1), formatter2);
		LocalDate toDateTime = LocalDate.now();
		long minutes = dateTime.until(toDateTime, ChronoUnit.MINUTES);

		System.out.println("minutes ==>" + minutes);

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

					// logRepository.save(log);
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
