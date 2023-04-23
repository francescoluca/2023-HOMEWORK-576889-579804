package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{
	private String AttrezzoLuce;

	public StanzaBuia(String nome,String AttrezzoLuce) {
		super(nome);
		this.AttrezzoLuce = AttrezzoLuce;
	}

	@Override 
	public String getDescrizione() {
		if(!(this.hasAttrezzo(AttrezzoLuce))) {
			return "qui c'è buio pesto";
		}
		return super.toString();
	}
}
