package uf2216.interfaces;

public class Naranja extends Fruto implements Rodable {

	private boolean porElSuelo = false;
	
	@Override
	public void comer() {
		if(porElSuelo) {
			System.out.println("Que la coma tu padre");
		} else {
			System.out.println("Ñam que rica");
		}
	}

	@Override
	public void rodar() {
		System.out.println("Naranja rodando");
		porElSuelo = true;
	}

}
