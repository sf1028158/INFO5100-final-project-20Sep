package ui;


import ui.AutomobileDealerInventoryUI02;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;

public class ShowAndSearchUI extends JFrame {

    AutomobileDealerInventoryUI02 inventory = new AutomobileDealerInventoryUI02();
    private JPanel sortPanel;
    private HashSet<String> selected;
    private JScrollPane jScrollPane2;
    private JLabel pageHeading;
    private JTable vehicleDisplay;
    private JPanel filterPanel;
    private JPanel mainDisplay;
    private final int[] selectedSort = {0};
    String[] dealerInventoryData;
    ArrayList<String[]> fullInventoryData;
    String dealerName= "gmps-aj-dohmann";
    private static final String PATH ="././data/";
    private JButton viewMore_button;



    public ShowAndSearchUI() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        mainDisplay = new JPanel();
        pageHeading = new JLabel();
        filterPanel = new JPanel();
        jScrollPane2 = new JScrollPane();
        vehicleDisplay = new JTable();
        sortPanel = new JPanel();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainDisplay.setBackground(new Color(153, 153, 153));
        pageHeading.setFont(new Font("Tahoma", 0, 48)); // NOI18N
        pageHeading.setText("INVENTORY");
        filterPanel.setBackground(new Color(204, 204, 204));
        GroupLayout filterPanelLayout = new GroupLayout(getFilterPanel());
        filterPanel.setLayout(filterPanelLayout);
        filterPanelLayout.setHorizontalGroup(
                filterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        filterPanelLayout.setVerticalGroup(
                filterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 128, Short.MAX_VALUE)
        );


        jScrollPane2.setViewportView(getTable());

        sortPanel.setBackground(new java.awt.Color(204, 204, 204));
        GroupLayout sortPanelLayout = new GroupLayout(getSortPanel());
        sortPanel.setLayout(sortPanelLayout);
        sortPanelLayout.setHorizontalGroup(
                sortPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        sortPanelLayout.setVerticalGroup(
                sortPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 117, Short.MAX_VALUE)
        );

       GroupLayout mainDisplayLayout = new GroupLayout(getMainDisplayPanel());
        mainDisplay.setLayout(mainDisplayLayout);
        mainDisplayLayout.setHorizontalGroup(
                mainDisplayLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, mainDisplayLayout.createSequentialGroup()
                                .addContainerGap(309, Short.MAX_VALUE)
                                .addComponent(pageHeading, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
                                .addGap(288, 288, 288))
                        .addComponent(jScrollPane2, GroupLayout.Alignment.TRAILING)
                        .addComponent(filterPanel,GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sortPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainDisplayLayout.setVerticalGroup(
                mainDisplayLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainDisplayLayout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(pageHeading)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(filterPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sortPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 561, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainDisplay, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(mainDisplay,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>



    JPanel getMainDisplayPanel() {

        // init panel
        mainDisplay = new JPanel(new GridLayout(3,1));
        // add Component to mailDisplayPanel;
        mainDisplay.add(getFilterPanel());
        mainDisplay.add(getSortPanel());
        mainDisplay.add(getTable());
        return mainDisplay;
    }



    private JPanel getFilterPanel() {
        //code for UI for filterPanel  ie filters for searching
        selected = new HashSet<String>();
        filterPanel = new JPanel();

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
                sort(selectedSort[0]);
            }
        });
        sortPanel.add(confirm);
        sortPanel.setBackground(Color.white);
        /* Need to get data from filter panel and sort the data using the selected sort method.
         *  */
        return sortPanel;
    }

    // Parameter: User's selected sorting preference
    // Select and sort the vehicle objects and store it in a LinkedHashSet
    private void sort(int userSelectedSort) {
        System.out.println(userSelectedSort);
        // TODO

    }


    private JTable getTable() {
        vehicleDisplay = new JTable();
        vehicleDisplay.setRowHeight(200);
        DefaultTableModel model=(DefaultTableModel) vehicleDisplay.getModel();
        Object[] columns=new Object[]{"Model","Type","Year","Price","View More","Image"};
        model.setColumnIdentifiers(columns);
        readDealerInventory(dealerName); //Reading Inventory data of dealer
        return vehicleDisplay;
    }

    private void getVehicals(String[] dealerInventoryData) throws MalformedURLException {
        int maxRowCountPerPage=50;
        String model=dealerInventoryData[5];
        String make=dealerInventoryData[4];
        String type=dealerInventoryData[7];
        String year=dealerInventoryData[3];
        String price="$"+dealerInventoryData[8];
        String vehicleImagePath=dealerInventoryData[dealerInventoryData.length-1];
        URL url = null;
        Image image = null;
        ImageIcon imageIcon;

        vehicleDisplay.getColumn("Image").setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                TableColumn tbImage = vehicleDisplay.getColumn("Image");
                tbImage.setMaxWidth(100);
                tbImage.setMinWidth(100);
                return (Component) value;
            }
        });

        vehicleDisplay.getColumn("View More").setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                TableColumn tbButton=vehicleDisplay.getColumn("View More");
                tbButton.setMaxWidth(100);
                tbButton.setMaxWidth(100);
                return (Component) value;
            }
        });

        try {
            url =new URL(vehicleImagePath);
            image = ImageIO.read(url);
            imageIcon= new ImageIcon(image);
        } catch (MalformedURLException e) {
            //e.printStackTrace();
            vehicleImagePath =PATH +"404NotFound.png";
            imageIcon= new ImageIcon(vehicleImagePath);

        } catch (IOException e) {
            vehicleImagePath=PATH +"404NotFound.png";
            imageIcon= new ImageIcon(vehicleImagePath);

        }
        //System.out.println(vehicleImagePath);

        Image img = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel imageLabel=new JLabel();
        viewMore_button=new JButton("View More");
        imageLabel.setIcon(new ImageIcon(img));
        DefaultTableModel newModel=(DefaultTableModel) vehicleDisplay.getModel();
        newModel.addRow(new Object[]{model,type,year,price,viewMore_button,imageLabel});

    }


    private void readDealerInventory(String dealerName) {
        {
            String line = "";
            String splitBy = "~";

            try
            {
                BufferedReader br = new BufferedReader(new FileReader(PATH + dealerName));
                while ((line = br.readLine()) != null){

                    dealerInventoryData=line.split(splitBy);
                    getVehicals(dealerInventoryData);
                    fullInventoryData.add(dealerInventoryData);
//                                          System.out.println(Arrays.toString(dealerInventoryData));
//                        System.out.println("Dealers [Dealer ID =" + dealerInventoryData[0] + ", WebId=" + dealerInventoryData[1] + ", Category=" + dealerInventoryData[2] +
//                                            ", year=" + dealerInventoryData[3] + ", Make=" + dealerInventoryData[4] + ", Model= " + dealerInventoryData[5] + ", Trim= " + dealerInventoryData[6]
//                                            + ", Type= " + dealerInventoryData[7] +", Price= " + dealerInventoryData[8] +"]");
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ShowAndSearchUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShowAndSearchUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShowAndSearchUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShowAndSearchUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ShowAndSearchUI().setVisible(true);
            }
        });
    }


}

