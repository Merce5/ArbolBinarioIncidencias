package services;

import domain.Incidencia;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuService {
    private Incidencia root;
    private ArbloIncidenciasDinamico dinamico;
    Scanner sc = new Scanner(System.in);

    public MenuService(Incidencia root, ArbloIncidenciasDinamico dinamico) {
        this.root = root;
        this.dinamico = dinamico;
    }

    // Menú para poder realizar las operaciones deseadas
    public void menu() {
        String opcionS;
        int opcion;
        while(true) {
            System.out.println("\n===== GESTOR DE INCIDENCIAS =====");
            System.out.println("1. Insertar incidencia");
            System.out.println("2. Buscar incidencia por nombre");
            System.out.println("3. Comprobar si una incidencia es hoja");
            System.out.println("4. Contar nodos hoja");
            System.out.println("5. Calcular profundidad del árbol");
            System.out.println("6. Recorrer en preorden");
            System.out.println("7. Recorrer en inorden");
            System.out.println("8. Recorrer en postorden");
            System.out.println("9. Recorrer en anchura");
            System.out.println("0. Salir");
            System.out.print("Seleccione opción: ");

            opcionS = sc.nextLine();
            try {
                opcion = Integer.parseInt(opcionS);
            } catch (NumberFormatException e) {
                System.out.println("Solo se permiten números. Vuelva a intentarlo.");
                menu();
                return;
            }
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre incidencia: ");
                    String nombre = sc.nextLine();
                    System.out.print("Código incidencia: ");
                    int codigo = sc.nextInt();
                    sc.nextLine();

                    Incidencia nueva = new Incidencia(nombre, codigo);

                    root = dinamico.insertar(root, nueva);
                    System.out.println("Incidencia insertada correctamente.");
                    menu();
                }

                case 2 -> {
                    System.out.print("Nombre a buscar: ");
                    String objetivo = sc.nextLine();

                    boolean encontrado = root.buscarEnPreorden(objetivo);

                    if (!encontrado) {
                        System.out.println("No se encontró la incidencia.");
                    }
                    menu();
                }

                case 3 -> {
                    System.out.print("Nombre de incidencia: ");
                    String nombre = sc.nextLine();
                    ArrayList<Incidencia> hojas = new ArrayList<>();
                    root.contarHojas(root, hojas);
                    if (hojas.stream().anyMatch(incidencia -> incidencia.getNombre().equals(nombre))) {
                        System.out.println("La incidencia es hoja.");
                    } else {
                        System.out.println("La incidencia no es hoja.");
                    }
                    menu();
                }

                case 4 -> {
                    ArrayList<Incidencia> hojas = new ArrayList<>();
                    root.contarHojas(root, hojas);
                    System.out.println("Cantidad de hojas: " + hojas.size());
                    menu();
                }

                case 5 -> {
                    System.out.println("Profundidad: " + root.calcularProfundidad());
                    menu();
                }

                case 6 -> {
                    System.out.println("\nPREORDEN:");
                    root.preorden();
                    menu();
                }

                case 7 -> {
                    System.out.println("\nINORDEN:");
                    root.inorden();
                    menu();
                }

                case 8 -> {
                    System.out.println("\nPOSTORDEN:");
                    root.postorden();
                    menu();
                }

                case 9 -> {
                    System.out.println("\nRECORRIDO EN ANCHURA:");
                    root.anchura();
                    menu();
                }
                case 0 -> {
                    System.out.println("Saliendo...");
                    System.exit(0);
                }
                default -> {
                    System.out.println("Opción inválida, por favor intente nuevamente.");
                    menu();
                }
            }
            sc.close();
        }
    }
}