package vue;

import java.awt.Color;
import java.awt.Component;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import model.Evenement;

public class CelluleRenderer extends DefaultTableCellRenderer {

	public CelluleRenderer() {
		super();
		setOpaque(true);
		setHorizontalAlignment(JLabel.CENTER);
		this.setForeground(new Color(180,100,40));
	}
	
@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		if(isSelected) {
			setBackground(new Color(255,213,214));
		}else {
			setBackground(new Color(234,255,251));
		}
		if (value!=null) {
			String img = ((Evenement)value).getImg();
			System.out.println(img);
			File fichierImage = new File("images/"+img+".png");
			if (fichierImage.isFile()) {
				setIcon(new ImageIcon(getClass().getResource("images/"+img+".png")));
			}else if ((fichierImage = new File("images/"+img+".jpg")).isFile()){
				setIcon(new ImageIcon(getClass().getResource("images/"+img+".jpg")));
			}
		}else {
			setIcon(null);
		}
		return this;
	}

}
