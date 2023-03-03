package System.methodots;

import System.objects.movie;
import System.objects.user;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MovieService {
    private static Scanner scanner = new Scanner(System.in);
    static List<movie> movieListtt = new ArrayList<>();


    public static void saveMovie(){
        System.out.println("type tha name of the movie");
        String name = scanner.nextLine();
        System.out.println("type the price");
        Double price = scanner.nextDouble();
        movie newmovie = movie.
                builder().
                name(name).
                price(price).
                amount(1).
                build();
        MovieCrud.registerMovie(newmovie);
    }
    public static void update(movie movie){
        movie newamount = movie.
                builder().
                name(movie.getName()).
                amount(movie.getAmount()+1).
                build();
        try{
            MovieCrud.updateAmount(newamount);
        } catch (SQLException e){
            throw new RuntimeException();
        }
    }
    private static void find(){
        System.out.println("Type the name or empty to all");
        String name = scanner.nextLine();
        List<movie> movies = MovieCrud.findbyname(name);
        movies.forEach(p -> System.out.printf(p.getName(),p.getPrice(), p.getAmount()));
    }
    public static void savingMovieTEST(){
        System.out.println("chose one NAME");
        String name = scanner.nextLine();
        System.out.println("CHose one price");
        Double price = scanner.nextDouble();
        movie movietop = movie.builder().name(name).price(price).amount(1).build();
        MovieCrud.savingMovieTEST(movietop);
    }
}
