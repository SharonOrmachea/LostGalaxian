package juego;
import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
import juego.Nave;

public class Ion {
	private double x;
	private double y;
	private double alto;
	private double ancho;
	private double angulo;
	private double velocidad;
	private double escala;
	//Entorno entorno;
	public Ion(double x, double y, double alto, double ancho, double velocidad) {
		this.angulo = 0;
		this.escala= 1;
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.ancho = ancho;
		this.velocidad = velocidad;
		//this.entorno=entorno;
		
	}
	
	
	public void dibujar(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, this.angulo, Color.red);
	}
	
	public double getY() {
		return this.y;
	}
	public double getX() {
		return this.x;
	}
	
	public void moverY(double y) {
		this.y -= y;
	}
	
	
	// Metodo que podiamos hacer para que colisione con el entorno en caso de que no haya colisionado con la nave y que se redibuje
	/*
	public boolean chocasteConEntorno(Entorno e) {
		return x <= e.ancho() || y <= e.alto() || x >= e.ancho() - y ;
	}*/
	
	// Metodo que podriamos hacer para que colisione con nave
	/*
	public boolean chocasteConNave(Nave nave) {
		return (this.getX() > nave.naveGetX() - nave.naveAncho / 2) && 
				(this.getX() < nave.naveGetX() + nave.naveAncho / 2) && 
				(this.getY() > nave.naveGetY() - nave.naveGetY() / 2);
	}*/
	
	
}
