package juego;

public class NodoMeteorito {
	Meteorito meteorito;
	NodoMeteorito siguiente;

    // Constructor
    public NodoMeteorito(Meteorito meteorito) {
        this.meteorito = meteorito;
        this.siguiente = null;
    }
}
