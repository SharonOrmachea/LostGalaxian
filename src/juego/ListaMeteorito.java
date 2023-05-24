package juego;

import java.util.Random;

public class ListaMeteorito {
	
	Meteorito[] lista;
	
	public ListaMeteorito() {
		Random random = new Random();
		int randomNumber = random.nextInt(3) + 4;
		
		this.lista = new Meteorito[randomNumber];
		
		for (int i = 0; i < randomNumber; i++) {
			int randomNumberEjeX = random.nextInt(600);
			
			lista[i] = new Meteorito(randomNumberEjeX, 0);
		}
		
		sacarMeteorito();
		
		
	}
	
	public void sacarMeteorito() {
		for(int i = 0; i < this.lista.length; i++ ) {
			if(lista[i].exploto) {
				lista[i] = null;
			}
		}
	}
	
	

	

}
