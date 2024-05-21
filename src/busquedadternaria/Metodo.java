package busquedadternaria;

import javax.swing.JOptionPane;

public class Metodo {

    public static String respuestaBusquedaTerciaria = "";

    private static int[] reOrdenarArreglo(int[] arreglo, int pX, int pY) {
        int tamano = pX - pY;
        int[] arregloNuevo = new int[tamano + 1];
        respuestaBusquedaTerciaria += "Elementos Obtenidos: \n";
        respuestaBusquedaTerciaria += "( ";

        for (int i = 0; i <= tamano; i++) {
            arregloNuevo[i] = arreglo[pY + i];
            respuestaBusquedaTerciaria += arregloNuevo[i] + " ";
            respuestaBusquedaTerciaria += " ";
        }
        respuestaBusquedaTerciaria += ")";
        respuestaBusquedaTerciaria += "\n";
        return arregloNuevo;
    }

    private static void vectoresDescarte(int[] arreglo, int pX, int pY) {
        respuestaBusquedaTerciaria += "Elementos Eliminados: \n";
        respuestaBusquedaTerciaria += "( ";
        for (int i = 0; i <= arreglo.length - 1; i++) {
            if (!((i >= pY) && (i <= pX))) {
                respuestaBusquedaTerciaria += arreglo[i] + " ";
            }
        }
        respuestaBusquedaTerciaria += ")";
        respuestaBusquedaTerciaria += "\n";
    }

    private static void vectoresDescarte(int[] arreglo, int x) {
        respuestaBusquedaTerciaria += "Elementos Eliminados: \n";
        respuestaBusquedaTerciaria += "( ";
        for (int i = 0; i <= arreglo.length - 1; i++) {
            if (!(i == x)) {
                respuestaBusquedaTerciaria += arreglo[i] + " ";
            }
        }
        respuestaBusquedaTerciaria += ")";
        respuestaBusquedaTerciaria += "\n";
    }

    private static boolean busquedaT(int[] arreglo, int posicionI, int posicionF, int BuscarE) {
        boolean resultado = false;
        if (posicionI < posicionF) { //Calcula si la posicion inicial es menor q la posicion final            
            int partirArreglo3 = (posicionF + posicionI) / 3;//Divide el tamaño del arreglo en 
            int particionArreglo2 = (partirArreglo3 + posicionF + 1) / 2;
            if (arreglo[partirArreglo3] == BuscarE) {
                vectoresDescarte(arreglo, partirArreglo3);
                resultado = true;
            } else {
                if (BuscarE < arreglo[partirArreglo3]) {
                    vectoresDescarte(arreglo, partirArreglo3, 0);
                    int[] Nuevoarreglo = reOrdenarArreglo(arreglo, partirArreglo3, 0);
                    return busquedaT(Nuevoarreglo, 0, Nuevoarreglo.length - 1, BuscarE);
                } else {
                    if (BuscarE == arreglo[particionArreglo2]) {
                        vectoresDescarte(arreglo, particionArreglo2);
                        resultado = true;
                    } else {
                        if (BuscarE < arreglo[particionArreglo2]) {
                            vectoresDescarte(arreglo, particionArreglo2, partirArreglo3 + 1);
                            int[] Nuevoarreglo = reOrdenarArreglo(arreglo, particionArreglo2, partirArreglo3 + 1);
                            return busquedaT(Nuevoarreglo, 0, Nuevoarreglo.length - 1, BuscarE);
                        } else {
                            if (BuscarE == arreglo[posicionF]) {
                                vectoresDescarte(arreglo, posicionF);
                                resultado = true;
                            } else {
                                vectoresDescarte(arreglo, posicionF, particionArreglo2);
                                int[] Nuevoarreglo = reOrdenarArreglo(arreglo, posicionF, particionArreglo2);
                                return busquedaT(Nuevoarreglo, 0, Nuevoarreglo.length - 1, BuscarE);
                            }
                        }
                    }
                }

            }
        }
        return resultado;
    }

    public void usarMetodos(String buscar) {
        long startTime = System.nanoTime();
        //int[] Vector = {1, 2, 3, 4, 5, 6};
        int[] Vector = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        //int[] Vector = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
        //int[] Vector = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60};
        int posicionInicial = 0;
        int posicionFinal = Vector.length - 1;
        int buscarElemento = Integer.parseInt(buscar);
        respuestaBusquedaTerciaria += "Elemento a buscar: \n" + buscar + " \n";
        if (busquedaT(Vector, posicionInicial, posicionFinal, buscarElemento)) {
            respuestaBusquedaTerciaria += "Elemento encontrado: \n" + buscar;
            JOptionPane.showMessageDialog(null, "Elemento encontrado");
        } else {
            respuestaBusquedaTerciaria += "Elemento no encontrado en la lista : \n" + buscar + " \n";
            JOptionPane.showMessageDialog(null, "Error, el elemento no se encuentra en la lista");
        }
        long endTime = System.nanoTime();
        respuestaBusquedaTerciaria += "\n \nTiempo de ejecucion: " + (endTime - startTime) / 1e6 + " MS";
    }
}
