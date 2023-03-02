package System.objects;

import lombok.Builder;
import lombok.Value;

import java.util.List;
@Value
@Builder
public class stock {
    private List<movie> moviesList;
}
