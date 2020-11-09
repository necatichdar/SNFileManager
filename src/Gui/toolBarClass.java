package Gui;

import Logic.Actions;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

public class toolBarClass {

    // Start Variables //
    JToolBar toolBar;
    treeClass tree;
    mainGui main;
    tableClass table;
    JButton open, edit, delete, newfile, copy, paste, addToPlaylist, removeToPlaylist, playMP3, pauseMP3, forwardMP3, backwardMP3;
    Actions eksin;
    Desktop desktop;
    // Finish Variables //

    // Start Constructors //
    public mainGui getMain() {
        if (main == null) {
            main = new mainGui();
        }
        return main;
    }
    // Finish Constructors //

    // Start Get-Set Methods //
    // <Desktop>
    public Desktop getDesktop() {
        if (desktop == null) {
            desktop = Desktop.getDesktop();
        }
        return desktop;
    }
    // </Desktop>

    // <Actions>
    public Actions getEksin() {
        if (eksin == null) {
            eksin = new Actions(this);

        }
        return eksin;
    }
    // </Actions>

    // <TreeClass>
    public treeClass getTree() {
        if (tree == null) {
            tree = new treeClass();
        }
        return tree;
    }
    // </TreeClass>

    //<TableClass>
    public tableClass getTable() {
        if (table == null) {
            table = new tableClass();
        }
        return table;
    }
    // </TableClass>
    // Finish Get-Set Methods //

    // Add Components Start //
    // <Open Button>
    public JButton getOpen() {
        if (open == null) {
            open = new JButton("Open");
            open.setMargin(new Insets(0, 3, 0, 3));
            open.setToolTipText("Open Selected File on Default Windows File Manager");
            open.setBorderPainted(false);
            open.addActionListener(getEksin().open);
        }
        return open;
    }
    // </Open Button>

    // <Rename Button>
    public JButton getEdit() {
        if (edit == null) {
            edit = new JButton("Rename");
            edit.setMargin(new Insets(0, 3, 0, 3));
            edit.setToolTipText("Rename Selected File");
            edit.setBorderPainted(false);
            edit.addActionListener(getEksin().rename);
        }
        return edit;
    }
    // </Rename Button>

    // <Delete Button>
    public JButton getDelete() {
        if (delete == null) {
            delete = new JButton("Delete");
            delete.setMargin(new Insets(0, 3, 0, 3));
            delete.setToolTipText("Delete Selected File");
            delete.setBorderPainted(false);
            delete.addActionListener(getEksin().delete);
        }
        return delete;
    }
    // </Delete Button>

    // <NewFile Button>
    public JButton getNewFile() {
        if (newfile == null) {
            newfile = new JButton("New File");
            newfile.setMargin(new Insets(0, 3, 0, 3));
            newfile.setToolTipText("Create A New File");
            newfile.setBorderPainted(false);
            newfile.addActionListener(getEksin().newFile);
        }
        return newfile;
    }
    // </NewFile Button>

    // <Copy Button>
    public JButton getCopy() {
        if (copy == null) {
            copy = new JButton("Copy");
            copy.setMargin(new Insets(0, 3, 0, 3));
            copy.setToolTipText("Copy Selected File");
            copy.setBorderPainted(false);
            copy.addActionListener(getEksin().copyFile);
        }
        return copy;
    }
    // </Copy Button>

    // <Paste Button>
    public JButton getPaste() {
        if (paste == null) {
            paste = new JButton("Paste");
            paste.setMargin(new Insets(0, 3, 0, 3));
            paste.setToolTipText("Paste Recent Copied File");
            paste.setBorderPainted(false);
            paste.addActionListener(getEksin().pasteFile);
        }
        return paste;
    }
    // </Paste Button>

    // <addToPlaylist Button>
    public JButton getAddToPlaylist() {
        if (addToPlaylist == null) {
            addToPlaylist = new JButton("Add To Playlist");
            addToPlaylist.setMargin(new Insets(0, 3, 0, 3));
            addToPlaylist.setToolTipText("Add To Playlist");
            addToPlaylist.setBorderPainted(false);
            addToPlaylist.setFont(new Font("Tahoma", Font.BOLD, 12));
            addToPlaylist.addActionListener(getEksin().addToPlaylist);
        }
        return addToPlaylist;
    }
    // </addToPlaylist Button>

    // <removeToPlaylist Button>
    public JButton getRemoveToPlaylist() {
        removeToPlaylist = new JButton("Remove To Playlist");
        removeToPlaylist.setMargin(new Insets(0, 3, 0, 3));
        removeToPlaylist.setToolTipText("Remove To Playlist");
        removeToPlaylist.setBorderPainted(false);
        removeToPlaylist.setFont(new Font("Tahoma", Font.BOLD, 12));
        removeToPlaylist.addActionListener(getEksin().removeToPlaylist);
        return removeToPlaylist;
    }
    // </removeToPlaylist Button>

    // <playMP3 Button>
    public JButton getPlayMP3() {
        if (playMP3 == null) {
            Icon playIcon = new ImageIcon(getClass().getResource("play.png"));
            playMP3 = new JButton(playIcon);
            playMP3.setBorder(null);
            playMP3.setBorderPainted(false);
            playMP3.setToolTipText("Play/Replay Playlist");
            playMP3.addActionListener(getEksin().playMP3);
        }
        return playMP3;
    }
    // </playMP3 Button>

    // <pauseButton>
    public JButton getPauseMP3() {
        if (pauseMP3 == null) {
            Icon pauseIcon = new ImageIcon(getClass().getResource("pause.png"));
            pauseMP3 = new JButton(pauseIcon);
            pauseMP3.setBorder(null);
            pauseMP3.setBorderPainted(false);
            pauseMP3.setToolTipText("Pause Playlist");
            pauseMP3.addActionListener(getEksin().pauseMP3);
        }
        return pauseMP3;
    }

