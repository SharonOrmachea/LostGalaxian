package juego;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Bala {
	double x;
	double y;
	int alto;
	int ancho;
	double angulo;
	double escala;
	Image img4;
	
	public Bala() {}
	
	public Bala (double x,double y, double alto, double ancho, double velocidad) {
		this.angulo=0;
		this.escala=0.1;
		this.x=x;
		this.y=y;
		this.alto = 80;
		this.ancho = 6;
		this.img4= Herramientas.cargarImagen("disparo.png");
		
	}
	
	public void dibujar (Entorno entorno) {
		entorno.dibujarImagen(img4 , this.x, this.y, this.angulo, this.escala);
	}
	public double getY() {
		return this.y;
	}
	public void setY(double y) {
		this.y-=y;
	}
	
	public void redibujar(Entorno entorno) {
		entorno.dibujarImagen(img4 , this.x, this.y, this.angulo, this.escala);
	}
	
	
	
}
