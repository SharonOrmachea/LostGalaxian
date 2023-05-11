package juego;


import entorno.Entorno;
import entorno.InterfaceJuego;

import java.util.Random;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	Meteorito[] meteorito;
	
	Random random = new Random();
	int randomNumber = random.nextInt(3) + 4;
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Lost Galaxian, Sharon - Grupo Alguno - v1", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		
		meteorito = new Meteorito[randomNumber];
		cantMeteorito();

		// Inicia el juego!
		this.entorno.iniciar();
		// System.out.println(randomNumber);
	}
	
	public void cantMeteorito() {
		int contador = 0;
		
		int x = 0;
		int y = 0;
		
		while(contador < meteorito.length) {
			x += 100;

			meteorito[contador] = new Meteorito(x, y);
			contador++;
		}
	}

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
		

	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
		
	}
}
