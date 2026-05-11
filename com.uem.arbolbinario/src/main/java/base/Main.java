package base;

import domain.ArbolIncidenciaSecuencial;
import domain.Incidencia;
import services.ArbloIncidenciasDinamico;
import services.MenuService;

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
        System.out.println("Preorden:");
        incidenciaRaiz.preorden();
        System.out.println("Inorden:");
        incidenciaRaiz.inorden(null);
        System.out.println("Postorden:");
        incidenciaRaiz.postorden(null);
        System.out.println("----------------------------------------");
        System.out.println("Buscar rreorden:");
        incidenciaRaiz.buscarEnPreorden("2");
        System.out.println("Buscar inorden:");
        incidenciaRaiz.buscarEnInorden(null, "1");
        System.out.println("Buscar postorden:");
        incidenciaRaiz.buscarEnPostorden(null, "4");
        System.out.println("----------------------------------------");
        System.out.println("Anchura:" + incidenciaRaiz.anchura());
        System.out.println("Contador de Hojas:");
        var lista = new ArrayList<Incidencia>();
        incidenciaRaiz.contarHojas(null, lista);
        System.out.println("El total de nodos hojas es: " + lista.size() + ". Los nodos son: " + String.join(", ", lista.stream().map(Incidencia::getNombre).toList()));
        System.out.println("Profundidad:" + incidenciaRaiz.calcularProfundidad());

        //ARBOL INCIDENCIA SECUENCIAL
        ArbolIncidenciaSecuencial arbol = new ArbolIncidenciaSecuencial(15);
        Incidencia incidencias = new Incidencia("Incidencias", 0);
        Incidencia hardware = new Incidencia("Hardware", 1);
        Incidencia software = new Incidencia("Software", 2);
        Incidencia equipo = new Incidencia("Equipo", 3);
        Incidencia red = new Incidencia("Red", 4);
        Incidencia sistema = new Incidencia("Sistema", 5);
        Incidencia aplicacion = new Incidencia("Aplicacion", 6);
        Incidencia pantalla = new Incidencia("Pantalla", 7);
        Incidencia router = new Incidencia("Router", 9);
        Incidencia login = new Incidencia("Login", 11);
        Incidencia correo = new Incidencia("Correo", 13);

        arbol.insertar(incidencias, 0);
        arbol.insertar(hardware, 1);
        arbol.insertar(software, 2);
        arbol.insertar(equipo, 3);
        arbol.insertar(red, 4);
        arbol.insertar(sistema, 5);
        arbol.insertar(aplicacion, 6);
        arbol.insertar(pantalla, 7);
        arbol.insertar(router, 9);
        arbol.insertar(login, 11);
        arbol.insertar(correo, 13);

        System.out.println("Preorden");
        arbol.preorden();
        System.out.println("Inorden");
        arbol.inorden();
        System.out.println("Postorden");
        arbol.postorden();
        System.out.println("Anchura");
        arbol.anchura();

        String [] objetivos = {"Correo", "Router"};
        for (String objetivo : objetivos) {
            System.out.println("Buscando" + objetivo + " en preorden");
            arbol.getRaiz().buscarEnPreorden(objetivo);

            Incidencia encontrado = arbol.buscarPorNombre(objetivo);
            if (encontrado != null){
                int posicion = encontrado.getCodigo();
                boolean hoja = arbol.esHoja(posicion);
                System.out.println( objetivo + " es nodo hoja? " + (hoja ? "Sí" : "No"));
            } else {
                System.out.println("Incidencia '" + objetivo + "' no encontrada.");
                }
        }

        int totalHojas = arbol.contarHojas();
        System.out.println("Numero de hojas:" + totalHojas);
        
        //ARBOL INCIDENCIA SECUENCIAL
        var dinamico = new ArbloIncidenciasDinamico();
        var root = new Incidencia("Incidencias", 100);
        root = dinamico.insertar(null, new Incidencia("Incidencias", 100));
        root = dinamico.insertar(root, new Incidencia("Hardware", 50));
        root = dinamico.insertar(root, new Incidencia("Software", 150));
        root = dinamico.insertar(root, new Incidencia("Equipo", 30));
        root = dinamico.insertar(root, new Incidencia("Red", 70));
        root = dinamico.insertar(root, new Incidencia("Pantalla", 20));
        root = dinamico.insertar(root, new Incidencia("Router", 60));
        root = dinamico.insertar(root, new Incidencia("Sistema", 125));
        root = dinamico.insertar(root, new Incidencia("Aplicación", 175));
        root = dinamico.insertar(root, new Incidencia("Login", 110));
        root = dinamico.insertar(root, new Incidencia("Correo", 160));
        System.out.println("---------- PREORDEN DINAMICO ----------");
        root.preorden();
        System.out.println("---------- INORDEN DINAMICO ----------");
        root.inorden();
        System.out.println("---------- POSTORDEN DINAMICO ----------");
        root.postorden();
        System.out.println("---------- ANCHURA DINAMICO ----------");
        root.anchura();
        var menu = new MenuService(root, dinamico);
        menu.menu();
    }
}

