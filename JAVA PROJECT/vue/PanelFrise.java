package vue;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import model.Chronologie;
import model.Evenement;

public class PanelFrise extends JPanel {
	TableFrise modele;
	final JTable tableFrise;
	CardLayout gestionnaire;
	JPanel panelDiapo;
	
	public PanelFrise(Chronologie parChrono,CardLayout parGestionnaire,JPanel parPanelDiapo){
		modele = new TableFrise(parChrono);
		tableFrise = new JTable(modele);
		gestionnaire = parGestionnaire;
		panelDiapo = parPanelDiapo;
		
		// Format des Intitulés
		tableFrise.getTableHeader().setBackground(new java.awt.Color(45, 35, 66));
		tableFrise.getTableHeader().setFont(new Font("Century Gothic",Font.BOLD,20));
		tableFrise.getTableHeader().setForeground(new java.awt.Color(229, 236, 239));
		tableFrise.getTableHeader().setResizingAllowed(false);
		tableFrise.getTableHeader().setReorderingAllowed(false);
		tableFrise.setRowHeight(100); // Hauteur des lignes
		
		tableFrise.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JScrollPane scrollPane = new JScrollPane(tableFrise,ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER , ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// Taille de la table
		scrollPane.setPreferredSize(new Dimension(1220,500));
		
		// Taille des colonnes
		for(int i=0; i<parChrono.getDuree();i++) {
			tableFrise.getColumnModel().getColumn(i).setPreferredWidth(120); // Largeur des colonnes
		}
		
		//Case cliquée
		tableFrise.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				ImageIcon selected = null;
				if (e.getClickCount() == 1) { // Nombre de clics à effectuer
					JTable target = (JTable)e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();
					selected = (ImageIcon) tableFrise.getValueAt(row, column);
					if (selected!=null)
							gestionnaire.show(panelDiapo,selected.getDescription());
							// chIndex = ...;
				}
			}
		});

		// Ajout d'un renderer
		tableFrise.setDefaultRenderer(Evenement.class, new CelluleRenderer());
		
		this.add(scrollPane);
	}

}
