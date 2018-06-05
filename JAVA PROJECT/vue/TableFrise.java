package vue;

import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import model.Chronologie;
import model.Evenement;

public class TableFrise extends DefaultTableModel{
	Evenement evenementCourant;
	public TableFrise(Chronologie parChrono) {
		this.setColumnCount(parChrono.getDuree()); // Période en années
		this.setRowCount(4); // Poids allant de 1 à 4
		String[] colIntitules = new String[parChrono.getDuree()];
		for (int i=0; i<parChrono.getDuree();i++) { // Pour chaque pas de colonnes
			if(i%parChrono.getPas()==0)// À chaque pas
				colIntitules[i] = Integer.toString(parChrono.getDebut()+i); // On ajoute la date correspondante a�l'intitule
			else
				colIntitules[i] = ""; // Sinon on la laisse vide
		}
		this.setColumnIdentifiers(colIntitules);
		
		for(int j=0; j<parChrono.getEvts().size();j++) { //Ajout des événements à la JTable
			ImageIcon imageIcon = new ImageIcon();
			evenementCourant = parChrono.getEvt(j);
			File fichierImage = new File("images/"+evenementCourant.getImg()+".png");
			if (fichierImage.isFile()) {
				imageIcon = new ImageIcon("images/"+evenementCourant.getImg()+".png");
			}else if ((fichierImage = new File("images/"+evenementCourant.getImg()+".jpg")).isFile()){
				imageIcon = new ImageIcon("images/"+evenementCourant.getImg()+".jpg");
			}
			Image image = imageIcon.getImage();
			Image scaledImage = image.getScaledInstance(150, 110,  java.awt.Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(scaledImage,evenementCourant.toString());
			setValueAt(imageIcon,evenementCourant.getPoids(),evenementCourant.getDate().getAnnee()-parChrono.getDebut());
		}
	}
	
	public boolean isCellEditable(int indiceLigne, int indiceColonne) {
		return false;
	}
	public Class<?> getColumnClass(int parNum) {
		return ImageIcon.class;
	}
}
