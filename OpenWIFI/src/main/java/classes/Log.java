package classes;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

// 4기 서진우

@Data
public class Log {
    public static List<Log> list = new ArrayList<>();
    private int id;
    private float lat;
    private float lnt;
    private String dttm;
}