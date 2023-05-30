package juego;

import entorno.Entorno;
import entorno.InterfaceJuego;

//import java.awt.Color;
//import java.awt.Rectangle;
import java.util.Random;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	private Nave nave;
	
	private ListaMeteoritos listaMeteoritos;
	
	//private Destructor[] destructor;
	
	private ListaDestructores destructores;
	
	Bala municion;
	
	
	Random random = new Random();
	int randomNumber = random.nextInt(3) + 4;
	int contador;
	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Lost Galaxian, Sharon - Grupo 2 - v1", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		contador=0;
		this.nave = new Nave(this.entorno);
		
		listaMeteoritos = new ListaMeteoritos();
		destructores = new ListaDestructores();
		
		listaMeteorito();
		listaDestructores();
		
		// Inicia el juego!
		this.entorno.iniciar();
	}
	public Nave getNave() {
		return this.nave;
	}
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
			Destructor destructor = new Destructor(randomNumberEjeX, ejeY += 50, entorno);
			destructores.agregarDestructor(destructor);
		}		
	}
	
	
	public void disparoDestructor() {
		System.out.println("inicio disparo");
		NodoDestructor actual = destructores.primero;
		
		while(actual != null) {
			actual.destructor.disparar();
			actual.destructor.moverProyectil();
			actual = actual.siguiente;
		}
	
	}
	
	public boolean colision(double x1, double y1, double x2, double y2, double dist) {
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
		contador++;
		//System.out.println(contador);
		
		nave.dibujarse(entorno);
		
		for(int i = 0; i < this.listaMeteoritos.longitud; i++) {
			//listaMeteoritos.cabeza.meteorito.dibujarse(entorno);
			Nodo nodoActual = listaMeteoritos.cabeza;
	        while (nodoActual != null) {
	            // Haz algo con el nodo actual
	            nodoActual.meteorito.dibujarse(entorno);
	            /*
	            if(colision(nodoActual.meteorito.x, nodoActual.meteorito.y, nave.naveGetX(), nave.naveGetY(), 1)) {
	            	System.out.println("METEORITO COLISIONA CON NAVE");
	            }*/
	            // Avanza al siguiente nodo
	            nodoActual = nodoActual.siguiente; 
	        }
		}
		
		
		
		disparoDestructor();
		if(this.nave.disparando) {
			if(listaMeteoritos.colisionMeteorito_Bala(nave.municion)){
				nave.borrarMunicion();
			}		
		}
		
		
		
		for(int i = 0; i < this.destructores.largo; i++) {
			NodoDestructor destructorActual = destructores.primero;
				
			while(destructorActual != null) {
				destructorActual.destructor.dibujarse(entorno);
				if(destructorActual.destructor.getDisparando()) {
					destructorActual.destructor.moverProyectil();
				}
				if(destructorActual.destructor.colisionaConEntorno(entorno)) {
					destructorActual.destructor.cambiarTrayectoria();
				}
				
				destructorActual = destructorActual.siguiente;
				}
		}
			/*
			while(destructorActual != null) {
				NodoDestructor actual1 = destructores.primero;
				while(actual1 != null) {
					if(destructorActual.destructor.hayColision(actual1.destructor)) {
						System.out.println("COLISIOOOOOOOOOOON");
						destructorActual.destructor.cambiarTrayectoria();
					}
					actual1 = actual1.siguiente;
				}
				destructorActual = destructorActual.siguiente;
			}
		*/
		
		for(int i = 0; i < this.destructores.largo; i++) {
			NodoDestructor actual = destructores.primero;
			
			while(actual != null) {
				NodoDestructor actual1 = destructores.primero;
				while(actual1 != null) {
					if(actual.destructor.hayColision(actual1.destructor)) {
						System.out.println("COLISIOOOOOOOOOOON");
						actual.destructor.cambiarTrayectoria();
					}
					actual1 = actual1.siguiente;
				}
				actual = actual.siguiente;
			}
		}
		
		if (this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA)|| this.entorno.estaPresionada('a'))
			nave.moverIzquierda();
		if (this.entorno.estaPresionada(this.entorno.TECLA_DERECHA)|| this.entorno.estaPresionada('d'))
			nave.moverDerecha();
		if (this.entorno.sePresiono(entorno.TECLA_ESPACIO))
			nave.disparar();
			nave.moverDisparo();
		
			
	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
		
	}
}
