package vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
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

	public void actionPerformed(ActionEvent parEvt) {
		if(parEvt.getSource()==ajout){
			Evenement newEvenement = new Evenement(saisieTitre.getText(), new Date(jour.getSelectedIndex()+1, 
					mois.getSelectedIndex()+1, annee.getSelectedIndex()+chronologie.chDebut.getAnnee()+1), saisieDescriptif.getText(), fileImage.getName(), saisiePoids.getSelectedIndex());
			chronologie.ajout(newEvenement);
			saisieTitre.setText("");
			jour.setSelectedIndex(0);
			mois.setSelectedIndex(0);
			annee.setSelectedIndex(0);
			saisieDescriptif.setText("");
			saisiePoids.setSelectedIndex(0);
		}
		if(parEvt.getSource()==inputImage) {
			final JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
	            fileImage = fc.getSelectedFile();
	            System.out.println("Fichier selectionné: " + fileImage.getName());
	        } else {
	        	System.out.println("Ouverture de fichier annulée par l'utilisateur");
	        }
		}
	}
}