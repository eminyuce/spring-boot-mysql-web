package guru.springframework.domain;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MyTestDummy {

    public MyTestDummy() {
    }


    public ArrayList<Log> getLog() {
        return log;
    }

    public void setLog(ArrayList<Log> log) {
        this.log = log;
    }

    ArrayList < Log > log = new ArrayList < Log > ();


}
