package juego;

import entorno.Entorno;

public class ListaMeteoritos {
	
	Nodo cabeza;
	int longitud;
	Bala disparo;
	Entorno entorno;
	

    // Constructor
    public ListaMeteoritos() {
        this.cabeza = null;
        this.longitud = 0;
        this.disparo = new Bala();
        
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
    
    public boolean colision2(double x1, double y1, double x2, double y2, double dist) {
		return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) < dist * dist;
	}
    
    public boolean colisionMeteorito_Bala(Bala disparo) {
    	Nodo hashirama = cabeza;
    	
    	while(hashirama != null) {
    		if(colision2(hashirama.meteorito.x, hashirama.meteorito.y, disparo.x, disparo.y, 20)) {
    			System.out.println("putas hashirama esta aca");
    			
    			hashirama.meteorito.exploto();
    			
    			eliminarMeteorito(hashirama.meteorito);
    			return true;
    			
    		}
    		hashirama = hashirama.siguiente;
    		
    	}return false;
    }
    
    public void eliminarMeteorito(Meteorito meteorito) {
    	Nodo nodoActual = cabeza;
        Nodo nodoAnterior = null;
        
        // Buscar el nodo que contiene el objeto Meteorito a eliminar
        while (nodoActual != null) {
            if (nodoActual.meteorito == meteorito) {
                break;
            }
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.siguiente;
        }

        // Si se encuentra el nodo
        if (nodoActual != null) {
            // Si es el primer nodo de la lista
            if (nodoAnterior == null) {
            	cabeza = nodoActual.siguiente;
            } else {
            	nodoAnterior.siguiente = nodoActual.siguiente;
            }

            // Liberar la referencia al nodo eliminado
            nodoActual.siguiente = null;
        }
    }

}
