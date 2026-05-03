package domain;

import java.util.HashMap;

public class Incidencia {

    private final String nombre;
    private final int codigo;
    private boolean encontrado = false;

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

    public void setDerecha(Incidencia derecha) {
        this.derecha = derecha;
    }

    public void setEncontrado(boolean encontrado) {
        this.encontrado = encontrado;
    }

    public void preorden(Incidencia nodo, String objetivo) {
        if (nodo == null) {
            nodo = this;
        }
        if (nodo.nombre.equals(objetivo)) {
            System.out.println("Objetivo encontrado nodo " + nodo.nombre);
            return;
        }
        System.out.println("Visitamos nodo " + nodo.nombre);
        if (nodo.izquierda != null) {
            preorden(nodo.izquierda, objetivo);
        }
        if (nodo.derecha != null) {
            preorden(nodo.derecha, objetivo);
        }
    }

    public void inorden(Incidencia nodo, String objetivo){
        if (nodo == null) {
            nodo = this;
        }
        if (nodo.izquierda != null) {
            inorden(nodo.izquierda, objetivo);
        }
        checkIfObjetivoEncontrado(nodo, objetivo);
        if (nodo.derecha != null) {
            inorden(nodo.derecha, objetivo);
        }
    }

    private static void setEncontrado(Incidencia nodo) {
        nodo.encontrado = true;
        if (nodo.izquierda != null) {
            nodo.izquierda.encontrado = true;
        }
        if (nodo.derecha != null) {
            nodo.derecha.encontrado = true;
        }
    }

    public void postorden(Incidencia nodo, String objetivo){
        if (nodo == null) {
            nodo = this;
        }
        if (nodo.izquierda != null) {
            postorden(nodo.izquierda, objetivo);
        }
        if (nodo.derecha != null) {
            postorden(nodo.derecha, objetivo);
        }
        checkIfObjetivoEncontrado(nodo, objetivo);
    }

    private void checkIfObjetivoEncontrado(Incidencia nodo, String objetivo) {
        if (nodo.nombre.equals(objetivo)) {
            setEncontrado(nodo);
            System.out.println("Objetivo encontrado nodo " + nodo.nombre);
            return;
        }
        if (nodo.izquierda != null && nodo.izquierda.encontrado || nodo.derecha != null && nodo.derecha.encontrado) {
            return;
        }
        System.out.println("Visitamos nodo " + nodo.nombre);
    }

    public int anchura() {
        HashMap<Integer, Integer> niveles = new HashMap<>();
        calcularNivel(this, 0, niveles);

        int max = 0;
        for (int valor : niveles.values()) {
            max = Math.max(max, valor);
        }
        return max;
    }

    private void calcularNivel(Incidencia nodo, int nivel, HashMap<Integer, Integer> niveles) {
        if (nodo == null) return;

        niveles.put(nivel, niveles.getOrDefault(nivel, 0) + 1);

        calcularNivel(nodo.izquierda, nivel + 1, niveles);
        calcularNivel(nodo.derecha, nivel + 1, niveles);
    }
}
