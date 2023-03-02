package System.methodots;

import System.connection.mainconnection;
import System.objects.user;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
