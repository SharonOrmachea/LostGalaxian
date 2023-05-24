package juego;
import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import entorno.Entorno;
import entorno.Herramientas;

public class Bala {
	private double x;
	private double y;
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
	
	/*
	public void chocasteConDestructor(Destructor destructor) {
		return (this.getY() > nave.naveGetX() - nave.naveAncho / 2) && 
				(this.getX() < nave.naveGetX() + nave.naveAncho / 2) && 
				(this.getY() > nave.naveGetY() - nave.naveGetY() / 2);
	}*/
	
	// Funcion para que colisione
	public boolean chocasteConDestructor(Destructor destructor) {
		return (this.getY() > destructor.destructorGetX() - destructor.destructorGetX() / 2) && 
				(this.getY() > destructor.destructorGetY() - destructor.destructorGetX() / 2);
	}
}
