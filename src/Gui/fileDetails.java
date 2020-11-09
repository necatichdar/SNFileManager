package Gui;

import Logic.Actions;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import jaco.mp3.player.MP3Player;
import jaco.mp3.player.c;
import java.awt.Dimension;
import java.io.File;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class fileDetails {

    MP3Player player;
    private static JTable table;
    DefaultListModel listModel;
    mainGui gui;
    List<File> playList;
    String[][] addData;
    private static DefaultTableModel model;
    File selectedMP3;
    treeClass tree;
    Actions action;
    final String[] captions = {"Name", "Duration", "Path"};
    JScrollPane scrollPane;

    public mainGui getMainGui() {
        if (gui == null) {
            gui = new mainGui();
        }
        return gui;
    }

    public JTable getTable() {
        if (table == null) {
            table = new JTable();
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setAutoCreateRowSorter(true);
            table.setRowHeight(25);
            table.setFillsViewportHeight(true);
            table.setShowGrid(true);
            table.setLocation(0, 0);
            table.setSize(mainGui.appWidth, mainGui.appHeight / 6);
            table.setModel(getModel());
        }
        return table;
    }

    public void setTableData(File selectedMP3) {
        model.addRow(new Object[]{selectedMP3.getName(), mp3Duration(selectedMP3), selectedMP3.getPath()});
        tableSettings();
    }

    public void removeTableData(int index) {
        model.removeRow(index);
        tableSettings();
        getPlayer().getPlayList().remove(index);
        if (getPlayer().getPlayList().isEmpty()) {
            getPlayer().stop();
        }
    }

    public DefaultTableModel getModel() {
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        if (getSelectedMP3() != null) {
            addData = new String[playList.size()][captions.length];
            for (int i = 0; i < playList.size(); i++) {
                File f = playList.get(i);
                addData[i][0] = f.getName();
                addData[i][1] = mp3Duration(f);
                addData[i][2] = f.getPath();
            }
        }
        model.setDataVector(addData, captions);
        model.fireTableDataChanged();
        return model;
    }

    public JScrollPane getScrollPane() {
        if (scrollPane == null) {
            scrollPane = new JScrollPane();
            scrollPane.setPreferredSize(new Dimension(getTable().getWidth(), getTable().getHeight()));
            scrollPane.getViewport().add(table);
        }
        return scrollPane;
    }

    public MP3Player getPlayer() {
        if (player == null) {
            player = new MP3Player();
        }
        return player;
    }

    String mp3Duration(File file) {
        Encoder encoder = new Encoder();
        try {
            int x = 1045650;
            DecimalFormat df = new DecimalFormat("##"); // Set your desired format here.
            MultimediaInfo mi = encoder.getInfo(file);
            long milliSecond = mi.getDuration();
            int minutes = (int) (milliSecond / (1000 * 60));
            int seconds = (int) (milliSecond - (minutes * 60 * 1000)) / 1000;
            return minutes + ":" + seconds;
        } catch (EncoderException e) {
            return null;
        }
    }

    public treeClass getTree() {
        if (tree == null) {
            tree = new treeClass();
        }
        return tree;
    }

    public void setSelectedMP3(File selectedMP3) {
        this.selectedMP3 = selectedMP3;
    }

    public File getSelectedMP3() {
        return selectedMP3;
    }

    void tableSettings() {
        int w = getTable().getWidth();
        for (int i = 0; i < 2; i++) {
            setColumnWidth(i, w / 3);
        }
        table.getSelectionModel().addListSelectionListener(getAction().mp3Listener);
    }

    public Actions getAction() {
        if (action == null) {
            action = new Actions(this);
        }
        return action;
    }

    void setColumnWidth(int index, int width) {
        TableColumn column = table.getColumnModel().getColumn(index);
        column.setMaxWidth(width);
        column.setMinWidth(width);
        column.setPreferredWidth(width);
    }

    public DefaultTableModel getTableModel() {
        return ((DefaultTableModel) getTable().getModel());
    }
}
