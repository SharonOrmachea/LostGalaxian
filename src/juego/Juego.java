package juego;


import entorno.Entorno;
import entorno.InterfaceJuego;

import java.util.Random;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	private Nave nave;
	Meteorito[] meteorito;
	// Destructor[] destructor;
	
	Random random = new Random();
	int randomNumber = random.nextInt(3) + 4;
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Lost Galaxian, Sharon - Grupo 2 - v1", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		
		meteorito = new Meteorito[randomNumber];
		cantMeteorito();
		// Destructores
		Destructor destructor1 = new Destructor(this.entorno);
		destructor1.setX(20);
		Destructor destructor2 = new Destructor(this.entorno);
		destructor2.setX(40);
		Destructor destructor3 = new Destructor(this.entorno);
		destructor3.setX(60);
		Destructor destructor4 = new Destructor(this.entorno);
		destructor4.setX(80);
		
		
		this.nave = new Nave(this.entorno.ancho()/2, this.entorno.alto()/1.1,0.2,0.2);

		// Inicia el juego!
		this.entorno.iniciar();
		// System.out.println(randomNumber);
	}
	
	public void cantMeteorito() {
		int contador = 0;
		
		int x = 0;
		int y = 0;
		
		while(contador < meteorito.length) {
			x += 50;

			meteorito[contador] = new Meteorito(x, y);
			contador++;
		}
	}
	
	/*
	public void cantDestructores() {
		int contador = 0;
		double x = 0;
		double y = 0;
		
		
		while(contador < destructor.length) {
			x += 50;
			destructor[contador] = new Destructor(entorno);
			contador++;
		}
	}*/

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		// Procesamiento de un instante de tiempo
		// ...
		// meteorito.dibujarse(entorno);
		
		for(int i = 0; i < meteorito.length; i++) {
			meteorito[i].dibujarse(entorno);
		}
		
		/*
		for(int i = 0; i < destructor.length; i++) {
			destructor[i].dibujarse(entorno);
		}*/
		destructor1.dibujarse(entorno);
		nave.dibujar(entorno);
		
		if(entorno.estaPresionada(entorno.TECLA_DERECHA)) {
			nave.derecha();
		}
		if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
			nave.izquierda();
		}

	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
		
	}
}
