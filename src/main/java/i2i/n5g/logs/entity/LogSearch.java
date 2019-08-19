package i2i.n5g.logs.entity;

import java.util.List;

public class LogSearch {

	public String search;
	private List<NfType> nfTypes;
	private List<N5gLogLevel> logLevels;
	private int logLimit;
	private List<NfType> fromNfTypesList;
	private List<NfType> toNfTypesList;
	private String sql;
	private String supi;
	private String snssai;
	private String httpStatus;
	private String destinationIp;
	private String sourceIp;
	private String searchBtn;
	private String dataSource;
	private String sqlExcluded;

	public LogSearch() {
		search = "";
		logLimit = 1200;
		httpStatus = "";
		supi = "";
		snssai = "";
		destinationIp = "";
		sourceIp = "";
		searchBtn = "";
		sqlExcluded = "";
		dataSource = "test";
	}

	public List<NfType> getFromNfTypesList() {
		return fromNfTypesList;
	}

	public void setFromNfTypesList(List<NfType> fromNfTypesList) {
		this.fromNfTypesList = fromNfTypesList;
	}

	public List<NfType> getToNfTypesList() {
		return toNfTypesList;
	}

	public void setToNfTypesList(List<NfType> toNfTypesList) {
		this.toNfTypesList = toNfTypesList;
	}

	public List<N5gLogLevel> getLogLevels() {
		return logLevels;
	}

	public void setLogLevels(List<N5gLogLevel> logLevels) {
		this.logLevels = logLevels;
	}

	public List<NfType> getNfTypes() {
		return nfTypes;
	}

	public void setNfTypes(List<NfType> nfTypes) {
		this.nfTypes = nfTypes;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getLogLimit() {
		return logLimit;
	}

	public void setLogLimit(int logLimit) {
		this.logLimit = logLimit;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getSupi() {
		return supi;
	}

	public void setSupi(String supi) {
		this.supi = supi;
	}

	public String getSnssai() {
		return snssai;
	}

	public void setSnssai(String snssai) {
		this.snssai = snssai;
	}

	public String getDestinationIp() {
		return destinationIp;
	}

	public void setDestinationIp(String destinationIp) {
		this.destinationIp = destinationIp;
	}

	public String getSourceIp() {
		return sourceIp;
	}

	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}

	public String getSearchBtn() {
		return searchBtn;
	}

	public void setSearchBtn(String searchBtn) {
		this.searchBtn = searchBtn;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getSqlExcluded() {
		return sqlExcluded;
	}

	public void setSqlExcluded(String sqlExcluded) {
		this.sqlExcluded = sqlExcluded;
	}

}
