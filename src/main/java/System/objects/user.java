package System.objects;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class user {
    private Double balance;
    private String password;
    private String email;
}