    // <forwardMP3 Button>
    public JButton getForwardMP3() {
        if (forwardMP3 == null) {
            Icon forwardIcon = new ImageIcon(getClass().getResource("forward.png"));
            forwardMP3 = new JButton(forwardIcon);
            forwardMP3.setBorderPainted(false);
            forwardMP3.setBorder(null);
            forwardMP3.setToolTipText("Forward Song");
            forwardMP3.addActionListener(getEksin().forwardMP3);
        }
        return forwardMP3;
    }
    // </forwardMP3 Button>

    // <backwardMP3 Button>
    public JButton getBackwardMP3() {
        if (backwardMP3 == null) {
            Icon backwardIcon = new ImageIcon(getClass().getResource("backward.png"));
            backwardMP3 = new JButton(backwardIcon);
            backwardMP3.setBorderPainted(false);
            backwardMP3.setBorder(null);
            backwardMP3.setToolTipText("Backward Song");
            backwardMP3.addActionListener(getEksin().forwardMP3);
        }
        return backwardMP3;
    }
    // </backwardMP3 Button>
    // Finish Components Add //

    // <ToolBar>
    public JToolBar getToolBar() {
        if (toolBar == null) {
            toolBar = new JToolBar();
            toolBar.setFloatable(false);
            toolBar.setSize(mainGui.appWidth, mainGui.appHeight / 12);
            toolBar.setBorderPainted(false);
            toolBar.add(getOpen());
            toolBar.addSeparator();
            toolBar.add(getEdit());
            toolBar.addSeparator();
            toolBar.add(getCopy());
            toolBar.addSeparator();
            toolBar.add(getPaste());
            toolBar.addSeparator();
            toolBar.add(getNewFile());
            toolBar.addSeparator();
            toolBar.add(getDelete());
            for (int i = 0; i < 5; i++) {
                toolBar.addSeparator();
            }
            toolBar.add(getAddToPlaylist());
            toolBar.addSeparator();
            toolBar.add(getRemoveToPlaylist());
            toolBar.addSeparator();
            toolBar.add(getBackwardMP3());
            toolBar.addSeparator();
            toolBar.add(getPlayMP3());
            toolBar.addSeparator();
            toolBar.add(getPauseMP3());
            toolBar.addSeparator();
            toolBar.add(getForwardMP3());
        }
        return toolBar;
    }
    // </ToolBar>
    // Finish Get-Set Methods //

    // Start Used Functions //
    // <showSuccessMessage>
    public void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Successfull", JOptionPane.PLAIN_MESSAGE);
    }
    // </showSuccessMessage>

    // <showErrorMessage>
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Error Occured", JOptionPane.ERROR_MESSAGE);
    }
    // </showErrorMessage>

    // <changePlayIcon>
    public void changeButtonsEnabled(Boolean i) {
        // i:0 => Pause Disable
        // i:1 => Play Disable
        getPlayMP3().setEnabled(i);
        getPauseMP3().setEnabled(!i);
        getToolBar().revalidate();
    }
    // </changePlayIcon>

    // <pasteCopiedFile>
    public void pasteCopiedFile(File source, File dest) throws IOException {
        FileChannel sourceChannel = new FileInputStream(source).getChannel();
        FileChannel destChannel = new FileOutputStream(dest).getChannel();
        try {
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            showSuccessMessage("File Copied Successfully !");
        } catch (IOException ex) {
            showErrorMessage(ex.getMessage());
        } finally {
            sourceChannel.close();
            destChannel.close();
        }
    }
    // </pasteCopiedFile>
    // Finish Used Functions //

    // Start Action Functions //
    // <Open Button>
    public void openFile(File selectedFile) {
        try {
            getDesktop().open(selectedFile);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "File Not Found !");
        }
    }
    // </Open Button>

    // <Rename Button>
    public void renameFile(File selectedFile, String newName) {
        File newFile = new File(selectedFile.getParentFile() + "/" + newName);
        for (File file : getTable().getFiles(selectedFile.getParentFile())) {
            if (file.equals(newFile)) {
                showErrorMessage("This File Already Exists !\nPlease Try Different File Name !");
                return;
            }
        }
        if (selectedFile.renameTo(newFile)) {
            showSuccessMessage("File Renamed Successfully !");
        } else {
            showErrorMessage("An Error Occured While Rename File !");
        }
    }
    // </Rename Button>

    // <Delete Button>
    public void deleteFile(File selectedFile) {
        if (selectedFile.delete()) {
            showSuccessMessage("File Delete Successfully");
        } else {
            showErrorMessage("An Error Occured While Delete File !");
        }
    }
    // </Delete Button>

    // <New File Button>
    public void createFile(File fileName, int type) {
        // type 0: file
        // type 1: directory
        File parent = fileName.getParentFile();
        for (File file : getTable().getFiles(parent)) {
            if (file.equals(fileName)) {
                showErrorMessage("This File Already Exsits !\nPlease Try Different File Name !");
                return;
            }
        }
        if (type == 0) {
            try {
                fileName.createNewFile();
                showSuccessMessage("File Created Successfully !");
            } catch (IOException ex) {
                showErrorMessage("An Error Occurred While Create New File !");
            }
        } else if (type == 1) {
            if (fileName.mkdir()) {
                showSuccessMessage("Directory Created Successfully !");
            } else {
                showErrorMessage("An Error Occurred While Create New Directory !");
            }
        }
    }
    // </New File Button>
    // Finish Action Functions
}
