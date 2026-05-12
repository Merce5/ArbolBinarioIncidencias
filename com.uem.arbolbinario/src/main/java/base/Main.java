package base;

import services.ArbolIncidenciaSecuencial;
import domain.Incidencia;
import services.ArbloIncidenciasDinamico;
import services.MenuService;

public class Main {
    public static void main(String[] args) {

        var dinamico = new ArbloIncidenciasDinamico();
        var incidencias = new Incidencia("Incidencias", 100);
        var hardware = new Incidencia("Hardware", 50);
        var software = new Incidencia("Software", 150);
        var equipo = new Incidencia("Equipo", 30);
        var red = new Incidencia("Red", 70);
        var sistema = new Incidencia("Pantalla", 20);
        var aplicacion = new Incidencia("Router", 60);
        var pantalla = new Incidencia("Sistema", 125);
        var router = new Incidencia("Aplicación", 175);
        var login = new Incidencia("Login", 110);
        var correo = new Incidencia("Correo", 160);

        var root = new Incidencia("Incidencias", 100);
        root = dinamico.insertar(null, incidencias);
        root = dinamico.insertar(root, hardware);
        root = dinamico.insertar(root, software);
        root = dinamico.insertar(root, equipo);
        root = dinamico.insertar(root, red);
        root = dinamico.insertar(root, sistema);
        root = dinamico.insertar(root, aplicacion);
        root = dinamico.insertar(root, pantalla);
        root = dinamico.insertar(root, router);
        root = dinamico.insertar(root, login);
        root = dinamico.insertar(root, correo);

        //ARBOL INCIDENCIA SECUENCIAL
        ArbolIncidenciaSecuencial arbol = new ArbolIncidenciaSecuencial(15);

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

        System.out.println("---------- PREORDEN SECUENCIAL ----------");
        arbol.preorden();
        System.out.println("---------- INORDEN SECUENCIAL ----------");
        arbol.inorden();
        System.out.println("---------- POSTORDEN SECUENCIAL ----------");
        arbol.postorden();
        System.out.println("---------- ANCHURA SECUENCIAL ----------");
        arbol.anchura();
        System.out.println("---------- BUSCAR SECUENCIAL ----------");
        // Se inicilizan dos objetivos de las incidencias de nuestro arbol.
        String [] objetivos = {"Correo", "Router"};
        // Logica de busqueda de objetivos
        for (String objetivo : objetivos) {
            System.out.println("Buscando" + objetivo + " en preorden");
            arbol.getRaiz().buscarEnPreorden(objetivo); // Empieza busqueda en preorden de los dos objetivos

            Incidencia encontrado = arbol.buscarPorNombre(objetivo);

            if (encontrado != null){ // Si el objetivo no es null
                int posicion = encontrado.getCodigo(); // Posicion donde se encuentra en el arbol
                boolean hoja = arbol.esHoja(posicion); // Si es nodo hoja o no
                System.out.println( objetivo + " es nodo hoja? " + (hoja ? "Sí" : "No"));
            } else {
                System.out.println("Incidencia '" + objetivo + "' no encontrada.");
                }
        }

        System.out.println("---------- CONTAR HOJAS SECUENCIAL ----------");
        int totalHojas = arbol.contarHojas();
        System.out.println("Numero de hojas secuencial:" + totalHojas);

        //ARBOL INCIDENCIA DINAMICO
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

