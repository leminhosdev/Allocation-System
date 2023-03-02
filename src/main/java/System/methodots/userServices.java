package System.methodots;

import System.objects.user;

import java.util.Scanner;

public class userServices {
    private static Scanner scanner = new Scanner(System.in);


    public static void register(){
        System.out.println("Type the new e-mail and password");
        System.out.print("email: ");
        String email = scanner.nextLine();
        System.out.print("password: ");
        String password = scanner.nextLine();
        user newuser = user.builder().email(email).password(password).build();
        userCrud.register(newuser);
    }


}
