package ui;

import javax.swing.*;
import java.awt.*;

public abstract class SearchSortAbstract {

    protected JFrame SearchSortframe;

    public SearchSortAbstract() {
        System.out.println("App constructor starting...");
        initGUI();
        showUI();
    }

    private void initGUI() {

        SearchSortframe = new JFrame("Automobile Shop");
        SearchSortframe.setSize(1000,1000);
        SearchSortframe.setResizable(true);
        SearchSortframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SearchSortframe.setVisible(true);
        SearchSortframe.add(getMainDisplayPanel(), BorderLayout.CENTER);
    }

    private void showUI() {
        SwingUtilities.invokeLater(() -> {
            SearchSortframe.setVisible(true);
        });
    }

    abstract JPanel getMainDisplayPanel();
}
