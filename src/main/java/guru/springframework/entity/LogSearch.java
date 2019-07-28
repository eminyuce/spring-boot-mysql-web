package guru.springframework.entity;

import java.util.List;

public class LogSearch {
	public String search;
	List<NfType> nfTypes;
	List<N5gLogLevel> logLevels;

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


	public LogSearch() {
		search="";
	}
 

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
}
