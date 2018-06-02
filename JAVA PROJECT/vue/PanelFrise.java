package vue;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;


import model.Chronologie;
import model.Evenement;

public class PanelFrise extends JPanel {
	
	public PanelFrise(Chronologie parChrono){
		TableFrise modele = new TableFrise(parChrono);
		JTable tableFrise = new JTable(modele);
		
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
		
		/*
		tableFrise.addMouseListener(new MouseAdapter(){
			public void MouseClicked(MouseEvent evt) {
				JTable table = (JTable) evt.getSource();
				TableFrise model = (TableFrise)table.getModel();
				Point point = evt.getPoint();
				int rowIndex = table.rowAtPoint(point);
				int colIndex = table.columnAtPoint(point);
				JOptionPane.showMessageDialog(table, model.getValueAt(rowIndex, colIndex));
			}
		});
		*/
		
		// Ajout d'un renderer
		tableFrise.setDefaultRenderer(Evenement.class, new CelluleRenderer());
		
		this.add(scrollPane);
	}
}
