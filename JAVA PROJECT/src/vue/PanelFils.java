package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.*;

public class PanelFils extends JPanel implements ActionListener{
	//Déclaration et instanciation des éléments graphiques
	CardLayout gestionnaire = new CardLayout();
	JButton boutonGauche = new JButton("<");
	JButton boutonDroite = new JButton(">");
	PanelTitre panelTitre = new PanelTitre("Titre");
	JPanel panelDiapo = new JPanel();
	PanelFrise panelFrise;
	
	
	Evenement evenementCourant;
	int chIndex = 0;
	public int chNombreDiapo;
	
	public PanelFils(Chronologie parChrono){
		PanelFrise panelFrise = new PanelFrise(parChrono);
		
		setLayout(new BorderLayout());
		panelDiapo.setLayout(gestionnaire);
		
		boutonGauche.addActionListener(this);
		boutonDroite.addActionListener(this);
		
		chNombreDiapo = parChrono.getEvts().size();
		PanelEvenement [] panelEvenement = new PanelEvenement[chNombreDiapo];

		for (int i=0; i<chNombreDiapo; i++){
			evenementCourant = parChrono.getEvt(i);
			panelEvenement[i] = new PanelEvenement(evenementCourant.getNom(),evenementCourant.getText(),evenementCourant.getDate().toString(),evenementCourant.getImg());
			panelDiapo.add(panelEvenement[i], i+"");
			System.out.println(i);
		}
		
		//Placement des composants dans le Panel
		add(panelTitre, BorderLayout.NORTH);
		add(panelFrise, BorderLayout.SOUTH);
		add(boutonGauche, BorderLayout.WEST);
		add(boutonDroite, BorderLayout.EAST);
		add(panelDiapo, BorderLayout.CENTER);
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
	}
}
