package base;

import domain.Incidencia;

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
        System.out.println(incidenciaRaiz.anchura());
        incidenciaRaiz.preorden(null);
        System.out.println("Inorden");
        incidenciaRaiz.inorden(null);
    }
}
