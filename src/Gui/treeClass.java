package Gui;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultTreeModel;
import Logic.Actions;
import java.awt.Dimension;

public class treeClass {

    public FileSystemView systemView;
    private DefaultTreeModel mainModel;
    private JTree tree;
    private JScrollPane scrollPane;
    private Actions action;
    FileTreeCellRenderer renderer;
    DefaultMutableTreeNode root;
    tableClass table;
    mainGui main;

    /**
     * Burada Pek Bişey Yapılmadı Ama Can Damarı Gibi Mübarek. 😭
     *
     * @return renderer desem anlayacan mı ?
     */
    public FileTreeCellRenderer getRenderer() {
        if (renderer == null) {
            renderer = new FileTreeCellRenderer();
        }
        return renderer;
    }

    public DefaultMutableTreeNode getRoot() {
        if (root == null) {
            root = new DefaultMutableTreeNode();
        }
        return root;
    }

    public tableClass getTable() {
        if (table == null) {
            table = new tableClass();
        }
        return table;
    }

    public JTree getTree() {
        if (tree == null) {
            mainModel = new DefaultTreeModel(getRoot());
            File[] roots = getSystemView().getRoots();
            for (File fileSystemRoot : roots) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(fileSystemRoot);
                root.add(node);
                File[] files = getSystemView().getFiles(fileSystemRoot, true);
                for (File file : files) {
                    if (file.isDirectory()) {
                        if (!file.getName().toLowerCase().equals("shellfolder")) {
                            node.add(new DefaultMutableTreeNode(file));
                        }
                    }
                }
            }
            tree = new JTree(mainModel);
            tree.addTreeSelectionListener(getAction().treeSelectionListener);
            tree.setRootVisible(false);
            tree.setLocation(0, 0);
            tree.expandRow(0);
            tree.setSize(mainGui.appWidth / 4, mainGui.appHeight * 3 / 4);
            tree.setCellRenderer(getRenderer());
            tree.setRowHeight(20);
        }
        return tree;
    }

    public mainGui getMain() {
        if (main == null) {
            main = new mainGui();
        }
        return main;
    }

    public Actions getAction() {
        if (action == null) {
            action = new Actions(this);
        }
        return action;
    }

    public FileSystemView getSystemView() {
        if (systemView == null) {
            systemView = FileSystemView.getFileSystemView();
        }
        return systemView;
    }

    public JScrollPane getScrollPane() {
        if (scrollPane == null) {
            scrollPane = new JScrollPane();
            scrollPane.setPreferredSize(new Dimension(getTree().getWidth(), getTree().getHeight()));
            scrollPane.getViewport().add(getTree());
        }
        return scrollPane;
    }

    public File[] getFiles(File file) {
        return getSystemView().getFiles(file, true);
    }

    public void addNewNode(DefaultMutableTreeNode node) {
        File[] files = getFiles((File) node.getUserObject());
        for (File file : files) {
            if (file.isDirectory()) {
                DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(file);
                node.add(newNode);
            }
        }
    }
}
