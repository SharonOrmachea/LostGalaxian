package juego;
import entorno.Entorno;
import entorno.Herramientas;

import java.awt.Image;
//import java.awt.image.*;

public class Nave {
	private double x,y;
	private double ancho,alto;
	private Image img;
	
	
	public Nave(double x, double y, double ancho, double alto) {
	
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.img = Herramientas.cargarImagen("nave.png");
	}
	
	public void dibujar(Entorno e) {
		e.dibujarImagen(img, this.x, this.y, this.alto,this.ancho);
	}
	
	public void derecha() {
		this.x+=4;
	}
	public void izquierda() {
		this.x-=4;
	}

}
