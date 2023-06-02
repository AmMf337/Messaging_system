package model;

import java.util.ArrayList;


import java.util.Random;

public class Controller {
    private GraphAbjacentList<Integer> city;

    public Controller(){
        city = new GraphAbjacentList<>(false);

    }

    public String addDeliveryPointToCity(int deliveryPointName) {
        String msj = "";

        for (Vertex<Integer> a:city.getVertexes()) {
            if(a.getValue() == deliveryPointName){
                return  "Un punto de entrega con este nombre ya fue agregado";
            }
        }
        Vertex<Integer> temp = new Vertex<>(deliveryPointName);
        city.addVertex(temp);
        msj = "Punto de entrega "+deliveryPointName+" ha sido agregado";
        return  msj;
    }
    public String addRouteBetweenDeliveryPoints(int deliveryPointName1,int deliveryPointName,int distance)  {

        if(city.searchVertex(deliveryPointName)!=null && city.searchVertex(deliveryPointName)!=null){
            if(distance>0){
                city.createEdge(deliveryPointName,deliveryPointName,distance);
                return "Ruta agregada entre los puntos de entrega "+deliveryPointName1+" y "+deliveryPointName;
            }
            return "el valor de la distancia de la ruta es invalido";
        }
        return "Uno de los puntos de entrega no fue encontrado";


    }
    public String printMap(){
        String msj = "";

        for (Vertex<Integer> a: city.getVertexes()) {
            msj += "Nombre Punto d entrega "+ a.getValue();
            msj += "Rutas: "+"\n";
            for (Vertex<Integer> b: a.getVertexes()) {
                msj += "Nombre Punto d entrega "+ b.getValue() +"Costo de ruta "+ b.getWeight();
            }
        }

        return  msj;
    }
    public void generateRoutes()  {
        Random random = new Random();
        for (int i = 0; i < city.getVertexes().size(); i++) {
            Vertex<Integer> vertexA = city.getVertexes().get(i);

            Vertex<Integer> vertexB;
            do {
                int randomIndex = random.nextInt(city.getVertexes().size());
                vertexB = city.getVertexes().get(randomIndex);
            } while (vertexA == vertexB);


            int weight = random.nextInt(10) + 1;

            addRouteBetweenDeliveryPoints(vertexA.getValue(), vertexB.getValue(), weight);

        }
    }
    public String printWayThroughtAllDeliverypoints(){
        return  city.printBFS(1);
    }
}


