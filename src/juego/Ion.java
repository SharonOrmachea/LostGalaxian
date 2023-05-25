package juego;
import java.awt.Color;
import java.awt.Image;
import juego.Nave;
import entorno.Entorno;
import entorno.Herramientas;


public class Ion {
	private double x;
	private double y;
	private double alto;
	private double ancho;
	private double angulo;
	private double velocidad;
	private double escala;
	private Image img5;
	
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
	
	public void dibujarse(Entorno entorno) {
		//entorno.dibujarImagen(img5, this.x, this.y, this.angulo, this.escala);
		entorno.dibujarTriangulo(this.x, this.y, 40, 20, this.angulo, Color.yellow);
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
	
	
	// Metodo que podriamos hacer para que colisione con nave
	public boolean chocasteConNave(Nave nave) {
		return (this.getX() > nave.naveGetX() - nave.naveAncho / 2) && 
				(this.getX() < nave.naveGetX() + nave.naveAncho / 2) && 
				(this.getY() > nave.naveGetY() - nave.naveGetY() / 2);
	}
	
	
}
