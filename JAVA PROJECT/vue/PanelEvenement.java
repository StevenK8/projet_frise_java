package vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelEvenement extends JPanel{
	
	public JLabel chImage = new JLabel("Image");
	public JLabel chTitre = new JLabel("Titre");
	public JLabel chDate = new JLabel("Date");
	public JLabel chTexte = new JLabel("Texte");
	
	public PanelEvenement(String parTitre, String parTexte, String parDate, String parImage){
		
		chTitre.setText(parTitre);
		chTexte.setText(parTexte);
		chDate.setText(parDate);
		chImage.setText(parImage);

		String imageLink = new String("images/"+parImage+".jpg");
		ImageIcon imageIcon = new ImageIcon(imageLink);
		File f = new File(imageLink);
		if(f.isFile() && !f.isDirectory()) { 
			chImage.setText(""); // Si l'image existe, on retire "image"
		}
		
		
		Image image = imageIcon.getImage();
		Image scaledImage = image.getScaledInstance(200, 120,  java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(scaledImage);
		
		chImage.setIcon(imageIcon);
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints contrainte = new GridBagConstraints();
        contrainte.gridheight = 1;
        contrainte.gridwidth = 1;
        contrainte.insets = new Insets(10, 0, 0, 0);
        contrainte.anchor = GridBagConstraints.CENTER;
        this.add(chImage, contrainte);
        
        contrainte.gridwidth = 4;
        contrainte.gridx = 5;
        this.add(chDate, contrainte);

        contrainte.gridy += 2;
        this.add(chTitre, contrainte);

        contrainte.gridy += 2;
        this.add(chTexte, contrainte);
		
	}
}
