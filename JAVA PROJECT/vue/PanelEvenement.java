package vue;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelEvenement extends JPanel{
	
	public JLabel chImage = new JLabel("Image");
	public JLabel chTitre = new JLabel("Titre");
	public JLabel chDate = new JLabel("Date");
	public JLabel chTexte = new JLabel("Texte");
	
	public PanelEvenement(String parTitre, String parTexte, String parDate, String parImage){
		if(parTexte.length()>25) { // Retour ? la ligne
			String texteLigne = new String("<html><div style='text-align: center;'>");
			char[] sAr = parTexte.toCharArray();
			int start = 0;
			for (int i = 25; i < sAr.length; i++) {
			    if (sAr[i] == ' ') {
			    	texteLigne+=parTexte.substring(start, i)+"<br>";
			        start = i+1;
			        i += 25;
			    }
			}
			texteLigne+="</div></html>";
			chTexte.setText(texteLigne);
		}else {
			chTexte.setText(parTexte);
		}
		chTitre.setText(parTitre);
		chDate.setText(parDate);
		chImage.setText(parImage);
		chTitre.setFont(new Font("Century Gothic",Font.BOLD,14));
		chTexte.setFont(new Font("Century Gothic",Font.ITALIC,12));
		chDate.setFont(new Font("Century Gothic",Font.BOLD+Font.ITALIC,14));
		
		String imageLink = new String();
		File fichierImage = new File("images/"+parImage+".jpg");
		if (fichierImage.isFile()) {
			imageLink = new String("images/"+parImage+".jpg");
		}else if ((fichierImage = new File("images/"+parImage+".png")).isFile()){
			imageLink = new String("images/"+parImage+".png");
		}
		ImageIcon imageIcon = new ImageIcon(imageLink);
		File f = new File(imageLink);
		if(f.isFile() && !f.isDirectory()) { 
			chImage.setText(""); // Si l'image existe, on retire "image"
		}
		
		
		Image image = imageIcon.getImage();
		Image scaledImage = image.getScaledInstance(300, 180,  java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(scaledImage);
		
		chImage.setIcon(imageIcon);
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints contrainte = new GridBagConstraints();
        contrainte.gridheight = 3;
        contrainte.gridwidth = 5;
        contrainte.insets = new Insets(10, 0, 0, 0);
        contrainte.anchor = GridBagConstraints.CENTER;
        this.add(chImage, contrainte);
        
        contrainte.gridheight = 1;
        contrainte.gridwidth = 5;
        contrainte.gridx = 5;
        this.add(chDate, contrainte);

        contrainte.gridy += 2;
        this.add(chTitre, contrainte);

        contrainte.gridheight = 1;
        contrainte.gridy += 1;
        this.add(chTexte, contrainte);
		
	}
}
