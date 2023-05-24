package juego;
import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import entorno.Entorno;
import entorno.Herramientas;


public class Bala {
	double x;
	double y;
	private double alto;
	private double ancho;
	private double angulo;
	private double velocidad;
	private double escala;
	private Image img4;
	
	public Bala (double x,double y, double alto, double ancho, double velocidad) {
		this.angulo=0;
		this.escala=0.1;
		this.x=x;
		this.y=y;
		this.alto=alto;
		this.ancho=ancho;
		this.velocidad=velocidad;
		this.img4= Herramientas.cargarImagen("disparo.png");
		
		System.out.println(this.y);
		
	}
	public void dibujar (Entorno entorno) {
		entorno.dibujarImagen(img4 , this.x, this.y, this.angulo, this.escala);
	}
	public double getY() {
		return this.y;
	}
	public void setY(double y) {
		this.y-=y;
		System.out.println(this.y);
	}
	public void redibujar(Entorno entorno) {
		entorno.dibujarImagen(img4 , this.x, this.y, this.angulo, this.escala);
	}
	
}
