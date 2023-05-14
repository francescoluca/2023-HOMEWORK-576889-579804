package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder{
	private Labirinto labirinto;
	private Map<String,Stanza> stanzeLabirinto;
	
	public LabirintoBuilder() {
		this.labirinto = new Labirinto();
		this.stanzeLabirinto = new HashMap<>();
	}

	public LabirintoBuilder addStanzaIniziale(String nomeStanzaIniziale) {
		Stanza s = new Stanza(nomeStanzaIniziale);
		stanzeLabirinto.put(nomeStanzaIniziale, s);
		labirinto.stanzaIniziale = s;
		return this;
	}

	public LabirintoBuilder addStanzaVincente(String nomeStanzaVincente) {
		Stanza s = new Stanza(nomeStanzaVincente);
		stanzeLabirinto.put(nomeStanzaVincente, s);
		labirinto.stanzaVincente = s;
		return this;
	}
	
	public LabirintoBuilder addAttrezzo(String nomeAttrezzo,int peso) {
		Attrezzo nuovoAttrezzo = new Attrezzo(nomeAttrezzo,peso);
		if(labirinto.stanzaIniziale!=null)
			labirinto.stanzaIniziale.addAttrezzo(nuovoAttrezzo);
		if(labirinto.stanzaVincente!=null)
			labirinto.stanzaVincente.addAttrezzo(nuovoAttrezzo);
		return this;
	}
	
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	public LabirintoBuilder addAdiacenza(String nomeStanza1, String nomeStanza2, String direzione) {
		if(this.stanzeLabirinto.containsKey(nomeStanza1) && this.stanzeLabirinto.containsKey(nomeStanza2))
			this.stanzeLabirinto.get(nomeStanza1).impostaStanzaAdiacente(direzione,this.stanzeLabirinto.get(nomeStanza2));
		return this;
	}

	public LabirintoBuilder addStanza(String stanza) {
		this.stanzeLabirinto.put(stanza, new Stanza("stanza"));
		return this;
	}

	public List<Stanza> getListaStanze() {
		List<Stanza> listaStanze = new ArrayList<>(this.stanzeLabirinto.values());
		return listaStanze;
	}

//	public Stanza addStanzaMagica(String nomeStanzaMagica, int sogliaMagica) {
//		return stanzaIniziale;
//		// TODO Auto-generated method stub
//		
	}


