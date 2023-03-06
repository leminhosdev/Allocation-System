package System.methodots;

import System.connection.mainconnection;
import System.objects.movie;
import System.objects.user;
import lombok.extern.slf4j.Slf4j;

import javax.swing.text.html.Option;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class userCrud {
    //here we have the methods responsible for do the CRUD for users. and other methods.

    public static void register(user user){
        log.info("Registering... wait a moment");
        try(Connection conn = mainconnection.getConnection();
            PreparedStatement smt = registerPrepareStatement(conn,user)) {
            smt.execute();
        } catch (SQLException e){
            log.error("Error while registering new account");
            e.printStackTrace();
        }
    }
    private static PreparedStatement registerPrepareStatement(Connection conn, user user) throws SQLException {
        String sql = "INSERT INTO `rent_store`.`user` (`email`, `pasword`) VALUES (?, ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, user.getEmail());
        ps.setString(2, user.getPassword());
        return ps;
    }
    // UPDATE `rent_store`.`user` SET `balance` = '123' WHERE (`email` = 'dudumalah@gmail.com');

    public static void deposit(user user) throws SQLException{  //creating the methodots that do deposits in your account
        log.info("Deposinting the amount'{}'", user);
        try(Connection conn = mainconnection.getConnection();
            PreparedStatement smt = depositPrepareStatement(conn, user)){
            smt.execute();

        } catch (SQLException e){
            log.error("We can't deposit your amount, a error occurred");
            e.printStackTrace();
        }
    }
    private static PreparedStatement depositPrepareStatement(Connection conn, user user) throws SQLException{
        String sql = "UPDATE `rent_store`.`user` SET `balance` = ? WHERE (`email` = ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDouble(1,user.getBalance());
        ps.setString(2,user.getEmail());

        return ps;
    }

    public static Optional<user> findbyEmail(String email){
        log.info("Searching results to '{}'", email);
        try(Connection conn = mainconnection.getConnection();
            PreparedStatement smt = findEmail(conn, email)){
            ResultSet rs = smt.executeQuery();
            if(!rs.next())return Optional.empty();
            return Optional.of(user.
                    builder().
                    email(rs.getString("email")).
                    password(rs.getString("pasword")).
                    balance(rs.getDouble("balance")).
                    build());
        } catch (SQLException e){
            log.info("We can't found ");
            e.printStackTrace();
        }
        return Optional.empty();
    }
    private static PreparedStatement findEmail(Connection conn, String email) throws SQLException{
        String sql = "SELECT * FROM rent_store.user where email = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, email);
        return ps;
    }
    public static List<user> findby2(String email) {
        log.info("Finding all users");
        String sql = "SELECT * FROM rent_store.user where email like ?;";
        List<user> users = new ArrayList<>();
        try (Connection conn = mainconnection.getConnection();
             PreparedStatement smt = create(conn, sql, email);
             ResultSet rs = smt.executeQuery();
        ) {
            while (rs.next()) {
                user newuser = user.
                        builder().
                        email(rs.getString("email")).balance(rs.getDouble("balance")).
                        password(rs.getString("pasword")).
                        build();
                users.add(newuser);
            }
        } catch (SQLException e) {
            log.error("deu B.O");
            e.printStackTrace();
        }
        return users;
    }
    private static PreparedStatement create(Connection conn, String sql, String email) throws SQLException {
        PreparedStatement smt = conn.prepareStatement(sql);
        smt.setString(1, String.format("%%%s%%", email));
        return smt;

    }


    public static ResultSet login(user user){
        try (Connection conn = mainconnection.getConnection();
            PreparedStatement ps = loginprepareStatement(conn, user);
             ResultSet rs = ps.executeQuery();){
            return rs;
        }catch (SQLException e){
            log.error("incorrect credentials");
            return null;
        }
    }
    private static PreparedStatement loginprepareStatement(Connection conn, user user) throws SQLException{
        String sql = "SELECT * FROM rent_store.user where email = ? and pasword = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, user.getEmail());
        ps.setString(1, user.getPassword());
        return ps;
    }
}
