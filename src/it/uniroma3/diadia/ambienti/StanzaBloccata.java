package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{
	private String direzioneBloccata;
	private String AttrezzoChiave;

	public StanzaBloccata(String nome,String direzioneBloccata,String AttrezzoChiave) {		
		super(nome);
		this.AttrezzoChiave = AttrezzoChiave;
		this.direzioneBloccata = direzioneBloccata;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(direzione == direzioneBloccata) {
			if(!this.hasAttrezzo(AttrezzoChiave))
				return this;
		}
		return super.getStanzaAdiacente(direzione);
	}
}
