package guru.springframework.entity;

import java.util.List;


public class LogSearch {
	public String search;
	private List<NfType> nfTypes;
	private List<N5gLogLevel> logLevels;
	private String messageExcluded;
	private String dataDetailExcluded;
	private int logLimit;
	private List<NfType> fromNfTypesList;
	private List<NfType> toNfTypesList;
	
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
