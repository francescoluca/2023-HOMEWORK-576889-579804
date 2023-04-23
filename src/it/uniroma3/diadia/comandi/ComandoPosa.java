package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Comando;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 *	Cerca di posare un oggetto. Se c'e' un oggetto nella borsa 
 *  lo aggiunge alla stanza corrente e lo rimuove dalla borsa, 
 *  altrimenti stampa un messaggio di errore
 */	

public class ComandoPosa implements Comando {
	private String nomeAttrezzo;
	@Override
	public void esegui(Partita partita) {
		if(nomeAttrezzo==null) {
			System.out.println("Inserire attrezzo che si vuole posare. Esempio:posa lanterna");
		}
		else {
			if(!(partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo))) {
				System.out.println("Attrezzo inesistente");
			}
			else {
				Attrezzo attrezzoBorsa = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
				partita.getStanzaCorrente().addAttrezzo(attrezzoBorsa);
				partita.getGiocatore().getBorsa().removeAttrezzo(attrezzoBorsa.getNome());
				System.out.println("Oggetto "+ nomeAttrezzo + " posato");
			}
		}
	}


	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}


	@Override
	public String getNome() {
		return "Posa";
	}


	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

}
