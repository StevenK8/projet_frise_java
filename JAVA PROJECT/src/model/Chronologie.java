package model;

import java.util.Iterator;
import java.util.TreeSet;

public class Chronologie {
	public String chNom;
	public Date chDebut;
	public Date chFin;
	private int chPas;
	private String chSave;
	private TreeSet<Evenement> chEvts;
	
	public Chronologie (String parNom, TreeSet<Evenement> parEvts, Date parDebut, Date parFin, int parPas, String parSave){
		chNom = parNom;
		chDebut = parDebut;
		chFin = parFin;
		chPas = parPas;
		chSave = parSave;
		chEvts = parEvts;
	}
	public TreeSet<Evenement> getEvts(){
		return chEvts;
	}
	
	public String getSave() {
		return chSave;
	}
	
	public String toString() {
		String evts = new String();
		Iterator<Evenement> it = chEvts.iterator();
		while (it.hasNext()) {
			Evenement e = it.next();
			evts += e.toString()+"Â§";
		}
		return chNom+"Â§"+chDebut.toStringNum()+"Â§"+chFin.toStringNum()+"Â§"+chPas+"Â§"+chSave+"Â§"+evts;
	}
	
	public void ajout(Evenement parEvenement){
		chEvts.add(parEvenement);
	}
	
	public Evenement getEvt(int parIndice){
		int i=0;
		Iterator<Evenement> it = chEvts.iterator();
		while (it.hasNext()) {
			Evenement e = it.next();
			if (i==parIndice)
				return e;
			i++;
		}
		return null;
	}
	public int getDuree() {
		if (chFin.getAnnee()>chDebut.getAnnee())
			return chFin.getAnnee()-chDebut.getAnnee()+1;
		System.out.println("Erreur : Année de fin antérieure à l'année de début");
		return 1;
	}
	public int getPas() {
		return chPas;
	}
	public int getDebut() {
		return chDebut.getAnnee();
	}
	
}
