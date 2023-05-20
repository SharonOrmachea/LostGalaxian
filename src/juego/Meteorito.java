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
	Image img;
	
	Random random = new Random();
	int randomNumber = random.nextInt(2);
	
	public Meteorito(int x, int y) {
		this.x = x;
		this.y = y;
		img = Herramientas.cargarImagen("Meteorito.png");
		
		
		
	}
	
	public void girar() {
		this.angulo += 0.03;
	}
	
	public void caer(int randomNumber) {
		
		System.out.println(randomNumber);
		
		if(randomNumber == 0) {
			this.y += 1;
			this.x -= 1;
			
			if(this.y >= 650) {
				this.y = -50;
				this.x = 0;
			}			
		} else {
			this.y += 1;
			this.x += 1;
			
			if(this.y >= 650) {
				this.y = 50;
				this.x = 0;
			}
		}
	}
	
	public void dibujarse(Entorno entorno){
//		entorno.dibujarTriangulo(this.x, this.y, 50, 30, this.angulo, Color.yellow);
		
		entorno.dibujarImagen(img, this.x, this.y, this.angulo, 0.1);
		this.girar();
		this.caer(randomNumber);
		

	}

}
