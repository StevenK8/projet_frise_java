package vue;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
			if (fichierImage.isFile()||(fichierImage = new File("images/"+evenementCourant.getImg()+".jpg")).isFile()) {
			BufferedImage image = null;
			try {
				image = ImageIO.read(fichierImage);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BufferedImage resized = resize(image, 110, 150);
			//Image image = imageIcon.getImage();
			//Image scaledImage = image.getScaledInstance(150, 110,  java.awt.Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(resized,evenementCourant.toString());
			setValueAt(imageIcon,evenementCourant.getPoids(),evenementCourant.getDate().getAnnee()-parChrono.getDebut());
			}
		}
	}
	
	private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

	
	
	public boolean isCellEditable(int indiceLigne, int indiceColonne) {
		return false;
	}
	public Class<?> getColumnClass(int parNum) {
		return ImageIcon.class;
	}
}
