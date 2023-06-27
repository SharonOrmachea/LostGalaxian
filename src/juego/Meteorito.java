package juego;

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
	double velX;
	double velY;
	double direccionX;
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
		this.velX = 0.2;
		this.velY = 1;
		this.direccionX = 1;
		img = Herramientas.cargarImagen("Meteorito.png");
		
	}
	
	public void girar() {
		this.angulo += 0.01;
	}
	
	Random random = new Random();
    double randomNumber = random.nextInt(2);
    
	
	public void caer(double x, double y) {
		
		this.y += velY;
		
		if (this.y >= 650 || this.x >= 850 || this.x <= -10) {
			this.x = (int) (1400 - 1800*Math.random());
            this.y = -30; // Regenerar en la parte superior de la pantalla

            // Calcular dirección aleatoria
            if (Math.random() > 0.5) {
                this.velX *= -1; // Invertir dirección si el número aleatorio es mayor a 0.5
            }
        }
		this.x += velX;
		
	}

	
	public void dibujarse(Entorno entorno){
		//entorno.dibujarCirculo(this.x, this.y, 34, Color.black);
		if(!this.exploto) {
			entorno.dibujarImagen(img, this.x, this.y, this.angulo, 0.1);
			this.girar();
			this.caer(this.x, this.y);	
		} else {
			entorno.dibujarImagen(img, this.x, this.y, 0, 0);
			
		}
	}
	
	public void exploto() {
		this.exploto = true;
	}
	
	
	
}
