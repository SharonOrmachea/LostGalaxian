package juego;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Destructor {
	private double x, y;
	private double angulo;
	private boolean disparando = false;
	private double escala;
	private Entorno entorno;
	private int entornoAncho;
	private int entornoAlto;
	private double destructorAncho;
	private Ion proyectil;
	private Image img1;
	// Para hacer un numero random entre 20 y 790;
	Random random = new Random();
	double randomX = random.nextDouble(790-20+1) + 25;
	
	// Constructor destructor
	public Destructor(Entorno entorno) {
		this.entorno = entorno;
		this.entornoAncho = entorno.ancho();
		this.entornoAlto = entorno.alto();
		this.x = 400;
		this.y = 560;
		this.img1 = Herramientas.cargarImagen("Destructor.png");
	}
	
	// Funcion que dibuja el destructor dentro del entorno
	public void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(img1, this.x, this.y, this.angulo, 0.1);
		System.out.println("Aparicion destructor");
	}
	
	// Getters de x e y de destructor
	public double destructorGetX() {
		return this.x;
	}
	
	public double destructorGetY() {
		return this.y;
	}
	
	// Setear los valores de x
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	// Funcion que mueve automaticamente a los destructores
	public void moverse() {
		// Para que se mueva a la derecha
		if(this.x+5+(this.destructorAncho/2) <= this.entornoAncho) {
			this.x = this.x+3;
			System.out.println("Movimiento derecho de destructor");
		}
		
		// Para que se mueva a la izquierda
		if(this.x-5-(this.destructorAncho/2) >= 0) {
			this.x = this.x - 3;
			System.out.println("Movimiento izquierdo de destructor");
		}
		
		// Para que se mueva para abajo
		if(this.y-5-(this.destructorAncho/2) <= this.entornoAlto) {
			this.y = this.y - 3;
		} else {
			this.redibujar(entorno);
		}
		
	}
	
	// Método para que el destructor dispare
	public void disparar() {
		if(!disparando) {
			this.disparando = true;
			this.proyectil = new Ion(this.destructorGetX(), this.destructorGetY()-60, 30, 50, 3);
		}
	}
	
	
	// Método para reiniciar posicion del Destructor si no muere y no colisiona con la Nave
	private void redibujar(Entorno entorno) {
		entorno.dibujarImagen(img1, this.x, this.y, this.angulo, this.escala);
	}
	
	
	public void borrarProyectil() {
		this.disparando = false;
		this.proyectil = null;
	}
	
}
