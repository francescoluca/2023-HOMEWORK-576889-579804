package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder{
	private Labirinto labirinto;
	private Map<String,Stanza> stanzeLabirinto;
	private Stanza ultimaStanzaAggiunta;

	public LabirintoBuilder() {
		this.labirinto = new Labirinto();
		this.stanzeLabirinto = new HashMap<>();
	}

	public LabirintoBuilder addStanzaIniziale(String nomeStanzaIniziale) {
		Stanza s = new Stanza(nomeStanzaIniziale);
		stanzeLabirinto.put(nomeStanzaIniziale, s);
		labirinto.stanzaIniziale = s;
		ultimaStanzaAggiunta = s;
		return this;
	}

	public LabirintoBuilder addStanzaVincente(String nomeStanzaVincente) {
		Stanza s = new Stanza(nomeStanzaVincente);
		stanzeLabirinto.put(nomeStanzaVincente, s);
		labirinto.stanzaVincente = s;
		ultimaStanzaAggiunta = s;
		return this;
	}

	public LabirintoBuilder addAttrezzo(String nomeAttrezzo,int peso) {
		Attrezzo nuovoAttrezzo = new Attrezzo(nomeAttrezzo,peso);
		ultimaStanzaAggiunta.addAttrezzo(nuovoAttrezzo);
		return this;
	}

	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	
	public LabirintoBuilder addAdiacenza(String nomeStanza1, String nomeStanza2, String direzione) {
		if(this.stanzeLabirinto.containsKey(nomeStanza1) && this.stanzeLabirinto.containsKey(nomeStanza2))
			if(this.stanzeLabirinto.get(nomeStanza1).getDirezioni().size()<=3)
				this.stanzeLabirinto.get(nomeStanza1).impostaStanzaAdiacente(direzione,this.stanzeLabirinto.get(nomeStanza2));
		return this;
	}

	public LabirintoBuilder addStanza(String nomeStanza) {
		Stanza s = new Stanza(nomeStanza);
		this.stanzeLabirinto.put(nomeStanza,s );
		ultimaStanzaAggiunta=s;
		return this;
	}

	public Map<String, Stanza> getListaStanze() {
		return this.stanzeLabirinto;
	}

	public LabirintoBuilder addStanzaMagica(String nomeStanzaMagica, int sogliaMagica) {
		StanzaMagica s = new StanzaMagica(nomeStanzaMagica,sogliaMagica);
		this.stanzeLabirinto.put(nomeStanzaMagica,s);
		ultimaStanzaAggiunta=s;
		return this;
	}

	public LabirintoBuilder addStanzaBloccata(String nomeStanzaBloccata, String direzione, String chiave) {
		StanzaBloccata s = new StanzaBloccata(nomeStanzaBloccata, direzione, chiave);
		this.stanzeLabirinto.put(nomeStanzaBloccata,s);
		ultimaStanzaAggiunta = s;
		return this;
	}

	public LabirintoBuilder addStanzaBuia(String nomeStanzaBuia, String attrezzoLuce) {
		StanzaBuia s = new StanzaBuia(nomeStanzaBuia,attrezzoLuce);
		this.stanzeLabirinto.put(nomeStanzaBuia,s );
		ultimaStanzaAggiunta=s;
		return this;
	}
}


