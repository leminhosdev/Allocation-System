package System.methodots;


import java.util.Scanner;

public class interfaceSystem {
    private static Scanner scanner = new Scanner(System.in);

    public static void menuzibn(){
        int optionn = 1;
        while (optionn != 0){
            menu();
            optionn = scanner.nextInt();
            buildmenu(optionn);
        }
    }

    public static void buildmenu(Integer optionn){

        switch (optionn){
           case 1 -> userServices.register();
           case 2 -> userServices.find();
           case 3 -> userServices.deposit();
           case 4 -> MovieService.find();
           case 5 -> MovieService.saveMovie();
           case 6 -> userServices.login();
           case 0 -> System.out.println("leaving");
            default -> throw new RuntimeException("ERROR");
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
