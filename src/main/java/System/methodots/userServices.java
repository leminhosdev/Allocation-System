package System.methodots;

import System.connection.mainconnection;
import System.objects.movie;
import System.objects.user;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
@Slf4j
public class userServices {
    private static Scanner scanner = new Scanner(System.in);


    public static void register() {
        System.out.println("Type the new e-mail and password");
        System.out.print("email: ");
        String email = scanner.nextLine();
        System.out.print("password: ");
        String password = scanner.nextLine();
        user newuser = user.builder().email(email).password(password).balance(0.0).build();
        userCrud.register(newuser);
    }

    public static void deposit() {
        System.out.println("what's the email account that you want deposit?");
        String emailfounded = scanner.nextLine();
        Optional<user> userOptional = userCrud.findbyEmail(emailfounded);
        if (userOptional.isEmpty()) {
            System.out.println("user not found");
            return;
        }
        user userfounded = userOptional.get();

        System.out.println("user " + userfounded + " found, balance: " + userfounded.getBalance());
        System.out.println("enter the amount to be deposited");
        Integer amount = scanner.nextInt();
        user newbalance = user.
                builder().
                email(userfounded.getEmail()).
                password(userfounded.getPassword()).
                balance(Double.valueOf(amount) + userfounded.getBalance()).
                //   rentedMovies(userfounded.getRentedMovies()).
                        build();
        try {
            userCrud.deposit(newbalance);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public static void find() {
        System.out.println("Type the name or empty to all");
        String email = scanner.nextLine();
        List<user> users = userCrud.findby2(email);
        users.forEach(p -> System.out.println(p.getEmail()));
    }

    public static void login()  {
        try {System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        String sql = "SELECT * FROM rent_store.user users WHERE email = ? AND pasword = ?";
        Connection conn = mainconnection.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            System.out.println("Login successful!");
            userCrud.InsiderLogin();
        } else {
            System.out.println("Invalid username or password.");
        }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}

    //balance(Double.valueOf(amount))if(userOptional.isEmpty()){
//            System.out.println("user not found");
//            return;
//        }