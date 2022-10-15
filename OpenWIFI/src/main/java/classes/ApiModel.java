package classes;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class ApiModel implements Comparable<ApiModel>{
    private Wifi wifi;
    public float dist;

    @Override
    public int compareTo(@NotNull ApiModel o) {
        if(this.dist < o.dist){
            return -1;
        } else if(this.dist > o.dist){
            return 1;
        } else{
            return 0;
        }
    }
}