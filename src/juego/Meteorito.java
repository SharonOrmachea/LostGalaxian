package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;
import entorno.Entorno;
import entorno.Herramientas;


public class Meteorito {

	// Variables
	
	double x;
	double y;
	int ancho;
	int alto;
	double angulo;
	boolean exploto;
	Image img;
	
	Entorno entorno;
	Bala municion;
	Nave nave;
	
	
	public Meteorito(double x, double y) {
		this.x = x;
		this.y = y;
		this.ancho = 30;
		this.alto = 30;
		this.exploto = false;
		
		img = Herramientas.cargarImagen("Meteorito.png");
		
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public void girar() {
		this.angulo += 0.01;
	}
	
	Random random = new Random();
    double randomNumber = random.nextInt(2);
    
	
	public void caer(double x, double y) {
		
        this.y += Math.sin(2)*1;
        
        if(randomNumber == 1) {
        	this.x += Math.cos(this.angulo)*0.2;
        } else {
        	this.x -= Math.cos(this.angulo)*0.2;

        }
		
		if(this.y >= 650 || this.x >= 850 || this.x <= -10) {
			this.x = (int) (1400 - 1800*Math.random());
			this.y = 0;
		}	
		
	}
	
	public void circulo(Entorno entorno) {
		//entorno.dibujarCirculo(this.x, this.y, 34, Color.cyan);
		entorno.dibujarRectangulo(this.x, this.y, 30, 30, this.angulo, Color.pink);

	}
	
	public void dibujarse(Entorno entorno){
		//entorno.dibujarCirculo(this.x, this.y, 34, Color.black);
		this.circulo(entorno);
		entorno.dibujarImagen(img, this.x, this.y, this.angulo, 0.1);
		this.girar();
		this.caer(this.x, this.y);	

	}

	
	
	public void exploto() {
		img = Herramientas.cargarImagen("Meteorito-Explosion.png");
		this.exploto = true;
	}
	
}
