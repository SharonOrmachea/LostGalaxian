package juego;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
//import java.awt.Color;
//import java.awt.Rectangle;
import java.util.Random;

import javax.sound.sampled.Clip;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	// Se crean los objetos de todas las imagenes
		Image gameover;
		Image fondo;
		Image end;
		Image intro;
		
		// Se crean los objetos de los audios necesarios
		Clip MusicaFondo;
		Clip Intro;
		Clip GameOver;
		
	// Se crean demas variables necesarias
		boolean enJuego;
		int yGameOver;
		int cantidadDeEnemigos;
		
	// Variables y métodos propios de cada grupo
	private Nave nave;
	
	private ListaMeteoritos listaMeteoritos;
	
	private ArrayList<Destructor> ListaDestructores = new ArrayList<>();
	
	Bala municion;
	
	//Vidas vidasNave;
	
	Random random = new Random();
	int randomNumber = random.nextInt(3) + 4;
	int contador;
	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Lost Galaxian, Sharon, Ian, Juan - Grupo 2 - v1", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// Se inician demas variables
			enJuego=false;
			yGameOver=-300;
			contador=0;
		// Se inician los objetos de imagen y sonido
			gameover=Herramientas.cargarImagen("retry.jpg");
			end=Herramientas.cargarImagen("level.jpg");
			fondo=Herramientas.cargarImagen("background.jpg");
			intro=Herramientas.cargarImagen("back (1).jpg");
			MusicaFondo=Herramientas.cargarSonido("Start Music - Galaga.wav");
			Intro=Herramientas.cargarSonido("Galaga Theme.wav");
			GameOver=Herramientas.cargarSonido("Game Over sound effect.wav");	
			
		//this.vidasNave = new Vidas (nave);
		
		this.nave = new Nave(this.entorno);
		
		listaMeteoritos = new ListaMeteoritos();
		
		listaMeteorito();
		listaDestructores();
		
		// Inicia el juego!
		this.entorno.iniciar();
	}

	
	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	
	
	public void tick() {
		// Procesamiento de un instante de tiempo
		iniciarJuego();
	}
	
	//Meteoritos -----------------------------------------------------------
	private void generarMeteoritos() {
		for(int i = 0; i < this.listaMeteoritos.longitud; i++) {
			Nodo nodoActual = listaMeteoritos.cabeza;
			while (nodoActual != null) {
				nodoActual.meteorito.dibujarse(entorno);
				nodoActual = nodoActual.siguiente; 
			}
		}
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
	
	//Destructores----------------------------------------------------------------------------
	public void listaDestructores() {
		int ejeY = -50;
		Random random = new Random();
		
		for(int i = 0; i < 4; i++) {
			int randomNumberEjeX = random.nextInt(600);
			Destructor destructor = new Destructor(randomNumberEjeX, ejeY += 50, entorno);
			ListaDestructores.add(destructor);
			destructor.dibujarse(entorno);
			destructor.disparar();
			destructor.moverProyectil();
		}	
	}

	
	public void dibujarDestructor() {
		for(int i = 0; i < this.ListaDestructores.size(); i++) {
					
			for(int j = 0; j < this.ListaDestructores.size(); j++) {
				if(ListaDestructores.get(i).hayColision(ListaDestructores.get(j))) {
					ListaDestructores.get(i).cambiarTrayectoria();
				}
			}
					
		}
	}
	
	public void disparoDestructor() {
		for(int i = 0; i < this.ListaDestructores.size(); i++) {
			ListaDestructores.get(i).dibujarse(entorno);
			if(ListaDestructores.get(i).getDisparando()) {
				ListaDestructores.get(i).sonidoDestructorDisparo();
				ListaDestructores.get(i).moverProyectil();
				if(ListaDestructores.get(i).proyectil != null && ListaDestructores.get(i).colision2(ListaDestructores.get(i).proyectil.x, ListaDestructores.get(i).proyectil.y, nave.naveX, nave.naveY, 20)) {
					ListaDestructores.get(i).colisionProyectilNave(nave);
					ListaDestructores.get(i).borrarMunicion();
					nave.restarVida();
					nave.posicionInicial();
				}
			}
		}
	
	}
	
	
	// Nuestra Nave -------------------------------------------------------------------------------------------------------------------
	public void naveMovimiento() {
		if(!nave.destruida) {
			if (this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA) || this.entorno.estaPresionada('a'))
				nave.moverIzquierda();
			if (this.entorno.estaPresionada(this.entorno.TECLA_DERECHA) || this.entorno.estaPresionada('d'))
				nave.moverDerecha();
			if (this.entorno.sePresiono(entorno.TECLA_ESPACIO))
				nave.disparar();
				nave.moverDisparo();	
		}
	}
	
	public void borrarMunicionNave() {
		for(int i = 0; i < ListaDestructores.size(); i++) {
			if(this.nave.disparando) {
				if(ListaDestructores.get(i).colisionDestructorBala(nave.municion)){
					nave.borrarMunicion();
					ListaDestructores.remove(ListaDestructores.get(i));
					this.cantidadDeEnemigos++;
				}
			}
		}
	}
	/*
	public void restarVida() {
		if(listaMeteoritos.colisionConNave(nave)) {
			nave.restarVida();
			nave.posicionInicial();
		}
	
		/*
		if(destructores.colisionConNave(nave)) {
			nave.restarVida();
			nave.posicionInicial();
		}
	}*/
	
	//Juego -----------------------------------------------------------------------------------------------
	public void iniciarJuego() {
		if(!enJuego)								// Si el juego no ha comenzado
		{
			mostrarIntro();
		}	
		else										// de lo contrario
		{
			
			contador++;
			if(!jugadorGano()) {
				if(nave.tieneVidas()) {
						
					//vidasNave.dibujar(nave, entorno);										// Se dibuja en pantalla la cantidad de vidas del jugador
					MusicaFondo.loop(100000);												// Inicia el audio de fondo			
					entorno.dibujarImagen(fondo, entorno.ancho()/2, entorno.alto()/2, 0);	//dibuja el fondo
					
					entorno.cambiarFont("Arial black", 20, Color.green);					// Se cambia la tipografia
					entorno.escribirTexto("Kills " + this.cantidadDeEnemigos, 350, 20);		//contador de kills
					
				
					nave.dibujarse(entorno);
					naveMovimiento();
					generarMeteoritos();
					dibujarDestructor();
					//restarVida();
					borrarMunicionNave();
					disparoDestructor();
				}
				else {
					gameOver();	//juego terminado
				}
			}
			else {
				nivelCompletado();
			}
		}
	}
	
	private void mostrarIntro()
	{
		Intro.loop(1);															// Reproducir el audio de Intro
		entorno.dibujarImagen(intro, entorno.ancho()/2, entorno.alto()/2, 0);	// Muestra la imagen del Intro en pantalla
		if(entorno.estaPresionada(entorno.TECLA_ENTER))							// Si el jugador presiona enter
			{
			enJuego=true;														// Se inicia el juego
			Intro.stop();														// Se detiene la musica del intro
			}
		
	}
	
	private void gameOver() 
	{
		if (yGameOver<300)															// Mientras la imagen de game over haciendo bajar la imagen
			yGameOver=yGameOver+5;													// ir bajandola 5px por Tick
		entorno.dibujarImagen(gameover, (entorno.ancho()/2), yGameOver, 0);			// Dibujar la imagen en la posicion establecida
		MusicaFondo.stop();															// Detener la musica del juego
		GameOver.start();															// Iniciar el audio de GameOver
		entorno.cambiarFont("Arial black", 18, Color.green);						// Cambiar la tipografia
		entorno.escribirTexto(cantidadDeEnemigos + " enemigos eliminados", 50, 595);	// Se muestra el numero del nivel Completado
			
		if(entorno.estaPresionada(entorno.TECLA_ENTER))								// Si el jugador presiona la tecla enter
		{
			reiniciar();															// Se reinicia el juego
			cantidadDeEnemigos=0;														// Se reinicia el contador de enemigos
		}
		
	}
	
	private void reiniciar()
	{
		GameOver.stop();					// Detiene el audio de GameOver
		nave.reestablecerVidas();		// Reestablece la cantidad de vidas del jugador
		nave.posicionInicial();			// Setea al jugador en la posicion inicial
		MusicaFondo.loop(100000);			// Inicia la reproduccion de la musica de fondo
		this.contador=0;
		this.randomNumber=1;					// Reinicia los valores de las variables
		this.yGameOver=-300;
		
		generarMeteoritos();
		dibujarDestructor();
		
		//generarDestructores();
		
	}
	
	private void nivelCompletado()
	{
		
		entorno.dibujarImagen(end, entorno.ancho()/2, entorno.alto()/2, 0);	// Dibuja la imagen en el centro de la pantalla
		entorno.cambiarFont("Arial black", 18, Color.green);						// Cambiar la tipografia
		entorno.escribirTexto(cantidadDeEnemigos + " enemigos eliminados", 50, 595);	// Se muestra el numero del nivel Completado
		if(entorno.estaPresionada(entorno.TECLA_ENTER))						// Si el jugador presiona enter
		{										
			reiniciar();	// Reiniciar el juego
			cantidadDeEnemigos=0;
		}
		
		
	}
	
	private boolean jugadorGano() {
		if(listaMeteoritos.cabeza==null&& ListaDestructores.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}

		
		@SuppressWarnings("unused")
		public static void main(String[] args) {
			Juego juego = new Juego();
		
		}
	}
