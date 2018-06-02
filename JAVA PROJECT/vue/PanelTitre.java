package vue;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelTitre extends JPanel{
	public PanelTitre(String parTitre){
		JLabel labelTitre = new JLabel(parTitre);
		this.add(labelTitre);
	}
}
