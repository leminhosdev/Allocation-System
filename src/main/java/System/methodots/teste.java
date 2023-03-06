package System.methodots;

import java.util.Scanner;

public class teste {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
      //  userServices.register();
       // userServices.deposit();
      // MovieService.saveMovie();
       // userServices.find();
        //MovieService.savingMovieTEST();
      //  MovieService.find();


        int option;
        while (true){
            menu();
            option = scanner.nextInt();
            interfaceSystem.buildmenu(option);
        }

    }
    private static void menu(){
        System.out.println("1 - Register new user");
        System.out.println("2 - List some/all users");
        System.out.println("3 - Deposit money");
        System.out.println("4 - List available movies");
        System.out.println("5 - Add more movies in stock");
        System.out.println("6 - user login");
        System.out.println("0 - exit");
    }
}
