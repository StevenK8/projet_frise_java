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

public class PanelFils extends JPanel implements ActionListener,Data{
	//Déclaration et instanciation des éléments graphiques
	CardLayout gestionEvts = new CardLayout();
	CardLayout gestionPanels = new CardLayout();
	JPanel panelAffichage = new JPanel();
	JButton boutonGauche = new JButton("<");
	JButton boutonDroite = new JButton(">");
	JLabel titre;
	JPanel panelDiapo = new JPanel();
	JPanel panelFormulaire;
	PanelFrise panelFrise;
	Chronologie chChronologie;
	Evenement evenementCourant;
	int chIndex = 0;
	int chNombreDiapo;
	
	public PanelFils(Chronologie parChrono){
		setLayout(gestionPanels);
		chChronologie = parChrono;
		PanelFrise panelFrise = new PanelFrise(chChronologie,gestionEvts,panelDiapo);
		panelAffichage.setLayout(new BorderLayout());
		panelDiapo.setLayout(gestionEvts);
		titre = new JLabel(chChronologie.chNom,SwingConstants.CENTER);
		titre.setFont(new Font("Century Gothic",Font.BOLD,20));
		panelFormulaire = new PanelFormulaire(chChronologie);
		
		boutonGauche.addActionListener(this);
		boutonDroite.addActionListener(this);
		
		chNombreDiapo = chChronologie.getEvts().size();
		PanelEvenement [] panelEvenement = new PanelEvenement[chNombreDiapo];

		for (int i=0; i<chNombreDiapo; i++){
			evenementCourant = chChronologie.getEvt(i);
			panelEvenement[i] = new PanelEvenement(evenementCourant.getNom(),evenementCourant.getText(),evenementCourant.getDate().toString(),evenementCourant.getImg());
			panelDiapo.add(panelEvenement[i], evenementCourant.toString());
		}
		
		//Placement des composants dans le Panel
		panelAffichage.add(titre, BorderLayout.NORTH);
		panelAffichage.add(panelFrise, BorderLayout.SOUTH);
		panelAffichage.add(boutonGauche, BorderLayout.WEST);
		panelAffichage.add(boutonDroite, BorderLayout.EAST);
		panelAffichage.add(panelDiapo, BorderLayout.CENTER);
		add(panelAffichage,ITEMS[0]);
		add(panelFormulaire,ITEMS[1]);
	}
	
	public Chronologie getChrono() {
		return chChronologie;
	}

	public void actionPerformed (ActionEvent parEvt) {
		if(parEvt.getSource()==boutonGauche && chIndex !=0) {
			gestionEvts.previous(panelDiapo);
			chIndex--;
		}
		else if(parEvt.getSource()==boutonDroite && chIndex !=chNombreDiapo-1) {
			gestionEvts.next(panelDiapo);
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
