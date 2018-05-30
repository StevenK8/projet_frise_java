package model;

import java.util.Iterator;
import java.util.TreeSet;

public class Chronologie {
	private String chNom;
	private Date chDebut;
	private Date chFin;
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
	
}
