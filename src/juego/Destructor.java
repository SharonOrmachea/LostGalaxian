package juego;
import java.awt.Color;
import java.awt.Image;
import java.util.Random;
import entorno.Entorno;
import entorno.Herramientas;

public class Destructor {
	private double x, y;
	private double angulo;
	private boolean disparando = true;
	private double ancho = 800;
	private double alto = 500;
	private boolean exploto;
	Entorno entorno;
	private int entornoAncho;
	private double destructorAncho = 500;
	private Ion proyectil;
	Image img;
	
	// Constructor destructor
	public Destructor(double x, int y) {
		this.x = x;
		this.y = y;
		this.exploto = false;
		img = Herramientas.cargarImagen("monstruo.png");
	}
	
	// Getters de x e y de destructor
	public double destructorGetX() {
		return this.x;
	}
	
	public double destructorGetY() {
		return this.y;
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
	
	public void girar() {
		this.angulo += 0.01;
	}
	
	// Funcion 
	public void dibujarse(Entorno entorno) {
			entorno.dibujarImagen(img, this.x, this.y, this.angulo, 0.1);
			//this.moverse(this.x, this.y);
			this.girar();
			this.caer(this.x, this.y);
			System.out.println("Aparicion destructor");
	}
	
	// Método para que el destructor dispare
	public void disparar() {
		if(disparando) {
			this.disparando = true;
			this.proyectil = new Ion(this.destructorGetX(), this.destructorGetY()-50, 30, 50, 3);
		}
	}

	public void moverProyectil() {
		if(disparando && enPantalla()) {
			this.proyectil.setY(2);
			this.proyectil.dibujarse(this.entorno);
		}
	}
	
	
	
	public boolean enPantalla () {
		if(this.proyectil.getY()+20<800) {
			borrarMunicion();
			return false;
		}else
			return true;
	}
	
	public void borrarMunicion() {
		this.disparando=false;
		this.proyectil=null;
	} 
	
	public boolean chocaConNave(Nave nave) {
		return (this.destructorGetX() > nave.naveGetX() - nave.naveAncho / 2) &&
				(this.destructorGetX() < nave.naveGetX() + nave.naveAncho / 2) &&
				(this.destructorGetY() > nave.naveGetY() - nave.naveGetY() /2); 
	}
	
	public boolean chocaConOtroDestructor(Destructor destructor) {
		return(this.destructorGetX() > destructor.destructorGetX() - destructor.ancho / 2) && 
				(this.destructorGetX() < destructor.destructorGetX() + destructor.ancho / 2) &&
					(this.destructorGetY() > destructor.destructorGetY() - destructor.destructorGetY() / 2);
	}
	
	
}
