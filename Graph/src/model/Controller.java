package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    private Map<String,GraphAbjacentList<String>> cities;
    private ArrayList<String> citiesNames;
    public Controller(){
        cities = new HashMap<>();
        citiesNames = new ArrayList<>();
    }
    public String addCity(String name){
        boolean isFound = false;
        String msj = "";
        for (String nameCity :citiesNames) {
            if(nameCity.equalsIgnoreCase(name)){
                return "La ciudad ya existe en el mapa";
            }else{
                GraphAbjacentList<String> temp = new GraphAbjacentList<>(false);
                cities.put(name,temp);
                citiesNames.add(name);
                 msj = "La ciudad "+name+" ha sido agregada";
            }
        }
        return msj;
    }
    public String addDeliveryPointToCity(String deliveryPointName,String cityName) throws Exception {
        String msj = "";
        if(cities.get(cityName)!=null){
            for (Vertex<String> a:cities.get(cityName).getVertexes()) {
                if(a.getValue().equalsIgnoreCase(deliveryPointName)){
                    msj =  "Un punto de entrega con este nombre ya fue agregado";
                }else{
                    Vertex<String> temp = new Vertex<>(deliveryPointName);
                    cities.get(cityName).addVertex(temp);
                    msj = "Punto de entrega "+deliveryPointName+" ha sido agregado";
                }
            }
        }
        return  msj;
    }
    public String addRouteBetweenDeliveryPoints(String deliveryPointName1,String deliveryPointName,String cityName,int cost) throws Exception {
        if(cities.get(cityName)!=null){
            if(cities.get(cityName).searchVertex(deliveryPointName)!=null && cities.get(cityName).searchVertex(deliveryPointName1)!=null){
                if(cost>0){
                    cities.get(cityName).createEdge(deliveryPointName1,deliveryPointName,cost);
                    return "Ruta agregada entre los puntos de entrega "+deliveryPointName1+" y "+deliveryPointName+ " en la ciudad "+cityName;
                }
                return "el valor del costo de la ruta es invalido";
            }
            return "Uno de los puntos de entrega no fue encontrado";
        }
        return "Ciudad no encontrada,tenga en cuenta el uso de mayusculas";
    }
    public String printMap(){
        String msj = "";
        for (String key:citiesNames) {
            GraphAbjacentList<String> temp = cities.get(key);
            for (Vertex<String> a: temp.getVertexes()) {
                msj += "Nombre Punto d entrega "+ a.getValue();
                msj += "Rutas: "+"\n";
                for (Vertex<String> b: a.getVertexes()) {
                    msj += "Nombre Punto d entrega "+ b.getValue() +"Costo de ruta "+ b.getWeight();
                }
            }
        }
        return  msj;
    }

}
