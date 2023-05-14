package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerPeso;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private List<Attrezzo> attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.numeroAttrezzi = 0;
		this.attrezzi = new ArrayList<Attrezzo>();
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		numeroAttrezzi ++;
		return this.attrezzi.add(attrezzo);
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for(Attrezzo a:this.attrezzi) {
			if(a.getNome().equals(nomeAttrezzo))
				return a;
		}
		return null;
	}

	public int getPeso() {
		int peso = 0;
		for(Attrezzo a:this.attrezzi) {
			peso += a.getPeso();
		}
		return peso;
	}

	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}

	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		Iterator<Attrezzo> iteratore = this.attrezzi.iterator();
		while(iteratore.hasNext()) {
			a = iteratore.next();
			if(a.getNome().equals(nomeAttrezzo)) {
				iteratore.remove();
				return a;
			}
		}
		return null;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for(Attrezzo a: this.attrezzi) {
				s.append(a.toString()+" ");
			}
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> risultato = new ArrayList<Attrezzo>(this.attrezzi);
		Collections.sort(risultato,new ComparatoreAttrezziPerPeso());
		return risultato;
	}
	
//	public List<Attrezzo> getContenutoDiPeso(int peso){
//		List<Attrezzo> risultato = new ArrayList<Attrezzo>(this.attrezzi);
//		for(Attrezzo a:this.attrezzi) {
//			if(a.getPeso()!=peso)
//				risultato.remove(a);
//		}
//		return risultato;
//	}
	
	SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		SortedSet<Attrezzo> risultato = new TreeSet<Attrezzo>();
		risultato.addAll(this.attrezzi);
		return risultato;	
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer,Set<Attrezzo>> risultato = new HashMap<>();
		for(Attrezzo a:this.attrezzi) {
			if(risultato.containsKey(a.getPeso()))
				risultato.get(a.getPeso()).add(a);
			else {
				Set<Attrezzo>setAttrezzi = new HashSet<>();
				setAttrezzi.add(a);
				risultato.put(a.getPeso(), setAttrezzi);
			}
		}
		return risultato;
	}
	
	SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		SortedSet<Attrezzo> risultato = new TreeSet<>(new ComparatoreAttrezziPerPeso());
		risultato.addAll(this.attrezzi);
		return risultato;	
	}
}

