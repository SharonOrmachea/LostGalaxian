package juego;
import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Destructor {
	private double x, y;
	private double angulo;
	private boolean disparando = false;
	private double ancho = 800;
	private double alto = 500;
	Entorno entorno;
	private int entornoAncho;
	private double destructorAncho;
	private Ion proyectil;
	Image img;
	// Para hacer un numero random entre 20 y 790;
	Random random = new Random();
	double randomX = random.nextDouble(790-20+1) + 25;
	
	// Constructor destructor
	public Destructor(Entorno entorno) {
		this.entorno = entorno;
		this.entornoAncho = entorno.ancho();
		this.x = x;
		this.y = 700;
		//img = Herramientas.cargarImagen("destructor2");
	}
	
	// Getters de x e y de destructor
	public double destructorGetX() {
		return this.x;
	}
	
	public double destructorGetY() {
		return this.y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	// Funcion que mueve automaticamente a los destructores
	public void moverse(double x, double y) {
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
		if(this.y-5-(this.destructorAncho/2) <= this.alto) {
			this.y = this.y - 3;
		} else {
			this.dibujarse(entorno);
		}
		
	}
	
	// Método para que el destructor dispare
	public void disparar() {
		if(!disparando) {
			this.disparando = true;
			this.proyectil = new Ion(this.destructorGetX(), this.destructorGetY()+60, 30, 50, 3);
		}
	}
	
	// Funcion que dibuja el destructor dentro del entorno
	public void dibujarse(Entorno entorno) {
			//entorno.dibujarImagen(img, this.x, this.y, angulo, alto);
			this.moverse(this.x, this.y);
			System.out.println("Aparicion destructor");
	}
		
	public void moverDisparo() {
		if(disparando && enPantalla()) {
			this.proyectil.setY(2);
			this.proyectil.redibujar(this.entorno);
		}
	}
	public boolean enPantalla () {
		if(proyectil.getY()+20<0) {
			borrarProyectil();
			return false;
		}else
			return true;
	}
	
	public void borrarProyectil() {
		this.disparando = false;
		this.proyectil = null;
	}
	
}
