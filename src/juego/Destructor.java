package juego;
import java.awt.Image;
import java.util.Random;

import javax.sound.sampled.Clip;

import entorno.Entorno;
import entorno.Herramientas;

public class Destructor {
	double x;
	double y;
	double angulo;
	boolean disparando;
	Entorno entorno;
	int alto;
	int ancho = 30;
	int velocidadRayo = 10;
	Ion proyectil;
	boolean exploto;
	private Clip DisparoDestructor;
	
	Image img3;
	
	// Constructor destructor
	public Destructor(double x, int y, Entorno entorno) {
		this.x = x;
		this.y = y;
		this.alto = 30;
		this.ancho = 30;
		this.entorno=entorno;
		this.disparando=false;
		img3 = Herramientas.cargarImagen("monstruo.png");
		this.exploto = false;
		this.DisparoDestructor=Herramientas.cargarSonido("shootdestructor.wav");
	}
	
	// Getters de x e y de destructor
	public double destructorGetX() {
		return this.x;
	}
	
	public double destructorGetY() {
		return this.y;
	}
	
	public int getAncho() {
		return this.ancho;
	}
	
	public int getAlto() {
		return this.alto;
	}
	
	
	// Setters de x y y.
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y += y;
	}
	
	
	Random random1 = new Random();
    double randomNumber = random1.nextInt(2);
	
    // Funcion que hace que los monstruos caigan
	public void caer(double x, double y) {
		
        this.y += Math.sin(2.5)*1;
        
        if(randomNumber == 1) {
        	this.x += Math.cos(this.angulo)*1;
        } else {
        	this.x -= Math.cos(this.angulo)*1;
        }
		
		if(this.y >= 650 || this.x >= 800 || this.x <= -10) {
			this.x = (int) (1400 - 1800*Math.random());
			this.y = 0;
		}	
		
	}
	
	// Funcion que hace girar al destructor
	public void girar() {
		this.angulo += 0.01;
	}
	
	// Funcion que dibuja al destructor
	public void dibujarse(Entorno entorno) {
			entorno.dibujarImagen(img3, this.x, this.y, this.angulo, 0.1);
			this.girar();
			this.caer(this.x, this.y);
	}
	
	// Funcion que cambia la trayectoria del destructor
	public void cambiarTrayectoria() {
		this.angulo += Math.PI/2;
	}
	
	
	// Método para que el destructor dispare
	public void disparar() {
		if(!disparando) {
			disparando=true;
			this.proyectil = new Ion(this.destructorGetX(), this.destructorGetY()+40, 40, 10, 3);
			this.proyectil.dibujar(this.entorno);
			
		}
	}
	
	// Funcion para mover el proyectil en este caso el ion
	public void moverProyectil() {
		if(disparando && enPantalla()) {
			this.proyectil.moverY(-2);
			this.proyectil.dibujar(this.entorno);
		}
	}
	
	
	// Funcion booleana para saber si estan en pantalla
	public boolean enPantalla () {
		if(this.proyectil.getY()-80>600) {
			borrarMunicion();
			return false;
		}else {
			return true;
		}
	}
	
	public void borrarMunicion() {
		this.disparando=false;
		this.proyectil=null;
	} 
	/*
	public boolean chocasteCon(Destructor destructor) {
		return (this.destructorGetX() > destructor.destructorGetX() - destructor.destructorGetX() / 2) &&
				(this.destructorGetX() < destructor.destructorGetX() + destructor.destructorGetX() / 2) &&
				(this.destructorGetY() > destructor.destructorGetY() - destructor.destructorGetY() /2); 
	}
	*/
	public boolean getDisparando() {
		return this.disparando;
	}
	
	public boolean hayColision(Destructor destructor) {
        // Verificar si hay colisión en el eje X
        boolean colisionX = this.x < destructor.destructorGetX() + destructor.getAlto() &&
                            this.x + this.ancho > destructor.destructorGetX();

        // Verificar si hay colisión en el eje Y
        boolean colisionY = this.y < destructor.destructorGetY() + destructor.getAlto() &&
                            this.y + this.alto > destructor.destructorGetY();

        // Retornar verdadero si hay colisión en ambos ejes
        return colisionX && colisionY;
    }
	
	public boolean colisionaConEntorno(Entorno entorno) {
		boolean colisionIzquierda = this.destructorGetX() < 0;
		boolean colisionDerecha = this.destructorGetX() + this.ancho > entorno.ancho();
		
		return colisionIzquierda || colisionDerecha;
		
	}
	
	public void exploto() {
		this.exploto = true;
	}
	
	public void sonidoDestructorDisparo() {
		DisparoDestructor.loop(1);
	}
	
}
