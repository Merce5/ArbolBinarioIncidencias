package domain;

import java.util.HashMap;

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

    public void setDerecha(Incidencia derecha) {
        this.derecha = derecha;
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

    public void preorden(Incidencia nodo) {
        if (nodo == null) {
            nodo = this;
        }
        System.out.println("Visitamos nodo " + nodo.nombre);
        if (nodo.izquierda != null) {
            preorden(nodo.izquierda);
        }
        if (nodo.derecha != null) {
            preorden(nodo.derecha);
        }
    }

    public void inorden(Incidencia nodo){
        if (nodo == null) {
            nodo = this;
        }
        if (nodo.izquierda != null) {
            inorden(nodo.izquierda);
        }
        System.out.println("Visitamos nodo " + nodo.nombre);
        if (nodo.derecha != null) {
            inorden(nodo.derecha);
        }
    }

    private void calcularNivel(Incidencia nodo, int nivel, HashMap<Integer, Integer> niveles) {
        if (nodo == null) return;

        niveles.put(nivel, niveles.getOrDefault(nivel, 0) + 1);

        calcularNivel(nodo.izquierda, nivel + 1, niveles);
        calcularNivel(nodo.derecha, nivel + 1, niveles);
    }
}
