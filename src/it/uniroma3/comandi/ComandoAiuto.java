package it.uniroma3.comandi;

import it.uniroma3.diadia.Comando;
import it.uniroma3.diadia.Partita;

/**
 * Stampa informazioni di aiuto.
 */

public class ComandoAiuto implements Comando {
	static final private String[] elencoComandi = {"vai","aiuto","fine","prendi","posa","guarda"};

	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++) 
			System.out.println(elencoComandi[i]+" ");		
	}
	@Override
	public void setParametro(String parametro) {
	}
	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return "aiuto";
	}
	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return null;
	}
}
