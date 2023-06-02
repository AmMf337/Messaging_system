package ui;
import java.util.Scanner;
import model.*;

public class Main {
    private Scanner reader;
    private Controller control;
    public Main() {
        reader = new Scanner(System.in);
        control = new Controller();
    }

    public static void main(String[] args) {


        Main main = new Main();

        int option = 0;

        do{

            option = main.getOptionShowMenu();
            main.executeOption(option);

        }while(option != 0);

        main.getReader().close();

    }

    public int getOptionShowMenu(){
        int option = 0;
        System.out.println(
                "1. Cargar puntos de entrega a la ciudad\n" +
                        "2. Generar rutas aleatorias\n" +
                        "3. Imprimir mapa de las rutas de la ciudad\n" +
                        "4. Imprimir recorrido de todos los puntos pasando por ellos por lo menos una vez\n" +
                        "0. Exit. ");
        option =  validateIntegerInput();
        return option;
    }

    public void executeOption(int option){

        String msj = "";
        switch(option){
            case 1:
                for(int i = 0;i<51;i++){
                    control.addDeliveryPointToCity(i);
                }
                break;

            case 2:
                control.generateRoutes();
                break;

            case 3:

                msj = control.printMap();
                System.out.println(msj);
                break;
            case 4:
                msj = control.printWayThroughtAllDeliverypoints();
                System.out.println(msj);
                break;
            case 5:


                break;
            case 0:
                System.out.println("Exit program.");
                break;

            default:
                System.out.println("Invalid Option");
                break;
        }
    }

    public Scanner getReader(){
        return reader;
    }

    public int validateIntegerInput(){
        int option = 0;

        if(reader.hasNextInt()){
            option = reader.nextInt();
        }
        else{
            // clear reader.
            reader.nextLine();
            option = -1;
        }

        return option;
    }

    public double validateDoubleInput(){
        double option = 0;

        if(reader.hasNextDouble()){
            option = reader.nextDouble();
        }
        else{
            // clear reader.
            reader.nextLine();
            option = -1;
        }

        return option;
    }



}

