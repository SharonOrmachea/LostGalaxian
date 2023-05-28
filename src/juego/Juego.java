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
	
	private Destructor[] destructor;
	
	Bala municion;
	
	
	Random random = new Random();
	int randomNumber = random.nextInt(3) + 4;
	
	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Lost Galaxian, Sharon - Grupo 2 - v1", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		
		this.nave = new Nave(this.entorno);
		
		listaMeteoritos = new ListaMeteoritos();
		
		listaMeteorito();
		
		listaMonstruos();
		
		// Inicia el juego!
		this.entorno.iniciar();
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
	
	public void listaMonstruos() {
		int ejeY = -40;
		
		this.destructor = new Destructor[4];
		
		for(int i = 0; i < 4; i++) {
			int randomNumberEjeX = random.nextInt(600);
			this.destructor[i] = new Destructor(randomNumberEjeX, ejeY += 50);
		}
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
		
		for(int i = 0; i < this.listaMeteoritos.longitud; i++) {
			//listaMeteoritos.cabeza.meteorito.dibujarse(entorno);
			Nodo nodoActual = listaMeteoritos.cabeza;
	        while (nodoActual != null) {
	            // Haz algo con el nodo actual
	            nodoActual.meteorito.dibujarse(entorno);
	            // Avanza al siguiente nodo
	            nodoActual = nodoActual.siguiente; 
	        }
		}
		
		if(this.nave.disparando) {
			listaMeteoritos.colisionConMeteoritos(nave.municion);
					
		}
		
		for(int i = 0; i < this.destructor.length; i++) {
			destructor[i].dibujarse(entorno);
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
