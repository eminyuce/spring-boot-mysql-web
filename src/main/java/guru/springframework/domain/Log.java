package guru.springframework.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "app_logs")
@JsonIgnoreProperties
public class Log {
	private String spec_info;

	private String exception;

	private String resp_rec_time;

	private String logger;

	@Column(name = "[FROM]")
	private String FROM;

	private String nf_type;

	private String gpsi;

	private String pei;

	private String dest_ip_port;

	private String source_ip_port;
	@Id
	@GeneratedValue
	private int id;

	private String state;

	private String nf_name;

	private String nf_instance_id;

	private String data_detail;

	private String corr_id;

	private String snssai;

	private String status_description;

	private String sub_status;
	@Column(name = "[level]")
	private String level;

	private int session_id;

	private String message;

	private String supi;

	private String log_time;

	private String marker;

	private String index_par2;

	private String int_instance_id;

	private String index_par3;

	@Column(name = "[TO]")
	private String TO;

	private String module_name;

	private String req_sent_time;

	private String index_par1;
	@Column(name = "[status]")
	private String status;

	public String getSpec_info() {
		return spec_info;
	}

	public void setSpec_info(String spec_info) {
		this.spec_info = spec_info;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getResp_rec_time() {
		return resp_rec_time;
	}

	public void setResp_rec_time(String resp_rec_time) {
		this.resp_rec_time = resp_rec_time;
	}

	public String getLogger() {
		return logger;
	}

	public void setLogger(String logger) {
		this.logger = logger;
	}

	public String getFrom() {
		return FROM;
	}

	public void setFrom(String from) {
		this.FROM = from;
	}

	public String getNf_type() {
		return nf_type;
	}

	public void setNf_type(String nf_type) {
		this.nf_type = nf_type;
	}

	public String getGpsi() {
		return gpsi;
	}

	public void setGpsi(String gpsi) {
		this.gpsi = gpsi;
	}

	public String getPei() {
		return pei;
	}

	public void setPei(String pei) {
		this.pei = pei;
	}

	public String getDest_ip_port() {
		return dest_ip_port;
	}

	public void setDest_ip_port(String dest_ip_port) {
		this.dest_ip_port = dest_ip_port;
	}

	public String getSource_ip_port() {
		return source_ip_port;
	}

	public void setSource_ip_port(String source_ip_port) {
		this.source_ip_port = source_ip_port;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getNf_name() {
		return nf_name;
	}

	public void setNf_name(String nf_name) {
		this.nf_name = nf_name;
	}

	public String getNf_instance_id() {
		return nf_instance_id;
	}

	public void setNf_instance_id(String nf_instance_id) {
		this.nf_instance_id = nf_instance_id;
	}

	public String getData_detail() {
		return data_detail;
	}

	public void setData_detail(String data_detail) {
		this.data_detail = data_detail;
	}

	public String getCorr_id() {
		return corr_id;
	}

	public void setCorr_id(String corr_id) {
		this.corr_id = corr_id;
	}

	public String getSnssai() {
		return snssai;
	}

	public void setSnssai(String snssai) {
		this.snssai = snssai;
	}

	public String getStatus_description() {
		return status_description;
	}

	public void setStatus_description(String status_description) {
		this.status_description = status_description;
	}

	public String getSub_status() {
		return sub_status;
	}

	public void setSub_status(String sub_status) {
		this.sub_status = sub_status;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getSession_id() {
		return session_id;
	}

	public void setSession_id(int session_id) {
		this.session_id = session_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSupi() {
		return supi;
	}

	public void setSupi(String supi) {
		this.supi = supi;
	}

	public String getLog_time() {
		return log_time;
	}

	public void setLog_time(String log_time) {
		this.log_time = log_time;
	}

	public String getMarker() {
		return marker;
	}

	public void setMarker(String marker) {
		this.marker = marker;
	}

	public String getIndex_par2() {
		return index_par2;
	}

	public void setIndex_par2(String index_par2) {
		this.index_par2 = index_par2;
	}

	public String getInt_instance_id() {
		return int_instance_id;
	}

	public void setInt_instance_id(String int_instance_id) {
		this.int_instance_id = int_instance_id;
	}

	public String getIndex_par3() {
		return index_par3;
	}

	public void setIndex_par3(String index_par3) {
		this.index_par3 = index_par3;
	}

	public String getTO() {
		return TO;
	}

	public void setTO(String TO) {
		this.TO = TO;
	}

	public String getModule_name() {
		return module_name;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	public String getReq_sent_time() {
		return req_sent_time;
	}

	public void setReq_sent_time(String req_sent_time) {
		this.req_sent_time = req_sent_time;
	}

	public String getIndex_par1() {
		return index_par1;
	}

	public void setIndex_par1(String index_par1) {
		this.index_par1 = index_par1;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCssClass() {
		String result = "";
		switch (level) {
		case "INFO":
			result = "info";
			break;
		case "":
			result = "success";
			break;
		case "DEBUG":
			result = "warning";
			break;
		case "TRACE":
			result = "error";
			break;
		default:
			break;
		}

		return result;
	}

}
