package vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PanelFormulaire extends JPanel{
	private JLabel etiquette;
	private JButton ajout;
	private JLabel titre;
	private JTextField saisieTitre;
	private JLabel date;
	private JComboBox jour;
	private JComboBox mois;
	private JComboBox annee;
	private JLabel descriptif;
	private JTextArea saisieDescriptif;
	private JLabel poids;
	private JComboBox saisiePoids;
	
	public PanelFormulaire() {
		setLayout(new GridBagLayout());
		GridBagConstraints position = new GridBagConstraints();
		etiquette = new JLabel("Ajout d'un Evenement");
		position.gridwidth = 2;
		position.gridx = 0;
		position.gridy = 0;
		add(etiquette, position);
		ajout = new JButton("+");
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
		jour = new JComboBox();
		position.gridx = 1;
		add(jour, position);
		mois = new JComboBox();
		position.gridx = 2;
		add(mois, position);
		annee = new JComboBox();
		position.gridx = 3;
		add(annee, position);
		descriptif = new JLabel("Descriptif :");
		position.gridx = 0;
		position.gridy = 3;
		add(descriptif, position);
		saisieDescriptif = new JTextArea(1, 15);
		position.gridx = 1;
		position.gridwidth = 3;
		add(saisieDescriptif, position);
		poids = new JLabel("Poids :");
		position.gridx = 0;
		position.gridy = 4;
		position.gridwidth = 1;
		add(poids, position);
		saisiePoids = new JComboBox();
		position.gridx = 1;
		add(saisiePoids, position);
	}
}
