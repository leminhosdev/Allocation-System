package System.objects;

import lombok.Builder;
import lombok.Setter;
import lombok.Value;
@Setter
@Value
@Builder
public class movie {
    @Setter
    private String name;
   // private Integer id;
    private Double price;
    private Integer amount;

}
