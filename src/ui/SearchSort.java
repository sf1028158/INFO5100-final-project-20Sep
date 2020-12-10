package ui;
import dao.Vehicle;

import javax.swing.*;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

public class SearchSort extends SearchSortAbstract{
    private JPanel mainDisplayPanel;
    private JPanel filterPanel;
    private JPanel vehicleDisplayPanel;
    private JPanel sortPanel;
    private HashSet<String> selected;
    private final int[] selectedSort = {0};
    private ArrayList<String[]> selectedList;
    private final int FILTER_CATEGROY_COUNT = 7;
    private final Integer[] ORDER = {2, 4, 5, 7, 6, 8, 8};

    @Override
    JPanel getMainDisplayPanel() {

        // init panel


        mainDisplayPanel = new JPanel(new GridLayout(3,1));

        // add Component to mailDisplayPanel;
        mainDisplayPanel.add(getFilterPanel());
        mainDisplayPanel.add(getSortPanel());
        mainDisplayPanel.add(getVehicleDisplayPanel());

        return mainDisplayPanel;
    }

    private JPanel getFilterPanel() {
        //code for UI for filterPanel  ie filters for searching
        selected = new HashSet<String>();
        filterPanel = new JPanel();
        filterPanel.setBackground(Color.red);
        filterPanel.add(new JLabel("FILTER"));
        addFilterChoice("CATEGORY", filterPanel);
        addFilterChoice("MAKE", filterPanel);
        addFilterChoice("MODEL", filterPanel);
        addFilterChoice("TYPE", filterPanel);
        addFilterChoice("BODY STYLE", filterPanel);
        addFilterChoice("ABOVE PRICE", filterPanel);
        addFilterChoice("BELOW PRICE", filterPanel);
        addFilterChoice("MORE", filterPanel);
        ((AbstractButton) filterPanel.add(new JButton("Clear All"))).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	selected.clear();
            	//System.out.println(selected.size());
            }
        });
        return filterPanel;
    }

    private void addFilterChoice(String choice, JPanel panel) {
    	JButton choiceButton = new JButton(choice);
    	JPopupMenu menu = new JPopupMenu();
        if (choice.equals("CATEGORY")) {
        	menu.add(new JCheckBoxMenuItem("New"));
            menu.add(new JCheckBoxMenuItem("Certified Pre-Owned"));
            menu.add(new JCheckBoxMenuItem("Pre-Owned"));
        } else if (choice.equals("MAKE")) {
        	menu.add(new JCheckBoxMenuItem("Chevrolet"));
            menu.add(new JCheckBoxMenuItem("BOW"));
            menu.add(new JCheckBoxMenuItem("Jeep"));
            menu.add(new JCheckBoxMenuItem("Mini"));
            menu.add(new JCheckBoxMenuItem("Nissan"));
            menu.add(new JCheckBoxMenuItem("Toyota"));
        } else if (choice.equals("MODEL")) {
        	menu.add(new JCheckBoxMenuItem("Acadia"));
            menu.add(new JCheckBoxMenuItem("Blazer"));
            menu.add(new JCheckBoxMenuItem("Bolt EV"));
        } else if (choice.equals("TYPE")) {
        	menu.add(new JCheckBoxMenuItem("Car"));
            menu.add(new JCheckBoxMenuItem("Cargo Van"));
            menu.add(new JCheckBoxMenuItem("SUV"));
            menu.add(new JCheckBoxMenuItem("Truck"));
            menu.add(new JCheckBoxMenuItem("Van"));
            menu.add(new JCheckBoxMenuItem("Wagon"));
        } else if (choice.equals("BODY STYLE")) {
        	menu.add(new JCheckBoxMenuItem("Crew Crab Pickup - Long Bed"));
            menu.add(new JCheckBoxMenuItem("Crew Crab Pickup - Short Bed"));
            menu.add(new JCheckBoxMenuItem("Crew Crab Pickup - Standard Bed"));
        } else if (choice.equals("Year")) {
            menu.add(new JCheckBoxMenuItem("2009"));
            menu.add(new JCheckBoxMenuItem("2010"));
            menu.add(new JCheckBoxMenuItem("2011"));
            menu.add(new JCheckBoxMenuItem("2012"));
            menu.add(new JCheckBoxMenuItem("2013"));
            menu.add(new JCheckBoxMenuItem("2014"));
            menu.add(new JCheckBoxMenuItem("2015"));
            menu.add(new JCheckBoxMenuItem("2016"));
            menu.add(new JCheckBoxMenuItem("2017"));
            menu.add(new JCheckBoxMenuItem("2018"));
            menu.add(new JCheckBoxMenuItem("2019"));
            menu.add(new JCheckBoxMenuItem("2020"));
            menu.add(new JCheckBoxMenuItem("2021"));
        } else if (choice.equals("ABOVE PRICE")) {
        	menu.add(new JCheckBoxMenuItem("1000"));
        	menu.add(new JCheckBoxMenuItem("5000"));
        	menu.add(new JCheckBoxMenuItem("10000"));
        	menu.add(new JCheckBoxMenuItem("15000"));
        	menu.add(new JCheckBoxMenuItem("20000"));
        	menu.add(new JCheckBoxMenuItem("25000"));
        	menu.add(new JCheckBoxMenuItem("30000"));
        } else if (choice.equals("BELOW PRICE")) {
        	menu.add(new JCheckBoxMenuItem("1000"));
        	menu.add(new JCheckBoxMenuItem("5000"));
        	menu.add(new JCheckBoxMenuItem("10000"));
        	menu.add(new JCheckBoxMenuItem("15000"));
        	menu.add(new JCheckBoxMenuItem("20000"));
        	menu.add(new JCheckBoxMenuItem("25000"));
        	menu.add(new JCheckBoxMenuItem("30000"));
        	menu.add(new JCheckBoxMenuItem("35000"));
        	menu.add(new JCheckBoxMenuItem("40000"));
        	menu.add(new JCheckBoxMenuItem("45000"));
        } else {
        	menu.add(new JCheckBoxMenuItem("Wait for coming"));
        }
        // add action listener to each ChekcBoxMenuItem
        for (Component item: menu.getComponents()) {
        	((AbstractButton) item).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                  if (!((JCheckBoxMenuItem)item).isSelected()) {
                	  selected.remove(e.toString().split(",")[1].substring(4));
                  } else {
                	  selected.add(e.toString().split(",")[1].substring(4));
                  }
                  // System.out.println(selected);
                  try {
                	  Thread.sleep(1000);
                  } catch (InterruptedException ie) {
                	  ie.printStackTrace();
                  }
                }
              });}

        // combine each button with CheckBoxMenu
        choiceButton.setAction(new AbstractAction(choice) {
            /**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
                menu.show(choiceButton, 0, choiceButton.getHeight());
            }
        });

        panel.add(choiceButton);
    }

    // sort the result as user selected and provide the result to display panel to display
    private JPanel getSortPanel() {
        //UI for sorting Panel for sorting the result on the basis on certain criterias

        sortPanel = new JPanel();
        sortPanel.setSize(600, 200);
        sortPanel.add(new Label("How would you like to sort the results?"));
        JPanel temp = new JPanel();
        JTextArea text = new JTextArea("");
        temp.add(text);
        String[] sortOptions = {"", "Price: High to low", "Price: Low to High", "Year: High to Low",
                "Year: Low to High"};
        JComboBox sortList = new JComboBox(sortOptions);
        sortList.setSelectedIndex(0);
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int s = (int) sortList.getSelectedIndex();
                switch(s) {
                    case 1:
                        text.setText("Selected: Sort price from high to low");
                        selectedSort[0] = 1;
                        break;
                    case 2:
                        text.setText("Selected: Sort price form low to high");
                        selectedSort[0] = 2;
                        break;
                    case 3:
                        text.setText("Selected: Sort year from high to low");
                        selectedSort[0] = 3;
                        break;
                    case 4:
                        text.setText("Selected: Sort year from low to high");
                        selectedSort[0] = 4;
                        break;
                }
                text.setEditable(false);
            }
        };
        sortList.addActionListener(actionListener);
        sortPanel.add(sortList);
        sortPanel.add(temp);
        JButton confirm = new JButton("Confirm");
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // call the sort method to implement sorting based on user's selection
                ShowAndSearchUI ui = new ShowAndSearchUI();
                ArrayList filteredList = filter(ui.fullInventoryData);
                sort(selectedSort[0], filteredList);
            }
        });
        sortPanel.add(confirm);
        sortPanel.setBackground(Color.white);
        /* Need to get data from filter panel and sort the data using the selected sort method.
         *  */
        return sortPanel;
    }

    // filter the data based on selected filter choices
    private ArrayList filter(ArrayList data) {
        ArrayList<String[]> result = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < selectedList.size(); i++) {
            if (selectedList.get(count).length > 0) {
                break;
            } else {
                count++;
            }
        }

        addToResult(count, data, result);
        count++;
        for (int i = count; i < FILTER_CATEGROY_COUNT; i++) {
            if (selectedList.get(i).length > 0) {
                deleteFromResult(i, data, result);
            }
        }
        return result;
    }


    private void addToResult(int count, ArrayList<String[]> data, ArrayList<String[]> result) {
        for (int i = 0; i < selectedList.get(count).length; i++) {
            for (int j = 0; j < data.size(); j++) {
                String[] currentData = data.get(j);
                if (currentData[ORDER[count]].toLowerCase().equals(selectedList.get(count)[i].toLowerCase())) {
                    result.add(currentData);
                }
            }
        }
    }

    private void deleteFromResult(int count, ArrayList<String[]> data, ArrayList<String[]> result) {
        for (int i = 0; i < selectedList.get(count).length; i++) {
            for (int j = 0; j < data.size(); j++) {
                String[] currentData = data.get(i);
                if (!currentData[ORDER[count]].toLowerCase().equals(selectedList.get(count)[i].toLowerCase())) {
                    data.remove(j);
                }
            }
        }

    }

    // Parameter: User's selected sorting preference
    // Select and sort the vehicle objects and store it in a LinkedHashSet
    private void sort(int userSelectedSort, ArrayList filteredList) {


    }

    private JPanel getVehicleDisplayPanel() {

        vehicleDisplayPanel=new JPanel(new GridLayout(5,5));
        vehicleDisplayPanel.setBackground(Color.yellow);
        vehicleDisplayPanel.add(new Label("vehical should be displayed here"));
        //code for UI for VehicalPanel;
        return vehicleDisplayPanel;
    }



    public static void main(String[] args) {
        SearchSort searchSortObj=new SearchSort();
        System.out.println("AppUI main starting...");
    }


}
