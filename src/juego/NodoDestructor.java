package juego;

public class NodoDestructor {
	
	Destructor destructor;
	NodoDestructor siguiente;
	
	
	public NodoDestructor(Destructor destructor) {
		this.destructor = destructor;
		this.siguiente = null;
	}
	
}
