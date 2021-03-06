package model;

public class Evenement implements Comparable<Evenement>{
    private Date chDate;
    private String chNom;
    private String chImage;
    private String chTexte;
    private int chPoids;
    private static int chNbEvt = 0;

    public String toString() {
        return chNom + "@" + chDate.toStringNum() + "@" + chTexte + "@" + chImage + "@" + chPoids;
    }

    public Evenement(String parNom, Date parDate, String parTexte, String parImage, int parPoids) {
        chDate = parDate;
        chNom = parNom;
        chImage = parImage;
        chTexte = parTexte;
        chPoids = parPoids;
        chNbEvt = chNbEvt + 1;
    }

    public String getNom() {
        return chNom;
    }
    
    public String getText() {
        return chTexte;
    }
    
    public String getImg() {
        return chImage;
    }

    public void setNom(String parNom) {
        chNom = parNom;
    }

    public static int getNbEvt() {
        return chNbEvt;
    }

    /*     public static Evenement lireEvenement(){
        Date a = Date.saisirUneDate();
        System.out.println("Nom de l'evenement: ");
        String b = Clavier.lireString();
        System.out.println("Lieu de l'evenement: ");
        String c = Clavier.lireString();
        return new Evenement(a,b,c); 
    }
     */
    
    public int compareTo(Evenement parEvt) {
        if (chDate.compareAn(parEvt.chDate) != 0)
            return (chDate.compareAn(parEvt.chDate));
        else if(chPoids < parEvt.chPoids)
        	return 1;
        else if(chPoids > parEvt.chPoids) 
        	return -1;
        else
        	return (chNom.compareTo(parEvt.chNom));

    }

    public Date getDate() {
        return chDate;
    }

	public int getPoids() {
		return chPoids;
	}
}