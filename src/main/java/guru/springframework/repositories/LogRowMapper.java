package guru.springframework.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Log;

@Component
public class LogRowMapper implements RowMapper<Log> {

	@Override
	public Log mapRow(ResultSet rs, int rowNum) throws SQLException {
		Log emp = new Log();
		emp.setId(rs.getInt("id"));
		return emp;
	}

}
