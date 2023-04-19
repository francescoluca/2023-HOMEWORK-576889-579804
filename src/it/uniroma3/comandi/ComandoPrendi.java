package it.uniroma3.comandi;

import it.uniroma3.diadia.Comando;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 *	Cerca di prendere un oggetto. Se c'e' un oggetto nella stanza 
 *  corrente lo aggiunge alla borsa del giocatore e lo rimuove 
 *  dalla stanza corrente, altrimenti stampa un messaggio di errore
 */	

public class ComandoPrendi implements Comando {
	private String nomeAttrezzo;
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		if(this.nomeAttrezzo==null) {
			System.out.println("Inserire attrezzo che si vuole prendere. Esempio:prendi lanterna");
		}
		else {
			if(!(stanzaCorrente.hasAttrezzo(nomeAttrezzo))) {
				System.out.println("Attrezzo inesistente");
			}
			else {
				Attrezzo attrezzoStanza = stanzaCorrente.getAttrezzo(nomeAttrezzo);
				partita.getGiocatore().getBorsa().addAttrezzo(attrezzoStanza);
				partita.getStanzaCorrente().removeAttrezzo(attrezzoStanza);
				System.out.println("Oggetto "+ nomeAttrezzo + " ottenuto");
			}
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}

	@Override
	public String getNome() {
		return "Prendi";
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

}
