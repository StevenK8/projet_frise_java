package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Evenement;
import vue.PanelFils;
import vue.PanelFrise;

public class Controleur implements ActionListener{
	PanelFils panelFils;
	PanelFrise panelFrise;
	
	public Controleur(PanelFils parPanelFils, PanelFrise parPanelFrise) {
		panelFils = parPanelFils;
		panelFrise = parPanelFrise;
		
		
		//panelFils.enregistreEcouteur(this);
	}
	
	public void actionPerformed(ActionEvent parEvt) {
		// Pour d�placer le scrollpane jusqu'� l'�v�nement affich� dans le conteneur
		//tableFrise.scrollRectToVisible(tableFrise.getCellRect(row, column, true));
	}
}
