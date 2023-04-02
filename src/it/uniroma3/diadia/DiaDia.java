package it.uniroma3.diadia;


import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = {"vai","aiuto","fine","prendi","posa","borsa"};

	private Partita partita;
	private IOConsole io;

	public DiaDia(IOConsole io) {
		this.partita = new Partita();
		this.io = io;
	}

	public void gioca() {
		String istruzione; 

		io.mostraMessaggio(MESSAGGIO_BENVENUTO);		
		do	{
			istruzione = io.leggiRiga();		
		}
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		String nome = comandoDaEseguire.getNome();
		if(nome==null) 
			io.mostraMessaggio("Comando sconosciuto");
		else if (nome.equals("fine")) {
			this.fine(); 
			return true;
		} else if (nome.equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (nome.equals("aiuto"))
			this.aiuto();
		else if (nome.equals("borsa"))
			this.vediBorsa();
		else if (nome.equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if (nome.equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else
			io.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			io.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]+" ");
	}

	private void vediBorsa() {
		io.mostraMessaggio(this.partita.getGiocatore().getBorsa().toString());;
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			io.mostraMessaggio("Dove vuoi andare ?");
		else {
			Stanza prossimaStanza = null;
			prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
			if (prossimaStanza == null)
				io.mostraMessaggio("Direzione inesistente");
			else {
				this.partita.setStanzaCorrente(prossimaStanza);
				int cfu = this.partita.getGiocatore().getCfu();
				this.partita.getGiocatore().setCfu(cfu--);
			}
			io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		}
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		io.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}
	/**
	 *	Cerca di prendere un oggetto. Se c'e' un oggetto nella stanza 
	 *  corrente lo aggiunge alla borsa del giocatore e lo rimuove 
	 *  dalla stanza corrente, altrimenti stampa un messaggio di errore
	 */	
	public void prendi(String nomeAttrezzo) {
		if(nomeAttrezzo==null) {
			io.mostraMessaggio("Inserire attrezzo che si vuole prendere. Esempio:prendi lanterna");
		}
		else {
			if(!(this.partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo))) {
				io.mostraMessaggio("Attrezzo inesistente");
			}
			else {
				Attrezzo attrezzoStanza = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
				this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzoStanza);
				this.partita.getStanzaCorrente().removeAttrezzo(attrezzoStanza);
				io.mostraMessaggio("Oggetto "+ nomeAttrezzo + " ottenuto");
			}
		}
	}
	/**
	 *	Cerca di posare un oggetto. Se c'e' un oggetto nella borsa 
	 *  lo aggiunge alla stanza corrente e lo rimuove dalla borsa, 
	 *  altrimenti stampa un messaggio di errore
	 */	

	public void posa(String nomeAttrezzo) {
		if(nomeAttrezzo==null) {
			io.mostraMessaggio("Inserire attrezzo che si vuole posare. Esempio:posa lanterna");
		}
		else {
			if(!(this.partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo))) {
				io.mostraMessaggio("Attrezzo inesistente");
			}
			else {
				Attrezzo attrezzoBorsa = this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
				this.partita.getStanzaCorrente().addAttrezzo(attrezzoBorsa);
				this.partita.getGiocatore().getBorsa().removeAttrezzo(attrezzoBorsa.getNome());
				io.mostraMessaggio("Oggetto "+ nomeAttrezzo + " posato");
			}
		}
	}

	public static void main(String[] argc) {
		IOConsole io = new IOConsole();
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
	}
}