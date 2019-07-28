package guru.springframework.services;

import guru.springframework.domain.Log;
import guru.springframework.entity.LogSearch;

import java.util.List;

public interface LogService {

    List<Log> listAll();
    Log getById(int id);
    void delete(int id);
    void deleteAll();
    List<Log> listAllByMessageContains(String search);
	List<Log> listAllByLogSearch(LogSearch logSearch, String[] selectedNfNames, String[] loglevelNames);

}
