package base;

import domain.Incidencia;

public class Main {
    public static void main(String[] args) {
        var incidenciaRoor = new Incidencia("Root", 0);
        var incidencia1 = new Incidencia("1", 1);
        var incidencia2 = new Incidencia("2", 2);
        var incidencia3 = new Incidencia("3", 3);
        var incidencia4 = new Incidencia("4", 4);

        incidencia1.setIzquierda(incidencia3);
        incidencia2.setDerecha(incidencia4);
        incidenciaRoor.setIzquierda(incidencia1);
        incidenciaRoor.setDerecha(incidencia2);

        System.out.println(incidenciaRoor.anchura());
    }
}
