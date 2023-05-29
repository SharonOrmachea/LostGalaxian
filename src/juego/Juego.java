package juego;

import entorno.Entorno;
import entorno.InterfaceJuego;

import java.util.Random;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	private Nave nave;
	
	private ListaMeteoritos listaMeteoritos;
	// Lista enlazada
	private ListaDestructores destructores;
	
	
	private Bala municion;
	
	Random random = new Random();
	int randomNumber = random.nextInt(3) + 4;
	
	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Lost Galaxian, Sharon - Grupo 2 - v1", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		
		this.nave = new Nave(this.entorno);
		
		
		listaMeteoritos = new ListaMeteoritos();
		destructores = new ListaDestructores();
		
		listaMeteorito();
		listaDestructores();
		
		
		// Destructores
		// Inicia el juego!
		this.entorno.iniciar();
	}
	
	// LISTA METEORITOS
	public void listaMeteorito() {
		
		Random random = new Random();
		int randomNumber = random.nextInt(3) + 4;
		int ejeY = -50;
		
		for (int i = 0; i < randomNumber; i++) {
			int randomNumberEjeX = random.nextInt(600);
			Meteorito asteroide = new Meteorito(randomNumberEjeX, ejeY += 50);
			listaMeteoritos.agregarMeteorito(asteroide);
		}
		
	}
	
	// LISTA DESTRUCTORES
	public void listaDestructores() {
		int ejeY = -50;
		Random random = new Random();
		
		for(int i = 0; i < 4; i++) {
			int randomNumberEjeX = random.nextInt(600);
			Destructor destructor = new Destructor(randomNumberEjeX, ejeY += 50);
			destructores.agregarDestructor(destructor);
		}
		
	}
	
	
	// FUNCION COLISION
	public boolean colision2(double x1, double y1, double x2, double y2, double dist) {
		return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) < dist * dist;
	}
	
	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick() {
		// Procesamiento de un instante de tiempo
		// ...
		
		
		nave.dibujarse(entorno);
		
		// SE DIBUJA EL METEORITO Y SI HAY UNA COLISION LO QUITA
		for(int i = 0; i < this.listaMeteoritos.longitud; i++) {
			//listaMeteoritos.cabeza.meteorito.dibujarse(entorno);
			NodoMeteorito nodoActual = listaMeteoritos.cabeza;
	        while (nodoActual != null) {
	            // Haz algo con el nodo actual
	            nodoActual.meteorito.dibujarse(entorno);
	            
	            // Avanza al siguiente nodo
	            nodoActual = nodoActual.siguiente;
	            /*
	            if(this.nave.disparando) {
	            	if(colision2(nodoActual.meteorito.x, nodoActual.meteorito.y, this.nave.municion.x, this.nave.municion.y, 20)) {
	            		nodoActual.meteorito.exploto();
	            		listaMeteoritos.remove(nodoActual.meteorito);
	            		this.nave.borrarMunicion();
	            		System.out.println("Colision!!!!");
	            		break;
	            	}
	            }
	            */
	        }
		}
	
		
		// DESTRUCTOR QUE DIBUJA Y SI HAY UNA COLISION SE NOTA LA COLISION
		for(int i = 0; i < this.destructores.largo; i++) {
			NodoDestructor destructorActual = destructores.primero;
			
			while(destructorActual != null) {
				destructorActual.destructor.dibujarse(entorno);
				//destructorActual.destructor.disparar();
				//destructorActual.destructor.moverProyectil();
				
				destructorActual = destructorActual.siguiente;
				/*
				if(this.nave.disparando) {
					if(colision2(destructorActual.destructor.destructorGetX(), destructorActual.destructor.destructorGetY(), this.nave.municion.x, this.nave.municion.y, 20)) {
						destructores.quitar(destructorActual.destructor);
						this.nave.borrarMunicion();
					}
				}*/
			}
		}
		
		// COLISION ENTRE DESTRUCTORES
		/*
		for(int i = 0; i < this.destructores.largo; i++) {
			NodoDestructor destructorActual = destructores.primero;
			
			while(destructorActual != null) {
				NodoDestructor destructorActual1 = destructores.primero;
				while(destructorActual1 != null) {
					if(colision2(destructorActual.destructor.destructorGetX(), destructorActual.destructor.destructorGetY(), destructorActual1.destructor.destructorGetX(), destructorActual1.destructor.destructorGetY(), 20)) {
						destructorActual.destructor.cambiarTrayectoria();
						//destructorActual.destructor.girar();
					}
					destructorActual1 = destructorActual1.siguiente;
				}
				destructorActual = destructorActual.siguiente;
			}
		}*/
		
		for(int i = 0; i < this.destructores.largo; i++) {
			NodoDestructor destructorActual = destructores.primero;
			
			while(destructorActual != null) {
				NodoDestructor destructorActual1 = destructores.primero;
				while(destructorActual1 != null) {
					if(destructorActual.destructor.chocasteCon(destructorActual1.destructor)) {
						destructorActual.destructor.cambiarTrayectoria();
					}
					destructorActual1 = destructorActual1.siguiente;
				}
				destructorActual = destructorActual.siguiente;
			}
		}
		
		
		
		if (this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA)|| this.entorno.estaPresionada('a'))
			nave.moverIzquierda();
		if (this.entorno.estaPresionada(this.entorno.TECLA_DERECHA)|| this.entorno.estaPresionada('d'))
			nave.moverDerecha();
		if (this.entorno.sePresiono(entorno.TECLA_ESPACIO))
			nave.Disparar();
			nave.moverDisparo();
		
		// COLISION ENTRE METEORITO Y NAVE
		
			
		// COLISION ENTRE DESTRUCTOR Y/O ION CON NAVE
		
			
	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
		
	}
}
