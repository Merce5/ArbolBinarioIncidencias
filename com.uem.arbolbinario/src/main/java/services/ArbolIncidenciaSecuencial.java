package services;

import domain.Incidencia;

import java.util.ArrayList;

public class ArbolIncidenciaSecuencial {

    private final Incidencia[] nodos;
    private final int capacidad;
    // Constructor de ArbolIncidenciaSecuencial con sus respectivos parametros
    public ArbolIncidenciaSecuencial (int capacidad){
        this.capacidad = capacidad;
        this.nodos = new Incidencia[capacidad];
    }
    // Metodo insertar para las incidencias del arbol
    public void insertar(Incidencia incidencia, int posicion) {
        if (posicion < 0 || posicion >= capacidad) {
            System.out.println("Posicion fuera de rango");
        }
        nodos[posicion] = incidencia;
        if (posicion > 0){
            int indicePadre = (posicion - 1) / 2;
            if (nodos[indicePadre] != null){
                if (posicion == 2 * indicePadre + 1){
                    nodos[indicePadre].setIzquierda(incidencia);
                } else {
                    nodos[indicePadre].setDerecha(incidencia);
                }
            }
        }
        int hijoIzquierdo = 2 * posicion + 1;
        int hijoDerecho = 2 * posicion + 2;
        if (hijoIzquierdo < capacidad && nodos[hijoIzquierdo] != null){
            incidencia.setIzquierda(nodos[hijoIzquierdo]);
        }
        if (hijoDerecho < capacidad && nodos[hijoDerecho] != null){
            incidencia.setDerecha(nodos[hijoDerecho]);
        }
    }
    // Metodo para marcar la raiz del arbol
    public Incidencia getRaiz() {
        return nodos[0];
    }
    // Metodo para encontrar las incidencias de nuestro arbol en sus respectivos nodos
    public Incidencia buscarPorNombre(String nombre) {
        for (Incidencia nodo : nodos){
            if (nodo != null && nodo.getNombre().equals(nombre)){
                return nodo;
            }
        }
        return null;
    }
    // Metodo para determinar si un nodo es hoja o no
    public boolean esHoja(int posicion) {
        if (posicion < 0 || posicion >= capacidad || nodos[posicion] == null) {
            System.out.println("Posicion fuera de rango");
        }
        int hijoIzquierdo = 2 * posicion + 1;
        int hijoDerecho = 2 * posicion + 2;

        boolean sinIzquierdo = hijoIzquierdo >= capacidad || nodos [hijoIzquierdo] == null;
        boolean sinDerecho = hijoDerecho >= capacidad || nodos [hijoDerecho] == null;
        return sinIzquierdo && sinDerecho;
    }

    // Metodo para contar todos los nodos hojas de nuestro arbol
    public int contarHojas() {
        if(getRaiz() == null) return 0;
        ArrayList<Incidencia> lista = new ArrayList<>();
        getRaiz().contarHojas(null, lista);
        return lista.size();
    }
    public ArrayList<Incidencia> obtenerHojas() {
        ArrayList<Incidencia> lista = new ArrayList<>();
        if (getRaiz() != null){
            getRaiz().contarHojas(null, lista);
        }
        return lista;
    }

    public int calcularProfundidad(){
        if (getRaiz() == null) return 0;
        return getRaiz().calcularProfundidad();
    }
    // Metodos de recorrido de nuestro arbol
    public void preorden() {
        if (getRaiz() == null) return;
        getRaiz().preorden();
    }
    public void inorden() {
        if (getRaiz() == null) return;
        getRaiz().inorden();
    }
    public void postorden() {
        if (getRaiz() == null) return;
        getRaiz().postorden();
    }
    public int anchura() {
        if (getRaiz() == null) return 0;
        return getRaiz().anchura();
    }
}