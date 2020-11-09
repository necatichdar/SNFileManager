package Gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class mainGui {

    JFrame frame = null;
    JPanel panel = null, p2 = null, p = null, p3 = null, p4 = null;
    treeClass tree;
    tableClass table;
    toolBarClass toolBar;
    fileDetails details;
    public static int appWidth, appHeight;

    public fileDetails getDetails() {
        if (details == null) {
            details = new fileDetails();
        }
        return details;
    }

    public toolBarClass getToolBar() {
        if (toolBar == null) {
            toolBar = new toolBarClass();
        }
        return toolBar;
    }

    public treeClass getTreeClass() {
        if (tree == null) {
            tree = new treeClass();
        }
        return tree;
    }

    public tableClass getTableClass() {
        if (table == null) {
            table = new tableClass();
        }
        return table;
    }

    public mainGui() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        appWidth = screenSize.width * 3 / 4;
        appHeight = screenSize.height * 3 / 4;
        getFrame().setVisible(true);
    }

    JFrame getFrame() {
        if (frame == null) {
            frame = new JFrame("SNFileManager");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocation(appWidth / 8, appHeight / 8);
            frame.setSize(appWidth, appHeight + 34);
            frame.setContentPane(getPanel());
            frame.setResizable(false);
        }
        return frame;
    }

    public JPanel getP2() {
        if (p2 == null) {
            p2 = new JPanel();
            p2.setLocation(appWidth / 4, 0);
            p2.setSize(appWidth * 3 / 4, appHeight * 3 / 4);
            p2.add(getTableClass().getScrollPane());
        }
        return p2;
    }

    public JPanel getP() {
        if (p == null) {
            p = new JPanel();
            p.setLocation(0, 0);
            p.setSize(appWidth / 4, appHeight * 3 / 4);
            p.add(getTreeClass().getScrollPane());
        }
        return p;
    }

    public JPanel getP3() {
        if (p3 == null) {
            p3 = new JPanel();
            p3.setLocation(0, appHeight * 3 / 4);
            p3.setSize(appWidth, appHeight / 12);
            p3.add(getToolBar().getToolBar());
        }
        return p3;
    }

    public JPanel getP4() {
        if (p4 == null) {
            p4 = new JPanel();
            p4.setSize(appWidth, appHeight / 6);
            p4.setLocation(0, appHeight * 5 / 6);
            p4.add(getDetails().getScrollPane());
        }
        return p4;
    }

    public JPanel getPanel() {
        if (panel == null) {
            panel = new JPanel(new GridLayout(2, 1));
            panel.setLayout(null);
            panel.add(getP());                      // Tree
            panel.add(getP2());                     // Table
            panel.add(getP3());                     // ToolBar
            panel.add(getP4());                     // List
        }
        return panel;
    }
}
