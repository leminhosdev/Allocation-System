package System.objects;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class movie {
    private String name;
    private Integer id;
    private Double price;
    private Integer amount;
}
