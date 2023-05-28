package juego;

public class ListaMeteoritos {
	
	Nodo cabeza;
	int longitud;
	
	

    // Constructor
    public ListaMeteoritos() {
        this.cabeza = null;
        this.longitud = 0;
    }

    public void agregarMeteorito(Meteorito meteorito) {
        Nodo nuevoNodo = new Nodo(meteorito);

        // Si la lista está vacía, el nuevo nodo se convierte en la cabeza
        if (cabeza == null) {
            cabeza = nuevoNodo;
            longitud++;
        } else {
            // Si la lista no está vacía, recorremos hasta el último nodo y agregamos el nuevo nodo al final
            Nodo nodoActual = cabeza;
            while (nodoActual.siguiente != null) {
                nodoActual = nodoActual.siguiente;
            }
            nodoActual.siguiente = nuevoNodo;
            longitud++;
        }
    }
    /*
    public void eliminarNodo(Meteorito meteorito) {
        if (cabeza == null) {
            // La lista está vacía
            return;
        }

        if (cabeza.meteorito == meteorito) {
            // El nodo a eliminar es la cabeza de la lista
            cabeza = cabeza.siguiente;
            return;
        }

        Nodo nodoActual = cabeza;
        while (nodoActual.siguiente != null) {
            if (nodoActual.siguiente.meteorito == meteorito) {
                // Encontramos el nodo a eliminar
                nodoActual.siguiente = nodoActual.siguiente.siguiente; 
                return;
            }
            nodoActual = nodoActual.siguiente;
        }
    }*/
    
    public void remove(Meteorito meteorito) {
        Nodo current = cabeza;
        //Nodo prev = null;

        // Buscar el nodo que contiene el objeto Meteorito a eliminar
        while (current != null) {
            if (current.meteorito == meteorito) {
                break;
            }
            //prev = current;
            current = current.siguiente;
        }

        // Si se encuentra el nodo
            

            // Liberar la referencia al nodo eliminado
            current.siguiente = null;
        
    }



    // Método para imprimir los elementos de la lista
    public void imprimirLista() {
        Nodo nodoActual = cabeza;
        while (nodoActual != null) {
            System.out.print(nodoActual.meteorito + " ");
            nodoActual = nodoActual.siguiente;
        }
        System.out.println();
    }
    
    public int length(){
    	return longitud;
    }
    
    
}
