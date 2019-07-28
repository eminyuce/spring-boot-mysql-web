package guru.springframework.services;

import guru.springframework.domain.Log;
import guru.springframework.domain.Product;
import guru.springframework.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {


    private LogRepository logRepository;


    @Autowired
    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }


    @Override
    public List<Log> listAll() {
        List<Log> logs = new ArrayList<>();
        logRepository.findAll().forEach(logs::add); //fun with Java 8
        return logs;
    }
    
    @Override
    public List<Log> listAllByMessageContains(String search) {
    	List<Log> logs = new ArrayList<>();
        logRepository.findByMessageContains(search).forEach(logs::add); //fun with Java 8
        return logs;
    }

    @Override
    public Log getById(int id) {
        return logRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        logRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        logRepository.deleteAll();
    }
}
