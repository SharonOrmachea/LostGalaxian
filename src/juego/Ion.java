package juego;
import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.*;
import juego.Nave;
import entorno.Entorno;
import entorno.Herramientas;


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
		this.escala= 1;
		this.x = x;
		this.y = y;
		this.alto = 50;
		this.ancho = 6;
		this.velocidad = velocidad;
		//this.img5 = Herramientas.cargarImagen("rayo22.png");
	}
	
	public void dibujar(Entorno entorno) {
		entorno.dibujarTriangulo(this.x, this.y, 80, 30, this.angulo, Color.yellow);
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
