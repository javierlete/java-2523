package uf2216.threads;

import java.util.Random;

public class Corredor implements Runnable {
	private final String dorsal;
	private int posicion;
	private boolean hayQueParar = false;
	
	public Corredor(String dorsal) {
		this.dorsal = dorsal;
	}

	@Override
	public void run() {
		for(posicion = 0; posicion <= 10; posicion++) {
			if(hayQueParar) {
				return;
			}
			
			try {
				Thread.sleep(new Random().nextInt(1, 1000));
			} catch (InterruptedException e) {
				// No hacer nada
			}
		}
	}

	public String getDorsal() {
		return dorsal;
	}

	public int getPosicion() {
		return posicion;
	}
	
	public void parar() {
		hayQueParar = true;
	}
}
