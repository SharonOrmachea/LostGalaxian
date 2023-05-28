package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Nave 
{
	// Variables de instancia
	public boolean disparando = false;
	private Entorno entorno;
	public Bala municion;
	private int	entornoAncho;
	private double naveX;
	private double naveY;
	public boolean vivo = true;
	public double naveAncho = 30;
	private double angulo;
	private Image  img1;
	private Image img2;
	private int velocidadDisparo = 10;
	

	public Nave(Entorno entorno) 
	{
		this.entorno=entorno;
		this.entornoAncho=entorno.ancho();
		this.angulo=-1.5708;
		this.naveX = 400;
		this.naveY = 560;
		this.img1 = Herramientas.cargarImagen("nave.png");
		//img2 = Herramientas.cargarImagen("navee.png");
		
	}
	public double naveGetX() {
		return this.naveX;
	}
	
	public double naveGetY() {
		return this.naveY;
	}
	
	public void dibujarse(Entorno entorno)
	{
		if(vivo) {
			entorno.dibujarTriangulo(this.naveX, this.naveY, 50, 30, this.angulo, Color.yellow);
			entorno.dibujarImagen(img1, this.naveX, this.naveY, 0, 0.2);
		}
	}
	
	
	public void moverDerecha() /* Mueve a nave a la derecha sin pasar del ancho de la ventana */
	{
		if(this.naveX+5+(this.naveAncho/2) <= this.entornoAncho)
			this.naveX=this.naveX+3;
			System.out.println("derecha");
			
	}

	public void moverIzquierda() // Mueve a nave a la izquierda sin pasar del ancho de la ventana
	{
		if(this.naveX-5-(this.naveAncho/2) >= 0)
			this.naveX=this.naveX-3;
			System.out.println("izquierda");
		
	}
	public void Disparar () {
		if(!disparando) {
			this.disparando=true;
			this.municion=new Bala(this.naveGetX(),this.naveGetY()+60,30,50,3);
		}
	}
	public void moverDisparo() {
		if(disparando && enPantalla()) {
			this.municion.setY(2);
			this.municion.redibujar(this.entorno);
		}
	}
	public boolean enPantalla () {
		if(this.municion.getY()+20<0) {
			borrarMunicion();
			return false;
		}else
			return true;
	}
	public void borrarMunicion() {
		this.disparando=false;
		this.municion=null;
	}
}