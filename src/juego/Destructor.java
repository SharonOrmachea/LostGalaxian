package juego;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Destructor {
	private double x, y;
	private double angulo;
	Image img;
	// Para hacer un numero random entre 20 y 790;
	Random random = new Random();
	double randomX = random.nextDouble(790-20+1) + 25;
	
	
	public Destructor(double x, double y) {
		this.x = x;
		this.y = y;
		img = Herramientas.cargarImagen("Destructor.png");
	}
	
	public void dibujarse(Entorno entorno) {
		
		entorno.dibujarImagen(img, this.x, this.y, this.angulo, 0.1);
		this.moverse();
		this.disparar();
	}
	
	public void moverse() {
		this.x -= 4;
		this.y -= 4;
		
		// Para el eje x
		if(this.x <= 20) {
			this.x += 4;
		} 
		if(this.x >= 790) {
			this.x -= 4;
		}
		
		// Para el eje y si igualamos la posicion y, reiniciamos la posicion.
		if(this.y <= 20) {
			this.reiniciarPosicion();
		}
		
	}
	
	// Método para que el destructor dispare
	public void disparar() {}
	
	
	// Método para reiniciar posicion del Destructor si no muere y no colisiona con la Nave
	private void reiniciarPosicion() {
		this.x = randomX;
		this.y = 590;
	}
	
	public void colisionConOtroDestructor() {
		
	}
	
	// Metodo para obtener el eje x del destructor
	public double getX() {
		return this.x;
	}
	
	// Metodo para obtener el eje y del destructor
	public double getY() {
		return this.y;
	}
	
	
	
}
