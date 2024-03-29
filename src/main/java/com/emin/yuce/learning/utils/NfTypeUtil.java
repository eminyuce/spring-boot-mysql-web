package com.emin.yuce.learning.utils;

import com.emin.yuce.learning.domain.Log;
import com.emin.yuce.learning.entity.NfType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NfTypeUtil {

    public static List<NfType> getNfTypes(String[] selectedNfNames) {
        List<NfType> nfTypes = new ArrayList<NfType>();
        nfTypes.add(new NfType("AMF", false));
        nfTypes.add(new NfType("SMF", false));
        nfTypes.add(new NfType("AUSF", false));
        nfTypes.add(new NfType("UDM", false));
        nfTypes.add(new NfType("UDR", false));
        nfTypes.add(new NfType("PCF", false));
        nfTypes.add(new NfType("NRF", false));
        nfTypes.add(new NfType("NSSF", false));
        nfTypes.add(new NfType("UPF", false));
        if (selectedNfNames != null && selectedNfNames.length > 0) {
            for (NfType nfType : nfTypes) {
                List<String> selectedNfNameList = Arrays.asList(selectedNfNames);
                nfType.setSelected(selectedNfNameList.stream().anyMatch(t1 -> nfType.getName().equals(t1)));
            }
        }
        return nfTypes;
    }

    public static List<Log> extractLogByNfTypes(String[] selectedNfNames, List<Log> logsResult) {
        if (selectedNfNames != null && selectedNfNames.length > 0) {
            List<String> selectedNfNameList = Arrays.asList(selectedNfNames);
            logsResult = logsResult.stream()
                    .filter(t -> selectedNfNameList.stream().anyMatch(t1 -> t.getNf_type().equalsIgnoreCase(t1)))
                    .collect(Collectors.toList());
        }
        return logsResult;
    }

}
