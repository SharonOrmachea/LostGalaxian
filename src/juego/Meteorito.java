package juego;

import java.awt.Image;
import java.util.Random;
import entorno.Entorno;
import entorno.Herramientas;


public class Meteorito {

	// Variables
	
	double x;
	double y;
	double angulo;
	boolean exploto;
	Image img;
	
	Bala municion;
	Nave nave;
	
	
	public Meteorito(double x, int y) {
		this.x = x;
		this.y = y;
		this.exploto = false;
		
		img = Herramientas.cargarImagen("Meteorito.png");
		
	}
	
	// GETTERS PARA OBTENER LOS VALORES DE Y Y X PARA HACER LA COLISION CON LA NAVE
	public double getY() {
		return this.y;
	}
	
	public double getX() {
		return this.x;
	}
	
	public void girar() {
		this.angulo += 0.03;
	}
	
	Random random = new Random();
    double randomNumber = random.nextInt(2);
    
	
	public void caer(double x, double y) {
		
        this.y += Math.sin(2)*1;
        
        if(randomNumber == 1) {
        	this.x += Math.cos(this.angulo)*4;
        } else {
        	this.x -= Math.cos(this.angulo)*4;

        }
		
		if(this.y >= 650 || this.x >= 850 || this.x <= -10) {
			this.x = (int) (1400 - 1800*Math.random());
			this.y = 0;
		}	
		
	}
	
	public void dibujarse(Entorno entorno){
		
		entorno.dibujarImagen(img, this.x, this.y, this.angulo, 0.1);
		this.girar();
		this.caer(this.x, this.y);	

	}
	
	public void exploto() {
		img = Herramientas.cargarImagen("Meteorito-Explosion.png");
		this.exploto = true;
	}
	
	/*
	public boolean chocaConNave(Nave nave) {
		return (this.destructorGetX() > nave.naveGetX() - nave.naveAncho / 2) &&
				(this.destructorGetX() < nave.naveGetX() + nave.naveAncho / 2) &&
				(this.destructorGetY() > nave.naveGetY() - nave.naveGetY() /2); 
	}*/
}
