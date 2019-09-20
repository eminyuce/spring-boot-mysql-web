package i2i.n5g.logs.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import i2i.n5g.logs.domain.Log;
import i2i.n5g.logs.services.FileService;

@Component
public class LogRowMapper implements RowMapper<Log> {
	
	@Autowired
	@Qualifier("fileService")
	FileService fileService;

	@Override
	public Log mapRow(ResultSet rs, int rowNum) throws SQLException {
		Map<String, String> obstructionMap =	fileService.getObstructionMap();
		Log emp = new Log();
		emp.setId(rs.getInt("id"));
		emp.setFrom(rs.getString("from"));
		emp.setTo(rs.getString("to"));
		emp.setCorr_id(rs.getString("corr_id"));
		emp.setData_detail(rs.getString("data_detail"));
		emp.setDest_ip_port(rs.getString("dest_ip_port"));
		emp.setException(rs.getString("exception"));
		emp.setGpsi(rs.getString("gpsi"));
		emp.setIndex_par1(rs.getString("index_par1"));
		emp.setIndex_par2(rs.getString("index_par2"));
		emp.setIndex_par3(rs.getString("index_par3"));
		emp.setInt_instance_id(rs.getString("int_instance_id"));
		emp.setLevel(rs.getString("level"));
		emp.setLog_time(rs.getString("log_time"));
		emp.setLogger(rs.getString("logger"));
		emp.setMarker(rs.getString("marker"));
		emp.setMessage(rs.getString("message"));
		emp.setModule_name(rs.getString("module_name"));
		emp.setNf_instance_id(rs.getString("nf_instance_id"));
		emp.setNf_name(rs.getString("nf_name"));
		emp.setNf_type(rs.getString("nf_type"));
		emp.setPei(rs.getString("pei"));
		emp.setReq_sent_time(rs.getString("req_sent_time"));
		emp.setResp_rec_time(rs.getString("resp_rec_time"));
		emp.setSession_id(rs.getString("session_id"));
		emp.setSnssai(rs.getString("snssai"));
		emp.setSource_ip_port(rs.getString("source_ip_port"));
		emp.setSpec_info(rs.getString("spec_info"));
		emp.setState(rs.getString("state"));
		emp.setStatus(rs.getString("status"));
		emp.setStatus_description(rs.getString("status_description"));
		emp.setSub_status(rs.getString("sub_status"));
		emp.setSupi(rs.getString("supi"));
		emp.setHttp_message(rs.getString("http_message"));
	
		return emp;
	}

}
