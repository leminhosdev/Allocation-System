package System.methodots;

import System.connection.mainconnection;
import System.objects.movie;
import System.objects.user;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MovieCrud {
   // static List<movie> movieListtt = new ArrayList<>();

    public static void registerMovie (movie movie){

        log.info("Adding movie");
        try(Connection conn = mainconnection.getConnection();
            PreparedStatement smt = registerPrepareStatementMovie(conn, movie))
        {
            boolean receptor = false;
            log.info("1");
           List<movie> movieList = findbyname(movie.getName());
            for(movie moviee: movieList){
            if (movie.getName().equalsIgnoreCase(moviee.getName())){
                log.info("adding one more");
                MovieService.update(moviee);
                receptor = true;
                //atualizar moviee para adicionar o valor amount + 1
            }
        }
            System.out.println(receptor);
           if(receptor == false){
            savingMovieTEST(movie);
         //  smt.execute();
            log.info("added");
        }
        } catch (SQLException e){
            log.error("Error adding new movie in stock");
            e.printStackTrace();
        }
    }
    private static PreparedStatement registerPrepareStatementMovie(Connection conn , movie movie) throws SQLException {
        String sql = " INSERT INTO `rent_store`.`movies` (`name_movie`,`price`,`amount`) VALUES ( ?, ? , ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, movie.getName());
        ps.setDouble(2, movie.getPrice());
        ps.setInt(3, movie.getAmount());
       // ps.setInt(3, movie.getAmount());
        return ps;
    }
    public static void updateAmount(movie movie) throws SQLException{  //creating the methodots that do deposits in your account
        log.info("Adding one more movie in amount", movie);
        try(Connection conn = mainconnection.getConnection();
            PreparedStatement smt = updateAmountPrepareStatement(conn, movie)){
            smt.execute();
        } catch (SQLException e){
            log.error("We can't add movie in amount");
            e.printStackTrace();
        }
    }

    private static PreparedStatement updateAmountPrepareStatement(Connection conn, movie movie) throws SQLException{
        String sql = "UPDATE `rent_store`.`movies` SET `amount` = ? WHERE (`name_movie` = ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,movie.getAmount());
        ps.setString(2,movie.getName());
        return ps;
    }
    public static List<movie> findbyname(String name) {
        log.info("Finding all movies");
        String sql = "SELECT * FROM rent_store.movies where name_movie like ?;";
        List<movie> movies = new ArrayList<>();
        try (Connection conn = mainconnection.getConnection();
             PreparedStatement smt = create(conn, sql, name);
             ResultSet rs = smt.executeQuery();
        ) {
            log.info("2");
            while (rs.next()) {
                movie newmovie = movie.
                        builder().
                        name(rs.getString("name_movie")).price(rs.getDouble("price")).
                        amount(rs.getInt("amount")).
                        build();
                movies.add(newmovie);
                log.info("3");
            }
        } catch (SQLException e) {
            log.error("deu B.O");
            e.printStackTrace();
        }
        return movies;
    }
    private static PreparedStatement create(Connection conn, String sql, String name) throws SQLException {
        PreparedStatement smt = conn.prepareStatement(sql);
        smt.setString(1, String.format("%%%s%%", name));
        return smt;
    }
    public static void savingMovieTEST(movie movie){
        log.info("Registering movie... wait a moment");
        try(Connection conn = mainconnection.getConnection();
            PreparedStatement smt = savingmoviePrepareStatement(conn,movie)) {
            smt.execute();
        } catch (SQLException e){
            log.error("Error while registering new movie");
            e.printStackTrace();
        }
    }
    private static PreparedStatement savingmoviePrepareStatement(Connection conn, movie movie) throws SQLException {
        String sql = "INSERT INTO `rent_store`.`movies` (`name_movie`, `price`,`amount`) VALUES (?, ?, ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, movie.getName());
        ps.setDouble(2, movie.getPrice());
        ps.setInt(3, movie.getAmount());
        return ps;
    }
}
