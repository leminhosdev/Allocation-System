package System.objects;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class user {
    private Double balance;
    private String password;
    private String email;
    private List<movie> rentedMovies;
}
