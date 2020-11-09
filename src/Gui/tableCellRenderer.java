package Gui;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

class tableCellRenderer implements TableCellRenderer {

    JLabel iconLabel = new JLabel();

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        iconLabel.setIcon((ImageIcon) value);
        return iconLabel;
    }
}
