package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Vidas 
{

	// Variables de Objeto
	
	private Image CRojo;
	private Image CGris;
	private int X;
	private int Y;
	private double escala;
	private int separacion;
	
	
	public Vidas(Nave nave) 
	{
		this.CRojo=Herramientas.cargarImagen("CorazonRojo.png");
		this.CGris=Herramientas.cargarImagen("CorazonGris.png");
		this.X=650;
		this.Y=30;
		this.escala= 0.25;
		this.separacion=60;
	}


	public void dibujar(Nave nave, Entorno entorno) 
	{
		for(int i=0; i<3;i++)
		{
			if(nave.getVidas()>i)
				entorno.dibujarImagen(CRojo, X+(this.separacion*i), this.Y, 0, this.escala);
			else
				entorno.dibujarImagen(CGris, X+(this.separacion*i), this.Y, 0, this.escala);
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
