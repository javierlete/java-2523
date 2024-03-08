package uf2216.interfaces;

import java.util.ArrayList;

public class InterfacesPrueba {
	public static void main(String[] args) {
		var rodables = new ArrayList<Rodable>();
		
		rodables.add(new Naranja());
		rodables.add(new Balon());
		
		for(Rodable r: rodables) {
			if(r instanceof Comestible c) {
				c.comer();
			}
			
			r.rodar();
			
			if(r instanceof Comestible c) {
				c.comer();
			}
		}
	}
}
