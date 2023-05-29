package juego;
import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;


public class Ion {
	double x;
	double y;
	double alto;
	double ancho;
	double angulo;
	double velocidad;
	double escala;
	Image img5;
	
	public Ion(double x, double y, double alto, double ancho, double velocidad) {
		this.angulo = 0;
		this.escala= 0.1;
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.ancho = ancho;
		this.velocidad = velocidad;
		//this.img5 = Herramientas.cargarImagen("rayo22.png");
	}
	
	public void dibujar(Entorno entorno) {
		entorno.dibujarTriangulo(this.x, this.y, 6, 80, this.angulo, Color.yellow);
	}
	
	public double getY() {
		return this.y;
	}
	public double getX() {
		return this.x;
	}
	
	public void setY(double y) {
		this.y += y;
	}
	
}
