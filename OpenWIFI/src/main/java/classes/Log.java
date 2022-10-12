package classes;

import lombok.Data;

import java.util.Stack;

@Data
public class Log {
    public static Stack<Log> list = new Stack<>();
    private int id;
    private float lat;
    private float lnt;
    private String dttm;
}