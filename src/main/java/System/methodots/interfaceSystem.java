package System.methodots;

public class interfaceSystem {
    public static void buildmenu(Integer option){
        switch (option){
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
}
