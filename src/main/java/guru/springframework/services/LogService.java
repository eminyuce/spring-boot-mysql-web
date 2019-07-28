package guru.springframework.services;

import guru.springframework.domain.Log;

import java.util.List;

public interface LogService {

    List<Log> listAll();
    Log getById(int id);
    void delete(int id);
    void deleteAll();
    List<Log> listAllByMessageContains(String search);

}
