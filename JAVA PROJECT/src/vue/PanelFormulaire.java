package vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.Chronologie;
import model.Data;
import model.Date;
import model.Evenement;

public class PanelFormulaire extends JPanel implements ActionListener,Data{
	private Chronologie chronologie;
	private JLabel etiquette;
	private JButton ajout;
	private JLabel titre;
	private JTextField saisieTitre;
	private JLabel date;
	private String [] JOURS = new String[31];
	private JComboBox jour;
	private String [] MOIS = new String[12];
	private JComboBox mois;
	private String [] ANNEE;
	private JComboBox annee;
	private JLabel descriptif;
	private JTextArea saisieDescriptif;
	private JLabel poids;
	private String [] POIDS = new String[4];
	private JComboBox saisiePoids;
	private JButton inputImage;
	private File fileImage;
	private String image;
	
	public PanelFormulaire(Chronologie parChronologie) {
		chronologie = parChronologie;
		setLayout(new GridBagLayout());
		GridBagConstraints position = new GridBagConstraints();
		etiquette = new JLabel("Ajout d'un Evenement");
		position.insets=new Insets (10,10,10,10);
		position.gridwidth = 2;
		position.gridx = 0;
		position.gridy = 0;
		add(etiquette, position);
		ajout = new JButton("+");
		ajout.addActionListener(this);
		position.gridwidth = 2;
		position.gridx = 3;
		add(ajout, position);
		titre = new JLabel("Titre :");
		position.gridx = 0;
		position.gridy = 1;
		position.gridwidth = 1;
		add(titre, position);
		saisieTitre = new JTextField(15);
		position.gridx = 1;
		position.gridwidth = 3;
		add(saisieTitre, position);
		date = new JLabel("Date :");
		position.gridx = 0;
		position.gridy = 2;
		position.gridwidth = 1;
		add(date, position);
		for(int i=0;i<31;i++)
			JOURS[i]=""+(i+1);
		jour = new JComboBox(JOURS);
		position.gridx = 1;
		add(jour, position);
		for(int i=0;i<12;i++)
			MOIS[i]=""+(i+1);
		mois = new JComboBox(MOIS);
		position.gridx = 2;
		add(mois, position);
		ANNEE = new String[chronologie.chFin.getAnnee()-chronologie.chDebut.getAnnee()+1];
		for(int i=0;i<ANNEE.length;i++){
			int an=i+chronologie.chDebut.getAnnee();
			ANNEE[i]=""+an;
		}
		annee = new JComboBox(ANNEE);
		position.gridx = 3;
		add(annee, position);
		descriptif = new JLabel("Descriptif :");
		position.gridx = 0;
		position.gridy = 3;
		add(descriptif, position);
		saisieDescriptif = new JTextArea(3,15);
		position.gridx = 1;
		position.gridwidth = 3;
		add(saisieDescriptif, position);
		poids = new JLabel("Poids :");
		position.gridx = 0;
		position.gridy = 4;
		position.gridwidth = 1;
		add(poids, position);
		for(int i=0;i<=3;i++)
			POIDS[i]=""+i;
		saisiePoids = new JComboBox(POIDS);
		position.gridx = 1;
		add(saisiePoids, position);
		position.gridx = 2;
		position.gridwidth = 2;
		inputImage = new JButton("Ajouter une image");
		inputImage.addActionListener(this);
		add(inputImage, position);
	}
	
	public static void writeFile(String parFile, String parTexte){
    	try {
    	File file = new File ("save/"+parFile+".txt");
    	BufferedWriter out = new BufferedWriter(new FileWriter(file)); 
    	out.write(parTexte);
    	out.close();	
    	}
    	catch(IOException parExc) {
    		System.exit(0);
    	}
	}

	public void actionPerformed(ActionEvent parEvt) {
		if(parEvt.getSource()==ajout){
			if(saisieTitre.getText()=="" || saisieTitre.getText()==null) {
				System.out.println("Veuillez donner un titre à l'événement");
				return;
			}
			if(saisieDescriptif.getText()=="" || saisieDescriptif.getText()==null) {
				System.out.println("Veuillez ajouter une description à l'événement");
				return;
			}
			if(image=="" || image==null) {
				System.out.println("Veuillez ajouter une image à l'événement");
				return;
			}
			Date dateEvt = new Date(jour.getSelectedIndex()+1, mois.getSelectedIndex()+1, annee.getSelectedIndex()+chronologie.chDebut.getAnnee());
			if(dateEvt.compareTo(chronologie.chDebut)==-1 || dateEvt.compareTo(chronologie.chFin)==1) {
				System.out.println("Veuillez entrer un date comprise entre le "+chronologie.chDebut.toStringNum()+" et le " + chronologie.chFin.toStringNum());
				return;
			}
			Evenement newEvenement = new Evenement(saisieTitre.getText(), dateEvt , saisieDescriptif.getText(), image, saisiePoids.getSelectedIndex());
			chronologie.ajout(newEvenement);
			//Sauvegarde de la nouvelle chronologie
			String savePath = new String(chronologie.getSave());
			Object[] options = {"Oui","Non"};
			int n = JOptionPane.showOptionDialog(
								this,"Voulez vous sauvegarder cette chronologie?",
								"Sauvegarder", 
								JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								null, 
								options,options[1]);
			if (n==0){
				writeFile(savePath,chronologie.toString());
			}
			saisieTitre.setText("");
			jour.setSelectedIndex(0);
			mois.setSelectedIndex(0);
			annee.setSelectedIndex(0);
			saisieDescriptif.setText("");
			saisiePoids.setSelectedIndex(0);
			image = null;
		}
		if(parEvt.getSource()==inputImage) {
			final JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
	            fileImage = fc.getSelectedFile();
	            String image = fileImage.getName();
	            int pos = image.lastIndexOf(".");
	            if (pos > 0) {
	            	image = image.substring(0, pos);
	            }
	            System.out.println("Fichier selectionné: " + fileImage.getName());
	        } else {
	        	System.out.println("Ouverture de fichier annulée par l'utilisateur");
	        }
		}
	}
}