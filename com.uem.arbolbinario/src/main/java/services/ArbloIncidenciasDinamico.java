package services;

import domain.Incidencia;

public class ArbloIncidenciasDinamico {

    public Incidencia insertar(Incidencia root, Incidencia newNode) {
        if (root == null) {
            return newNode;
        }
        if (newNode.getCodigo() < root.getCodigo()) {
            root.setIzquierda(
                    insertar(root.getIzquierda(), newNode)
            );
        } else {
            root.setDerecha(
                    insertar(root.getDerecha(), newNode)
            );
        }
        return reordenar(root);
    }

    public Incidencia reordenar(Incidencia root) {
        if (root == null) {
            return null;
        }
        root.setIzquierda(reordenar(root.getIzquierda()));
        root.setDerecha(reordenar(root.getDerecha()));

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

    private Incidencia rotacionDerecha(Incidencia y) {
        var x = y.getIzquierda();
        var t2 = x.getDerecha();

        x.setDerecha(y);
        y.setIzquierda(t2);

        return x;
    }

    private Incidencia rotacionIzquierda(Incidencia x) {
        var y = x.getDerecha();
        var t2 = y.getIzquierda();

        y.setIzquierda(x);
        x.setDerecha(t2);

        return y;
    }

    private int getBalance(Incidencia nodo) {
        if (nodo == null) {
            return 0;
        }

        var izquierda = nodo.getIzquierda() != null ? nodo.getIzquierda().calcularProfundidad() : 0;
        var derecha = nodo.getDerecha() != null ? nodo.getDerecha().calcularProfundidad() : 0;

        return izquierda - derecha;
    }
}
