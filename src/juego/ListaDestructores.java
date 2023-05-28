package juego;
import entorno.Entorno;

public class ListaDestructores {
	
	NodoDestructor primero;
	int largo;
	
	public ListaDestructores() {
		this.primero = null;
		this.largo = 0;
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
		
		
	}
	
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
