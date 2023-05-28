package juego;
import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import entorno.Entorno;
import entorno.Herramientas;


public class Bala {
	double x;
	double y;
	int alto;
	int ancho;
	private double angulo;
	private double velocidad;
	private double escala;
	private Image img4;
	
	public Bala (double x,double y, double alto, double ancho, double velocidad) {
		this.angulo=0;
		this.escala=0.1;
		this.x=x;
		this.y=y;
		this.alto = 80;
		this.ancho = 6;
		this.velocidad=velocidad;
		this.img4= Herramientas.cargarImagen("disparo.png");
		
		//System.out.println(this.y);
		
	}
	
	public void rectangulo(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y, 6, 80, this.angulo, Color.magenta);
	}
	
	public void dibujar (Entorno entorno) {
		this.rectangulo(entorno);
		entorno.dibujarImagen(img4 , this.x, this.y, this.angulo, this.escala);
	}
	public double getY() {
		return this.y;
	}
	public void setY(double y) {
		this.y-=y;
		//System.out.println(this.y);
	}
	public void redibujar(Entorno entorno) {
		this.rectangulo(entorno);
		entorno.dibujarImagen(img4 , this.x, this.y, this.angulo, this.escala);
	}
	
	public void colision() {}
	
}
