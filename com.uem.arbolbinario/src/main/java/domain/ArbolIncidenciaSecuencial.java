package domain;

public class ArbolIncidenciaSecuencial {
    private Incidencia[] arbol;
    private int capacidad;

    //Constructor
    public ArbolIncidenciaSecuencial(int capacidad) {
        this.capacidad = capacidad;
        this.arbol = new Incidencia[capacidad + 1]; //Empieza en el indice 1
    }

    public void insertar(int posicion, Incidencia incidencia) {

    }


}