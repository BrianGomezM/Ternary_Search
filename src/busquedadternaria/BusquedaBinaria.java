package busquedadternaria;

import javax.swing.JOptionPane;

public class BusquedaBinaria {

    public static String respuestaBusquedaBinaria = "";

    private static int[] reOrdenarArreglo(int[] arreglo, int pX, int pY) {
        int tamano = pX - pY;
        int[] arregloNuevo = new int[tamano + 1];
        respuestaBusquedaBinaria += "Elementos Obtenidos: \n";
        respuestaBusquedaBinaria += "( ";

        for (int i = 0; i <= tamano; i++) {
            arregloNuevo[i] = arreglo[pY + i];
            respuestaBusquedaBinaria += arregloNuevo[i] + " ";
            respuestaBusquedaBinaria += " ";
        }
        respuestaBusquedaBinaria += ")";
        respuestaBusquedaBinaria += "\n";
        return arregloNuevo;
    }

    private static void vectoresDescarte(int[] arreglo, int x) {
        respuestaBusquedaBinaria += "Elementos Eliminados: \n";
        respuestaBusquedaBinaria += "( ";
        for (int i = 0; i <= arreglo.length - 1; i++) {
            if (!(i == x)) {
                respuestaBusquedaBinaria += arreglo[i] + " ";
            }
        }
        respuestaBusquedaBinaria += ")";
        respuestaBusquedaBinaria += "\n";
    }

    private static void elementosEliminados(int arreglo[], int posicionI, int posicionF) {
        respuestaBusquedaBinaria += "Elemento eliminados: \n";
        respuestaBusquedaBinaria += "( ";
        for (int i = 0; i <= arreglo.length - 1; i++) {
            if (!((i >= posicionI) && (i <= posicionF))) {
                respuestaBusquedaBinaria += arreglo[i] + " ";
            }
        }
        respuestaBusquedaBinaria += ") ";
        respuestaBusquedaBinaria += "\n";
    }

    private static boolean busquedaB(int arreglo[], int posicionI, int posicionF, int BuscarE) {
        boolean resultado = false;
        if (posicionI < posicionF) {
            int partirArreglo2 = (posicionF + posicionI) / 2;
            if (BuscarE == arreglo[partirArreglo2]) {
                vectoresDescarte(arreglo, partirArreglo2);
                return true;
            } else if (BuscarE <= arreglo[partirArreglo2]) {
                elementosEliminados(arreglo, posicionI, partirArreglo2);
                int[] Nuevoarreglo = reOrdenarArreglo(arreglo, partirArreglo2, 0);
                return busquedaB(Nuevoarreglo, 0, Nuevoarreglo.length - 1, BuscarE);
            } else {
                elementosEliminados(arreglo, partirArreglo2, posicionF);
                int[] Nuevoarreglo = reOrdenarArreglo(arreglo, posicionF, partirArreglo2);
                return busquedaB(Nuevoarreglo, 0, Nuevoarreglo.length - 1, BuscarE);
            }
        }
        return resultado;
    }

    public void usarMetodos(String buscar) {
        long startTime = System.nanoTime();
        //int[] Vector = {1, 2, 3, 4, 5, 6};
        int[] Vector = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        //int[] Vector = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
       // int[] Vector = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60};
        int posicionInicial = 0;
        int posicionFinal = Vector.length - 1;
        int buscarElemento = Integer.parseInt(buscar);
        respuestaBusquedaBinaria += "Elemento a buscar: \n" + buscar + " \n";
        if (busquedaB(Vector, posicionInicial, posicionFinal, buscarElemento)) {
            respuestaBusquedaBinaria += "Elemento encontrado: \n" + buscar;
            JOptionPane.showMessageDialog(null, "Elemento encontrado");
        } else {
            respuestaBusquedaBinaria += "Elemento no encontrado en la lista : \n" + buscar + " \n";
            JOptionPane.showMessageDialog(null, "Error, el elemento no se encuentra en la lista");
        }
        long endTime = System.nanoTime();
        respuestaBusquedaBinaria += "\n \nTiempo de ejecucion: " + (endTime - startTime) / 1e6 + " MS";
    }

}
