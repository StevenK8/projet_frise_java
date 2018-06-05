package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.*;

public class PanelFils extends JPanel implements ActionListener{
	//Déclaration et instanciation des éléments graphiques
	CardLayout gestionnaire = new CardLayout();
	CardLayout gestionPanels = new CardLayout();
	JPanel panelAffichage = new JPanel();
	JButton boutonGauche = new JButton("<");
	JButton boutonDroite = new JButton(">");
	JLabel panelTitre = new JLabel("Titre",SwingConstants.CENTER);
	JPanel panelDiapo = new JPanel();
	JPanel panelFormulaire;
	PanelFrise panelFrise;
	Chronologie chCronologie;
	Evenement evenementCourant;
	int chIndex = 0;
	int chNombreDiapo;
	
	public PanelFils(Chronologie parChrono){
		setLayout(gestionPanels);
		chCronologie = parChrono;
		PanelFrise panelFrise = new PanelFrise(chCronologie,gestionnaire,panelDiapo);
		panelAffichage.setLayout(new BorderLayout());
		panelDiapo.setLayout(gestionnaire);
		panelTitre.setFont(new Font("Century Gothic",Font.BOLD,20));
		panelFormulaire = new PanelFormulaire(chCronologie);
		
		boutonGauche.addActionListener(this);
		boutonDroite.addActionListener(this);
		
		chNombreDiapo = chCronologie.getEvts().size();
		PanelEvenement [] panelEvenement = new PanelEvenement[chNombreDiapo];

		for (int i=0; i<chNombreDiapo; i++){
			evenementCourant = chCronologie.getEvt(i);
			panelEvenement[i] = new PanelEvenement(evenementCourant.getNom(),evenementCourant.getText(),evenementCourant.getDate().toString(),evenementCourant.getImg());
			panelDiapo.add(panelEvenement[i], evenementCourant.toString());
		}
		
		//Placement des composants dans le Panel
		panelAffichage.add(panelTitre, BorderLayout.NORTH);
		panelAffichage.add(panelFrise, BorderLayout.SOUTH);
		panelAffichage.add(boutonGauche, BorderLayout.WEST);
		panelAffichage.add(boutonDroite, BorderLayout.EAST);
		panelAffichage.add(panelDiapo, BorderLayout.CENTER);
		add(panelAffichage,ITEMS[0]);
		add(panelFormulaire,ITEMS[1]);
	}
	
	public Chronologie getChrono() {
		return chCronologie;
	}

	public void actionPerformed (ActionEvent parEvt) {
		if(parEvt.getSource()==boutonGauche && chIndex !=0) {
			gestionnaire.previous(panelDiapo);
			chIndex--;
		}
		else if(parEvt.getSource()==boutonDroite && chIndex !=chNombreDiapo-1) {
			gestionnaire.next(panelDiapo);
			chIndex++;
		}
		else if (parEvt.getActionCommand() == ITEMS[0]) {
			gestionPanels.show(this, ITEMS[0]);
		}
		else if (parEvt.getActionCommand() == ITEMS[1]) {
			gestionPanels.show(this, ITEMS[1]);
		}
	}
}
