package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Evenement;
import vue.PanelFils;

public class Controleur implements ActionListener{
	PanelFils panelFils;
	Evenement chEvenement;
	
	public Controleur(PanelFils parPanelFils, Evenement parEvenement) {
		panelFils = parPanelFils;
		chEvenement = parEvenement;
		
		
		//panelFils.enregistreEcouteur(this);
	}
	
	public void actionPerformed(ActionEvent parEvt) {
		// Pour déplacer le scrollpane jusqu'à l'événement affiché dans le conteneur
		//tableFrise.scrollRectToVisible(tableFrise.getCellRect(row, column, true));
	}
}
