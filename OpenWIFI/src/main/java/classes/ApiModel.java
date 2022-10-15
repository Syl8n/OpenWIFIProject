package classes;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class ApiModel implements Comparable<ApiModel>{
    private Wifi wifi;
    public float dist;

    @Override
    public int compareTo(@NotNull ApiModel o) {
        return (int) (this.dist - o.dist);
    }
}