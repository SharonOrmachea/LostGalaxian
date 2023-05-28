package juego;

public class Nodo {
	Meteorito meteorito;
	Nodo siguiente;

    // Constructor
    public Nodo(Meteorito meteorito) {
        this.meteorito = meteorito;
        this.siguiente = null;
    }

}
