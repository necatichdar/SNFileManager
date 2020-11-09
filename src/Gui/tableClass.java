package Gui;

import Logic.Actions;
import java.io.File;
import javax.swing.JTable;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.util.Calendar;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;

public class tableClass {

    public static JTable table;
    JScrollPane scrollPane;
    treeClass tree;
    public DefaultMutableTreeNode selectedNode;
    Object[][] addData = null;
    private static File[] tableFiles;
    final String[] captions = {"Icon", "File Name", "Path", "Size", "Last Modified"};
    final String[] monthOfYear = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    mainGui main;
    Actions action;
    DefaultTableModel model;
    tableCellRenderer tableRenderer;

    /**
     * Acayip Birşeyler Yaptık Ama Tam Kestiremedim
     *
     * @return
     */
    public tableCellRenderer getTableRenderer() {
        if (tableRenderer == null) {
            tableRenderer = new tableCellRenderer();
        }
        return tableRenderer;
    }

    public DefaultTableModel getModel() {
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableFiles = getFiles((File) getSelectedNode().getUserObject());
        File file;
        addData = new String[tableFiles.length][captions.length];
        for (int i = 0; i < tableFiles.length; i++) {
            file = tableFiles[i];
            addData[i][1] = file.getName();                                           // File Name
            addData[i][2] = file.getPath();                                           // File Path
            addData[i][3] = getFileSize(file.length());                               // File Size
            addData[i][4] = convertToTime(file.lastModified());                       // Last Modified
        }
        model.setDataVector(addData, captions);
        model.fireTableDataChanged();
        return model;
    }

    public Actions getAction() {
        if (action == null) {
            action = new Actions(this);
        }
        return action;
    }

    public treeClass getTree() {
        if (tree == null) {
            tree = new treeClass();
        }
        return tree;
    }

    public DefaultMutableTreeNode getSelectedNode() {
        if (selectedNode == null) {
            selectedNode = new DefaultMutableTreeNode(new File(System.getProperty("user.home") + "/Desktop"));
        }
        return selectedNode;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public void setTableData(DefaultMutableTreeNode node) {
        this.selectedNode = node;
        File file = (File) node.getUserObject();
        tableFiles = getFiles(file);
        table.setModel(getModel());
        tableSettings();
    }

    public DefaultTableModel getTableModel() {
        return ((DefaultTableModel) getTable().getModel());
    }

    public JTable getTable() {
        if (table == null) {
            table = new JTable(getModel());
            setTable(table);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setAutoCreateRowSorter(true);
            table.setRowHeight(25);
            table.setFillsViewportHeight(true);
            table.setShowGrid(true);
            table.setShowVerticalLines(false);
            table.setLocation(0, 0);
            table.setSize(mainGui.appWidth * 3 / 4, mainGui.appHeight * 3 / 4);
            tableSettings();
        }
        return table;
    }

    void tableSettings() {
        int w = table.getWidth() * 19 / 20;
        setColumnWidth(0, table.getWidth() / 18);
        setColumnWidth(1, w / 3);
        setColumnWidth(2, w / 3);
        setColumnWidth(3, w / 6);
        setColumnWidth(4, w / 7);
        table.getColumnModel().getColumn(0).setCellRenderer(getTableRenderer());
        for (int i = 0; i < tableFiles.length; i++) {
            getTableModel().setValueAt(getTree().getSystemView().getSystemIcon(tableFiles[i]), i, 0);
        }
        table.getSelectionModel().addListSelectionListener(getAction().listListener);
    }

    void setColumnWidth(int index, int width) {
        TableColumn column = table.getColumnModel().getColumn(index);
        column.setMaxWidth(width);
        column.setMinWidth(width);
        column.setPreferredWidth(width);
    }

    public JScrollPane getScrollPane() {
        if (scrollPane == null) {
            scrollPane = new JScrollPane();
            scrollPane.setPreferredSize(new Dimension(getTable().getWidth(), getTable().getHeight()));
            scrollPane.getViewport().add(table);
        }
        return scrollPane;
    }

    public File[] getFiles(File file) {
        return getTree().getSystemView().getFiles(file, true);
    }

    private mainGui getMain() {
        if (main == null) {
            main = new mainGui();
        }
        return main;
    }

    public String getFileSize(long size) {
        DecimalFormat df = new DecimalFormat("0.00");
        float sizeKb = 1024.0f;
        float sizeMb = sizeKb * sizeKb;
        float sizeGb = sizeMb * sizeKb;
        float sizeTb = sizeGb * 1024;
        if (size < 1024) {
            return size + " Byte";
        } else if (size < sizeMb) {
            return df.format(size / sizeKb) + " KB";
        } else if (size < sizeGb) {
            return df.format(size / sizeMb) + " MB";
        } else if (size < sizeTb) {
            return df.format(size / sizeGb) + " GB";
        }
        return "o ne la 🤔";
    }

    public String convertToTime(long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        int day = c.get(Calendar.DAY_OF_MONTH);
        String month = monthOfYear[c.get(Calendar.MONTH)];
        int year = c.get(Calendar.YEAR);
        return String.format("%s %s %s", day, month, year);
    }
}
