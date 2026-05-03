package base;

import domain.Incidencia;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        var incidenciaRaiz = new Incidencia("Root", 0);
        var incidencia1 = new Incidencia("1", 1);
        var incidencia2 = new Incidencia("2", 2);
        var incidencia3 = new Incidencia("3", 3);
        var incidencia4 = new Incidencia("4", 4);

        incidencia1.setIzquierda(incidencia3);          //         Root
        incidencia2.setDerecha(incidencia4);            //       /      \
        incidenciaRaiz.setIzquierda(incidencia1);       //      1        2
        incidenciaRaiz.setDerecha(incidencia2);         //     /          \
                                                        //    3            4
        System.out.println("Anchura:");
        System.out.println(incidenciaRaiz.anchura());
        System.out.println("Preorden:");
        incidenciaRaiz.buscarEnPreorden(null, "2");
        System.out.println("Inorden:");
        incidenciaRaiz.buscarEnInorden(null, "1");
        System.out.println("Postorden:");
        incidenciaRaiz.buscarEnPostorden(null, "4");
        System.out.println("Contador de Hojas:");
        var lista = new ArrayList<Incidencia>();
        incidenciaRaiz.contarHojas(null, lista);
        System.out.println("El total de nodos hojas es: " + lista.size() + ". Los nodos son: " + String.join(", ", lista.stream().map(Incidencia::getNombre).toList()));
    }
}
