package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class AutomobileDealerInventoryUI02 extends JFrame {

    // Variables
    private JScrollPane jScrollPane2;
    private JPanel mainDisplay;
    private JLabel pageHeading;
    private JTable vehicleDisplay;
    String dealerName= "gmps-aj-dohmann";
    String[] dealerInventoryData;
    /**
     * Creates new form Inventory
     */
    public AutomobileDealerInventoryUI02() {

        initComponents();
    }

    private void initComponents() {

        mainDisplay = new javax.swing.JPanel();
        pageHeading = new JLabel();
        jScrollPane2 = new JScrollPane();
        vehicleDisplay = new JTable();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainDisplay.setBackground(new Color(153, 153, 153));
        pageHeading.setFont(new Font("Tahoma", 0, 36)); // NOI18N
        pageHeading.setText("INVENTORY");
        jScrollPane2.setViewportView(getTable());
        GroupLayout mainDisplayLayout = new GroupLayout(mainDisplay);
        mainDisplay.setLayout(mainDisplayLayout);
        mainDisplayLayout.setHorizontalGroup(
                mainDisplayLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainDisplayLayout.createSequentialGroup()
                                .addContainerGap(352, Short.MAX_VALUE)
                                .addComponent(pageHeading)
                                .addGap(500, 500, 500))
        );
        mainDisplayLayout.setVerticalGroup(
                mainDisplayLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainDisplayLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(pageHeading)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 540, GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainDisplay, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainDisplay, GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }


    private JTable getTable() {
        vehicleDisplay = new JTable();
        vehicleDisplay.setRowHeight(600);
        DefaultTableModel model=(DefaultTableModel) vehicleDisplay.getModel();
        Object[] columns=new Object[]{"Model","Type","Year","Price","Image"};
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
                TableColumn tb = vehicleDisplay.getColumn("Image");
                tb.setMaxWidth(100);
                tb.setMinWidth(100);
                vehicleDisplay.setRowHeight(60);
                return (Component) value;
            }
        });
        try {
            url =new URL(vehicleImagePath);
            image = ImageIO.read(url);
            imageIcon= new ImageIcon(image);
        } catch (MalformedURLException e) {
            //e.printStackTrace();
            vehicleImagePath ="././data/404NotFound.png";
            imageIcon= new ImageIcon(vehicleImagePath);

        } catch (IOException e) {
            vehicleImagePath="././data/404NotFound.png";
            imageIcon= new ImageIcon(vehicleImagePath);

        }
        //System.out.println(vehicleImagePath);
        Image img = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel imageLabel=new JLabel();
        imageLabel.setIcon(new ImageIcon(img));
        DefaultTableModel newModel=(DefaultTableModel) vehicleDisplay.getModel();
        newModel.addRow(new Object[]{model,type,year,price,imageLabel});
    }


    private void readDealerInventory(String dealerName) {
        {
            String line = "";
            String splitBy = "~";
            try
            {
                BufferedReader br = new BufferedReader(new FileReader("././Data/"+dealerName));
                while ((line = br.readLine()) != null){

                    dealerInventoryData=line.split(splitBy);
                    getVehicals(dealerInventoryData);
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
            java.util.logging.Logger.getLogger(AutomobileDealerInventoryUI02.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AutomobileDealerInventoryUI02.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AutomobileDealerInventoryUI02.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AutomobileDealerInventoryUI02.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AutomobileDealerInventoryUI02().setVisible(true);

            }
        });
    }
}