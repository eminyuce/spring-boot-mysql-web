package com.emin.yuce.learning.services;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileService {

    Map<String, String> obstructionMap = new HashMap<>(); // Map to put data

    public Map<String, String> getObstructionMap() {
        return obstructionMap;
    }

    public void setObstructionMap(Map<String, String> obstructionMap) {
        this.obstructionMap = obstructionMap;
    }

    public void parseFile() {

        try {
            String result = IOUtils.toString(getClass()
                    .getClassLoader().getResourceAsStream("obstruction.txt"), StandardCharsets.UTF_8);
            var bufferedReader = new BufferedReader(new StringReader(result));
            var line = bufferedReader.readLine();
            while (line != null) {
                String[] split = line.split(" ");
                obstructionMap.put(split[0], split[1]);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
