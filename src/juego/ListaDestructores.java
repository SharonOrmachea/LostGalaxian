package juego;

public class ListaDestructores {
	
	NodoDestructor primero;
	int largo;
	
	public ListaDestructores() {
		this.primero = null;
		this.largo = 0;
	}
	
	public void agregarDestructor(Destructor destructor) {
        NodoDestructor nuevoNodo = new NodoDestructor(destructor);

        // Si la lista está vacía, el nuevo nodo se convierte en la cabeza
        if (primero == null) {
            primero = nuevoNodo;
            largo++;
        } else {
            // Si la lista no está vacía, recorremos hasta el último nodo y agregamos el nuevo nodo al final
            NodoDestructor nodoActual = primero;
            while (nodoActual.siguiente != null) {
                nodoActual = nodoActual.siguiente;
            }
            nodoActual.siguiente = nuevoNodo;
            largo++;
        }
    }
	
	public boolean colision2(double x1, double y1, double x2, double y2, double dist) {
		return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) < dist * dist;
	}
	
	public boolean colisionDestructorBala(Bala disparo) {
		NodoDestructor hashirama = primero;
    	
    	while(hashirama != null) {
    		if(colision2(hashirama.destructor.x, hashirama.destructor.y, disparo.x, disparo.y, 20)) {
    			hashirama.destructor.exploto();
    			quitar(hashirama.destructor);
    			return true;		
    		}
    		hashirama = hashirama.siguiente;
    		
    	}
    	return false;

    }
    
    public boolean colisionConNave(Nave nave) {
    	NodoDestructor hashirama = primero;
    	
    	while(hashirama != null) {
    		if(colision2(hashirama.destructor.x, hashirama.destructor.y, nave.naveX, nave.naveY, 50)) {
    			hashirama.destructor.exploto();
    			quitar(hashirama.destructor);
    			nave.destruirNave();
    			return true;
    		}
    		hashirama = hashirama.siguiente;
    	}
    	return false;

    }
	
	public boolean estaVacia() {
		if(this.primero == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public int largo() {
		// El numero de lista empieza en 0
		int l = 0;
		NodoDestructor n = this.primero;
		while(n != null) {
			l += 1;
			n = n.siguiente;
		}
		return l;
	}
	/*
	public void insertar(Destructor d) {
		
		NodoDestructor nuevo = new NodoDestructor(d);
		
		
		if(estaVacia()) {
			this.primero = nuevo;
			largo++;
		} else {
			NodoDestructor actual = this.primero;
			while(actual.siguiente != null) {
				actual = actual.siguiente;
			}
			actual.siguiente = actual;
			largo++;
		}
		
		
	}*/
	
	// Para quitar 
	void quitar(Destructor d) {
		NodoDestructor n = this.primero, anterior = null;
		while(n != null && n.destructor != d) {
			anterior = n;
			n = n.siguiente;
		}
		// Aca encontramos el elemento
		if(n != null) {
			if(anterior == null) {
				this.primero = n.siguiente;
			} else {
				anterior.siguiente = n.siguiente;
			}
		}
	}
		
	
	
}
