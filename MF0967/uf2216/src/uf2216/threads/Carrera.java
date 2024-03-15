package uf2216.threads;

public class Carrera {
	public static void main(String[] args) {
		Corredor c1 = new Corredor("c1");
		Corredor c2 = new Corredor("c2");

		Thread t1 = new Thread(c1);
		Thread t2 = new Thread(c2);

		t1.start();
		t2.start();

		do {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// No hacer nada
			}
			
			System.out.printf("| %s | %s |\n", c1.getPosicion(), c2.getPosicion());
		} while (c1.getPosicion() < 10 && c2.getPosicion() < 10);
		
		c1.parar();
		c2.parar();
	}
}
