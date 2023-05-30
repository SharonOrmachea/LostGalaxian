package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Nave 
{
	// Variables de instancia
		boolean disparando;
		int	entornoAncho;
		double naveX;
		double naveY;
		double naveAncho;
		double angulo;
		boolean destruida;
		Entorno entorno;
		Bala municion;
		Image img1;

	public Nave(Entorno entorno) 
	{
		this.disparando = false;
		this.entorno=entorno;
		this.entornoAncho=entorno.ancho();
		this.angulo=-1.5708;
		this.naveX = 400;
		this.naveY = 560;
		this.img1 = Herramientas.cargarImagen("nave.png");
		this.naveAncho = 30;
		this.destruida = false;
		
	}
	public double naveGetX() {
		return this.naveX;
	}
	
	public double naveGetY() {
		return this.naveY;
	}
	
	public void dibujarse(Entorno entorno)
	{
		entorno.dibujarTriangulo(this.naveX, this.naveY, 50, 30, this.angulo, Color.yellow);
		entorno.dibujarImagen(img1, this.naveX, this.naveY, 0, 0.2);	
	}
	
	
	public void moverDerecha() /* Mueve a nave a la derecha sin pasar del ancho de la ventana */
	{
		if(this.naveX+5+(this.naveAncho/2) <= this.entornoAncho)
			this.naveX=this.naveX+3;
			//System.out.println("derecha");
	}

	public void moverIzquierda() // Mueve a nave a la izquierda sin pasar del ancho de la ventana
	{
		if(this.naveX-5-(this.naveAncho/2) >= 0)
			this.naveX=this.naveX-3;
			//System.out.println("izquierda");
	}
	
	public void disparar () {
		if(!disparando) {
			this.disparando=true;
			this.municion=new Bala(this.naveGetX(),this.naveGetY()-60,30,50,3);
		}
	}
	
	public void moverDisparo() {
		if(disparando && enPantalla()) {
			this.municion.setY(10);
			this.municion.redibujar(this.entorno);
		}
	}
	
	public boolean enPantalla () {
		if(municion.getY()+20<0) {
			borrarMunicion();
			return false;
		}else return true;
	}
	
	public void borrarMunicion() {
		this.disparando=false;
		this.municion=null;
	}
	
	public void destruirNave() {
		this.img1 = Herramientas.cargarImagen("Nave-Explosion.png");
		this.destruida = true;
		System.out.println("Game Over");
	}
}