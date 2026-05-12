package services;

import domain.Incidencia;

public class ArbloIncidenciasDinamico {

    // Al insertar tenemos en cuenta la magnitud del código para saber si lo insertamos a la izquierda o a la derecha
    public Incidencia insertar(Incidencia root, Incidencia newNode) {
        if (root == null) {
            return newNode;
        }
        if (newNode.getCodigo() < root.getCodigo()) {
            root.setIzquierda(insertar(root.getIzquierda(), newNode));
        } else {
            root.setDerecha(insertar(root.getDerecha(), newNode));
        }
        // Después de insertar siempre comprobamos si es necesario reordenar, reordenamos el root para hacer el recorrido completo
        return reordenar(root);
    }

    public Incidencia reordenar(Incidencia root) {
        if (root == null) {
            return null;
        }
        // Primero reordenamos los hijos empezando por la izquierda
        root.setIzquierda(reordenar(root.getIzquierda()));
        root.setDerecha(reordenar(root.getDerecha()));

        // Comprobamos el balance del nodo actual para saber si está balanceado para la izquierda o para la derecha
        // si el balance es 0 no reordenamos
        int balance = getBalance(root);
        if (balance > 1) {
            if (getBalance(root.getIzquierda()) < 0) {
                root.setIzquierda(rotacionIzquierda(root.getIzquierda()));
            }
            return rotacionDerecha(root);
        }
        if (balance < -1) {
            if (getBalance(root.getDerecha()) > 0) {
                root.setDerecha(rotacionDerecha(root.getDerecha()));
            }
            return rotacionIzquierda(root);
        }
        return root;
    }

    // Para rotar a la derecha seteamos la incidencia actual a la derecha de la izquierda del nodo actual y la derecha del nodo actual a la izquierda
    private Incidencia rotacionDerecha(Incidencia y) {
        var x = y.getIzquierda();
        var t2 = x.getDerecha();

        x.setDerecha(y);
        y.setIzquierda(t2);

        return x;
    }

    // Para rotar a la izquierda seteamos la incidencia actual a la izquierda de la derecha del nodo actual y la izquierda del nodo actual a la derecha
    private Incidencia rotacionIzquierda(Incidencia x) {
        var y = x.getDerecha();
        var t2 = y.getIzquierda();

        y.setIzquierda(x);
        x.setDerecha(t2);

        return y;
    }

    // Para saber el balance de un nodo miramos si tenemos la misma cantidad de nodos a la izquierda y a la derecha
    private int getBalance(Incidencia nodo) {
        if (nodo == null) {
            return 0;
        }

        var izquierda = nodo.getIzquierda() != null ? nodo.getIzquierda().calcularProfundidad() : 0;
        var derecha = nodo.getDerecha() != null ? nodo.getDerecha().calcularProfundidad() : 0;

        return izquierda - derecha;
    }
}
