package guru.springframework.entity;

import java.util.List;


public class LogSearch {
	public String search;
	List<NfType> nfTypes;
	List<N5gLogLevel> logLevels;
	private String messageExcluded;
	private String dataDetailExcluded;
	private int logLimit;
	
	

	public LogSearch() {
		search = "";
		logLimit = 3000;
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

}
