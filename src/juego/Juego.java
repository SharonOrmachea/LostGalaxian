package juego;

import entorno.Entorno;
import entorno.InterfaceJuego;

import java.util.Random;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	private Nave nave;
	
	private Meteorito[] asteroide;
	
	// private ListaMeteorito listaMeteorito;
	
	private Destructor[] destructor;
	
	private Bala municion;
	
	Random random = new Random();
	int randomNumber = random.nextInt(3) + 4;
	
	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Lost Galaxian, Sharon - Grupo 2 - v1", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		
		this.nave = new Nave(this.entorno);
		
		listaMeteorito();
		listaMonstruos();
		
		// Destructores
		// Inicia el juego!
		this.entorno.iniciar();
	}
	
	public void listaMeteorito() {
		
		Random random = new Random();
		int randomNumber = random.nextInt(3) + 4;
		int ejeY = -50;
		
		this.asteroide = new Meteorito[randomNumber];
		
		for (int i = 0; i < randomNumber; i++) {
			int randomNumberEjeX = random.nextInt(600);
			this.asteroide[i] = new Meteorito(randomNumberEjeX, ejeY += 50);
		}
		
		for(int i = 0; i < this.asteroide.length; i++ ) {
			if(asteroide[i].exploto) {
				asteroide[i] = null;
			}
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
		
		for(int i = 0; i < this.asteroide.length; i++ ) {
			asteroide[i].dibujarse(entorno);
		}
		
		for(int i = 0; i < this.destructor.length; i++) {
			destructor[i].dibujarse(entorno);
		}
		
		// No funciona xd
		for(Destructor monstruo : destructor) {
			monstruo.disparar();
			monstruo.moverDisparo();
		}
		
		if (this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA)|| this.entorno.estaPresionada('a'))
			nave.moverIzquierda();
		if (this.entorno.estaPresionada(this.entorno.TECLA_DERECHA)|| this.entorno.estaPresionada('d'))
			nave.moverDerecha();
		if (this.entorno.sePresiono(entorno.TECLA_ESPACIO))
			nave.Disparar();
			nave.moverDisparo();
		
		// Poner la colision para que cuando algun meteorito choque con la nave y asi se termina el juego
			
		// Poner la colision para que cuando algun monstruo choque con la nave y asi se termina el juego
		
		// Poner la colision de las balas asi si pega en algun monstruo o algun meteorito 
			
		// Poner la colision 
	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
		
	}
}
