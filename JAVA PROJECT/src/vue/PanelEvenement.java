package vue;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelEvenement extends JPanel{
	
	public JLabel chImage = new JLabel("Image");
	public JLabel chTitre = new JLabel("Titre");
	public JLabel chDate = new JLabel("Date");
	public JLabel chTexte = new JLabel("Texte");
	
	public PanelEvenement(String parTitre, String parTexte, String parDate, String parImage){
		if(parTexte.length()>25) { // Retour � la ligne
			String texteLigne = new String("<html><div style='text-align: justify;'>");
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
		
		File fichierImage = new File("images/"+parImage+".jpg");
		if (fichierImage.isFile()||(fichierImage = new File("images/"+parImage+".png")).isFile()) {
			chImage.setText(""); // Si l'image existe, on retire "image"
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(fichierImage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedImage resized = resize(image, 200, 300);
		ImageIcon imageIcon = new ImageIcon(resized);
		chImage.setIcon((Icon) imageIcon);
		}
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints contrainte = new GridBagConstraints();
		contrainte.gridx = 0;
		contrainte.gridy = 0;
        contrainte.gridheight = 5;
        contrainte.gridwidth = 5;
        contrainte.insets = new Insets(10, 10, 10, 10);
        contrainte.anchor = GridBagConstraints.NORTH;
        this.add(chImage, contrainte);
        
        contrainte.gridheight = 1;
        contrainte.gridwidth = 4;
        contrainte.gridx = 6;
        this.add(chDate, contrainte);

        contrainte.gridy += 2;
        this.add(chTitre, contrainte);

        contrainte.gridheight = 2;
        contrainte.gridy += 1;
        this.add(chTexte, contrainte);
		
	}
	
	private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
}
