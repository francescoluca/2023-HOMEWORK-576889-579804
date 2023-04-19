package it.uniroma3.comandi;

import it.uniroma3.diadia.Comando;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {

	@Override
	public void esegui(Partita partita) {
		System.out.println(partita.getStanzaCorrente());
		System.out.println(partita.getGiocatore().getBorsa());

	}

	@Override
	public void setParametro(String parametro) {
	}

	@Override
	public String getNome() {
		return "Guarda";
	}

	@Override
	public String getParametro() {
		return null;
	}

}
