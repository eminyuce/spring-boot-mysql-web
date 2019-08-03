package i2i.n5g.logs.entity;

import java.util.List;

import i2i.n5g.logs.domain.Log;


public class LogSearch {
	public String search;
	private List<NfType> nfTypes;
	private List<N5gLogLevel> logLevels;
	private String messageExcluded;
	private String dataDetailExcluded;
	private int logLimit;
	private List<NfType> fromNfTypesList;
	private List<NfType> toNfTypesList;
	private String sql;
	private String httpStatus;
	
	public LogSearch() {
		search = "";
		logLimit = 3000;
		httpStatus="";
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

	public String getMessageExcluded() {
		return messageExcluded;
	}

	public void setMessageExcluded(String messageExcluded) {
		this.messageExcluded = messageExcluded;
	}

	public String getDataDetailExcluded() {
		return dataDetailExcluded;
	}

	public void setDataDetailExcluded(String dataDetailExcluded) {
		this.dataDetailExcluded = dataDetailExcluded;
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

}
