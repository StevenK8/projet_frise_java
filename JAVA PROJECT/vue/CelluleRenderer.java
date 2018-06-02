package vue;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import model.Evenement;

public class CelluleRenderer extends JLabel implements TableCellRenderer {

	public CelluleRenderer() {
		super();
		setOpaque(true);
		setHorizontalAlignment(JLabel.CENTER);
		this.setForeground(new Color(180,100,40));
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Evenement evt = (Evenement) value;
		setBackground(new Color(234,255,251));
		setIcon(new ImageIcon("images/"+evt.getImg()+".jpg"));
		return this;
	}

}
