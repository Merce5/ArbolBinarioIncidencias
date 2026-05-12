package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Incidencia {

    private final String nombre;
    private final int codigo;

    private Incidencia izquierda;
    private Incidencia derecha;

    public Incidencia(String nombre, int codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setIzquierda(Incidencia izquierda) {
        this.izquierda = izquierda;
    }

    public Incidencia getIzquierda() {
        return izquierda;
    }

    public void setDerecha(Incidencia derecha) {
        this.derecha = derecha;
    }

    public Incidencia getDerecha() {
        return derecha;
    }

    // Raíz -> Izquierda -> Derecha
    public void preorden() {
        System.out.println(this.nombre);
        if (this.izquierda != null) {
            System.out.printf("%sI -> ", "  ".repeat(5 - this.calcularProfundidad()));
            this.izquierda.preorden();
        }
        if (this.derecha != null) {
            System.out.printf("%sD -> ", "  ".repeat(5 - this.calcularProfundidad()));
            this.derecha.preorden();
        }
    }

    // Izquierda -> Raíz -> Derecha
    public void inorden(){
        if (this.izquierda != null) {
            this.izquierda.inorden();
        }
        System.out.println("Visitamos nodo " + this.nombre);
        if (this.derecha != null) {
            this.derecha.inorden();
        }
    }

    // Izquierda -> Derecha -> Raíz
    public void postorden(){
        if (this.izquierda != null) {
            this.izquierda.postorden();
        }
        if (this.derecha != null) {
            this.derecha.postorden();
        }
        System.out.println("Visitamos nodo " + this.nombre);
    }

    // Controlamos con el método recursivo si se ha encontrado el objetivo siguiendo
    //  los mismos métodos de inorden, preorden y postorden descritos anteriormente
    public boolean buscarEnPreorden(String objetivo) {
        System.out.println("Visitamos nodo " + this.getNombre());
        if (this.getNombre().equals(objetivo)) {
            System.out.println("Objetivo encontrado nodo " + this.getNombre());
            return true;
        }
        if (this.getIzquierda() != null && this.getIzquierda().buscarEnPreorden(objetivo)) {
            return true;
        }
        return this.getDerecha() != null && this.getDerecha().buscarEnPreorden(objetivo);
    }

    public boolean buscarEnInorden(String objetivo) {
        if (this.getIzquierda() != null && this.getIzquierda().buscarEnInorden(objetivo)) {
            return true;
        }
        System.out.println("Visitamos nodo " + this.getNombre());
        if (this.getNombre().equals(objetivo)) {
            System.out.println("Objetivo encontrado nodo " + this.getNombre());
            return true;
        }
        return this.getDerecha() != null && this.getDerecha().buscarEnInorden(objetivo);
    }

    public boolean buscarEnPostorden(String objetivo){
        if (this.getIzquierda() != null && this.getIzquierda().buscarEnPostorden(objetivo)) {
            return true;
        }
        if (this.getDerecha() != null && this.getDerecha().buscarEnPostorden(objetivo)) {
            return true;
        }
        System.out.println("Visitamos nodo " + this.getNombre());
        if (this.getNombre().equals(objetivo)) {
            System.out.println("Objetivo encontrado nodo " + this.getNombre());
            return true;
        }
        return false;
    }

    // Creamos un hash map para saber en que nivel nos encontramos, sumando un nivel cada vez que nos desplazamos a la izquierda o a la dercha
    private void calcularNivel(Incidencia nodo, int nivel, HashMap<Integer, List<Incidencia>> niveles) {
        if (nodo == null) {
            return;
        }

        var nivelValue = niveles.getOrDefault(nivel, new ArrayList<>());
        nivelValue.add(nodo);
        niveles.put(nivel, nivelValue);

        calcularNivel(nodo.izquierda, nivel + 1, niveles);
        calcularNivel(nodo.derecha, nivel + 1, niveles);
    }

    // Miramos el máximo de nodos por nivel utilizando el método calcularNivel
    public int anchura() {
        LinkedHashMap<Integer, List<Incidencia>> niveles = new LinkedHashMap<>();
        calcularNivel(this, 0, niveles);

        int max = 0;
        for (var valor : niveles.values()) {
            max = Math.max(max, valor.size());
        }
        for (var valor : niveles.values()) {
            System.out.println(String.join(" - ", valor.stream().map(Incidencia::getNombre).toList()));
        }
        return max;
    }

    // Miramos el size del hash map para saber cuantos niveles tenemos
    public int calcularProfundidad(){
        HashMap<Integer, List<Incidencia>> niveles = new HashMap<>();
        calcularNivel(this, 0, niveles);
        return niveles.size();
    }

    // Sabemos que un nodo es hoja cuando no tenemos ningún nodo ni a la izquierda ni a la derecha, entonces sumamos uno
    public void contarHojas(Incidencia nodo, ArrayList<Incidencia> contador){
        if (nodo == null) {
            nodo = this;
        }
        if (nodo.izquierda == null && nodo.derecha == null){
            contador.add(nodo);
        }
        if (nodo.izquierda != null) {
            contarHojas(nodo.izquierda, contador);
        }
        if (nodo.derecha != null) {
            contarHojas(nodo.derecha, contador);
        }
    }
}
