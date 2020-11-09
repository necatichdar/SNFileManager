package Logic;

import Gui.*;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Actions {

    // Start Variables //
    tableClass table;
    treeClass tree;
    toolBarClass option;
    fileDetails details;
    public static File selectedFile, selectedMP3;
    Desktop desktop;
    File copiedFile;
    static int index = -1;
    // Finish Variables //

    // Start Constructors //
    public Actions(treeClass tree) {

    }

    public Actions(tableClass table) {

    }

    public Actions(toolBarClass option) {
    }

    public Actions(fileDetails details) {

    }
    // Finish Constructors //

    // Start Click Listeners //
    // <Open Button>
    public ActionListener open = (ActionEvent) -> {
        getOption().openFile(getSelectedFile());
    };
    // </Open Button>

    // <Rename Button>
    public ActionListener rename = (ActionEvent) -> {
        String newName = JOptionPane.showInputDialog("Write New Name");
        if (newName == null || newName.isEmpty()) {
            getOption().showErrorMessage("New Name Can't Be Null Or Empty !");
        } else {
            getOption().renameFile(getSelectedFile(), newName);
        }
    };
    // </Rename Button>

    // <Delete Button>
    public ActionListener delete = (ActionEvent) -> {
        if (JOptionPane.showConfirmDialog(null, "Are You Sure to Delete This File ?") == 0) {
            getOption().deleteFile(getSelectedFile());
        }
    };
    // </Delete Button>

    // <New File Button>
    public ActionListener newFile = (ActionEvent) -> {
        JPanel newFilePanel = new JPanel(new BorderLayout(3, 3));
        JPanel southRadio = new JPanel(new GridLayout(1, 0, 2, 2));
        JRadioButton newTypeFile = new JRadioButton("File", true);
        JRadioButton newTypeDirectory = new JRadioButton("Directory");
        ButtonGroup bg = new ButtonGroup();
        bg.add(newTypeFile);
        bg.add(newTypeDirectory);
        southRadio.add(newTypeFile);
        southRadio.add(newTypeDirectory);
        JTextField name = new JTextField(15);
        newFilePanel.add(new JLabel("Name"), BorderLayout.WEST);
        newFilePanel.add(name);
        newFilePanel.add(southRadio, BorderLayout.SOUTH);
        if (JOptionPane.showConfirmDialog(null, newFilePanel, "Create File", JOptionPane.OK_CANCEL_OPTION) == 0) {
            int type = -1;
            if (newTypeFile.isSelected()) {
                type = 0;
            } else if (newTypeDirectory.isSelected()) {
                type = 1;
            }
            String file = name.getText();
            File newFile1 = new File(getSelectedFile() + "/" + file);
            getOption().createFile(newFile1, type);
            getTable().setTableData(new DefaultMutableTreeNode(getSelectedFile()));
        }
    };
    // </New File Button>

    // <Copy Button>
    public ActionListener copyFile = (ActionEvent) -> {
        setCopiedFile(getSelectedFile());
        getOption().getPaste().setEnabled(true);
        getTable().setTableData(new DefaultMutableTreeNode(getSelectedFile()));
    };
    // </Copy Button>

    // <Paste Button>
    public ActionListener pasteFile = (ActionEvent) -> {
        if (getCopiedFile() == null) {
            getOption().showErrorMessage("Source File Can't Be Empty !");
            return;
        }
        if (!getSelectedFile().isDirectory()) {
            getOption().showErrorMessage("Destination File Must Be Directory !");
            return;
        }
        try {
            getOption().pasteCopiedFile(getCopiedFile(), getSelectedFile());
        } catch (IOException ex) {
            getOption().showErrorMessage(String.format("File Don't Copied to Destination.\nError Message: %s", ex.getMessage()));
        }
    };
    // </Paste Button>

    // <addToPlaylist Button>
    public ActionListener addToPlaylist = (ActionEvet) -> {
        if (getSelectedFile().isDirectory()) {
            int added = 0;
            for (File file : getTable().getFiles(getSelectedFile())) {
                int index1 = file.toString().lastIndexOf('.');
                String extension = file.toString().substring(index1 + 1).toLowerCase();
                if (extension.equals("mp3")) {
                    getDetails().getPlayer().addToPlayList(file);
                    getDetails().setTableData(file);
                    added++;
                }
            }
            if (added == 0) {
                getOption().showErrorMessage("Not Found Any MP3 File In Selected Directory !");
            } else {
                getOption().showSuccessMessage(added + " MP3 File" + (added == 1 ? "" : "s") + " Found And Added To Playlist !");
            }
            return;
        }
        int index1 = getSelectedFile().toString().lastIndexOf('.');
        String extension = getSelectedFile().toString().substring(index1 + 1).toLowerCase();
        if (!extension.equals("mp3")) {
            getOption().showErrorMessage("Selected File Is Not MP3 File !");
            return;
        }
        getDetails().getPlayer().addToPlayList(getSelectedFile());
        getDetails().setTableData(getSelectedFile());
    };
    // </addToPlaylist Button>

    // <removeToPlaylist Button>
    public ActionListener removeToPlaylist = (ActionEvent) -> {
        if (index != -1) {
            getDetails().removeTableData(index);
        }
    };
    // </removeToPlaylist Button>

    // <playMP3 Button>
    public ActionListener playMP3 = (ActionEvent) -> {
        if (getDetails().getPlayer().getPlayList().size() > 0) {
            getDetails().getPlayer().play();
            getOption().changeButtonsEnabled(true);
        } else {
            getOption().showErrorMessage("Playlist Is Empty !");
        }
    };
    // </playMP3 Button>

    // <pauseMP3 Button>
    public ActionListener pauseMP3 = (ActionEvent) -> {
        if (getDetails().getPlayer().getPlayList().size() > 0) {
            getDetails().getPlayer().pause();
            getOption().changeButtonsEnabled(false);
        } else {
            getOption().showErrorMessage("Playlist Is Empty !");
        }
    };
    // </pauseMP3 Button>

    // <backwardMP3 Button>
    public ActionListener backwardMP3 = (ActionEvent) -> {
        getDetails().getPlayer().skipBackward();
    };
    // </backwardMP3 Button>

    // <forwardMP3 Button>
    public ActionListener forwardMP3 = (ActionEvent) -> {
        getDetails().getPlayer().skipForward();
    };
    // </forwardMP3 Button>
    // Finish Click Listeners //

    // Start Get-Set Methods //
    // <ToolBarClas>
    public toolBarClass getOption() {
        if (option == null) {
            option = new toolBarClass();
        }
        return option;
    }
    // </ToolBarClass>

    // <Desktop>
    public Desktop getDesktop() {
        if (desktop == null) {
            desktop = Desktop.getDesktop();
        }
        return desktop;
    }
    // </Desktop>

    // <fileDetails>
    public fileDetails getDetails() {
        if (details == null) {
            details = new fileDetails();
        }
        return details;
    }
    // </fileDetails>

    // <TreeClass>
    public treeClass getTree() {
        if (tree == null) {
            tree = new treeClass();
        }
        return tree;
    }
    // </TreeClass>

    // <TableClass>
    public tableClass getTable() {
        if (table == null) {
            table = new tableClass();
        }
        return table;
    }

    // <copiedFile Variable>
    public File getCopiedFile() {
        return copiedFile;
    }

    public void setCopiedFile(File copiedFile) {
        this.copiedFile = copiedFile;
    }
    // </copiedFile Variable>

    // <SelectedFile Variable>
    public File getSelectedFile() {
        if (selectedFile == null) {
            selectedFile = new File(System.getProperty("user.home") + "/Desktop");
        }
        return selectedFile;
    }

    public void setSelectedFile(File selectedFile) {
        Actions.selectedFile = selectedFile;
    }
    // </SelectedFile Variable>

    public static File getSelectedMP3() {
        return selectedMP3;
    }

    // <SelectedMP3 Variable>
    public static void setSelectedMP3(File selectedMP3) {
        Actions.selectedMP3 = selectedMP3;
    }
    // </SelectedMP3 Variable>
    // Finish Get-Set Methods //

    // Start Selection Listeners //
    // <treeClass JTree>
    public TreeSelectionListener treeSelectionListener = (TreeSelectionEvent tse) -> {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tse.getPath().getLastPathComponent();
        if (node != null) {
            getTree().addNewNode(node);
            getTable().setTableData(node);
            setSelectedFile((File) node.getUserObject());
        }
    };
    // </treeClass JTree>

    //  <tableClass JTable>
    public ListSelectionListener listListener = (ListSelectionEvent lse) -> {
        int index1 = getTable().getTable().getSelectionModel().getLeadSelectionIndex();
        if (index1 != -1) {
            //setSelectedFile(new File(getTable().getTableModel().getValueAt(index1, 2).toString()));
        }
    };
    // </tableClass JTable>

    // <fileDetails JTable>
    public ListSelectionListener mp3Listener = (ListSelectionEvent lse) -> {
        index = getDetails().getTable().getSelectionModel().getLeadSelectionIndex();
    };
    // </fileDetails JTable>
    // Finish Selection Listeners //
}
