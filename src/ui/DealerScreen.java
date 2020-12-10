/* Use Case I Screen */
package ui;
import incentive.*;
import dao.*;

import service.*;
//import ui.*; 
import validation.*;
import dto.*;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;



public class DealerScreen {
    private JFrame frame;
    private JPanel panelLeft, panelRight;
    private JTextField textFieldDealerName;
    private JTextField textFieldZipCode;
    private JComboBox<String> comboBox;
    private JButton btnSearch;
        
    private JTable table;
    int signal = 0;
    
    //private ArrayList<Dealer> dealerList=new ArrayList<>();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DealerScreen window = new DealerScreen();
                    window.frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private DealerScreen() {
        frameAndPanel();
        dealerName();
        zipCode();
        searchBtn();
    }

    // complete frame
    private void frameAndPanel() {
        frame = new JFrame();
        frame.setTitle("Dealer Locator Website");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,650);
        frame.getContentPane().setLayout(new BorderLayout());
        initializeLeftPanel();
        initializeRightPanel();
    }
    
    // left panel containing the form
    private void initializeLeftPanel() {
        panelLeft= new JPanel();
        panelLeft.setBackground(new Color(165, 194, 147));
        panelLeft.setLayout(null);
        panelLeft.setPreferredSize(new Dimension(280,650));
        frame.getContentPane().add(panelLeft,BorderLayout.WEST);
    }

    // right panel containing the table
    private void initializeRightPanel() {
        panelRight= new JPanel();
        panelRight.setPreferredSize(new Dimension(720,650));
        panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
        frame.getContentPane().add(panelRight,BorderLayout.CENTER);
    }

    // initializing dealer's name
    private void dealerName() {
        JLabel lblName = new JLabel("Search by Dealers Name: ");
        lblName.setBounds(10, 50, 200, 14);
        lblName.setForeground(Color.WHITE);
        panelLeft.add(lblName);

        textFieldDealerName = new JTextField();
        textFieldDealerName.setBounds(10, 90, 200, 20);
        textFieldDealerName.setColumns(10);
        panelLeft.add(textFieldDealerName);
        highlightDealerName();
        dealerNameValidation();
    }

    // validating dealer's name
    private void dealerNameValidation(){
        textFieldDealerName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                String dealerName = textFieldDealerName.getText();
                // !dealerName.isEmpty()&&!Validator.isValidDealerName(dealerName)
                if(!dealerName.isEmpty()&&!Validator.isValidDealerName(dealerName)) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please try again.");
                }
            }

        });
    }

    // highlighting if dealer's name textfield is empty
    private void highlightDealerName(){
        Border defaultBorder = textFieldDealerName.getBorder();
        textFieldDealerName.setBorder(BorderFactory.createLineBorder(new Color(0, 113, 238), 3));
        textFieldDealerName.getDocument().addDocumentListener(new DocumentListener() {
        // Interface DocumentListener
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                maybeHighlight();
            }
            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                maybeHighlight();
            }
            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                maybeHighlight();
            }

            private void maybeHighlight() {
                if (textFieldDealerName.getText().trim().length() != 0) {
                    textFieldDealerName.setBorder(defaultBorder);
                } else {
                    textFieldDealerName.setBorder(BorderFactory.createLineBorder(new Color(0, 113, 238), 3));
                }
            }
        });
    }

    // handling zip code
    private void zipCode() {
        JLabel lblPhone = new JLabel("Search by Zipcode: ");
        lblPhone.setBounds(10, 190, 270, 14);
        lblPhone.setForeground(Color.WHITE);
        panelLeft.add(lblPhone);
        textFieldZipCode = new JTextField();
        textFieldZipCode.setBounds(10, 230, 200, 20);
        textFieldZipCode.setColumns(10);
        panelLeft.add(textFieldZipCode);
        
        highlightZipCode();
        validateZipCode();
    }

    // highlighting zipcode textfield if it is empty
    private void highlightZipCode(){
        Border defaultBorder2 = textFieldZipCode.getBorder();
        textFieldZipCode.setBorder(BorderFactory.createLineBorder(new Color(0, 113, 238), 3));
        textFieldZipCode.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                maybeHighlight();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                maybeHighlight();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                maybeHighlight();
            }

            private void maybeHighlight() {
                if (textFieldZipCode.getText().trim().length() != 0)
                {
                    textFieldZipCode.setBorder(defaultBorder2);
                }
                else
                {
                    textFieldZipCode.setBorder(BorderFactory.createLineBorder(new Color(0, 113, 238), 3));
                }
            }
        });
    }

    // validation for zip code'e length
    private void validateZipCode(){
        textFieldZipCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                String zipCode=textFieldZipCode.getText();
                try {
                //  && !Validator.isValidZipCodeRange(zipCode)
	                if(!textFieldZipCode.getText().isEmpty()&& !Validator.isValidZipCodeRange(zipCode)) {
	                	// if zip code is invalid
	                    JOptionPane.showMessageDialog(frame, "This is a invalid Zip Code, Please enter again \nHint: Zip Code should be 5-digit or 9-digit (ZIP+4)."); 
	                }
                }
                catch(Exception e) {
                	e.printStackTrace();
                }
            }
        });
    }
    
    private void searchBtn(){
    	//int signal = 0;   	
    	ArrayList<Dealer> searchList = null;
    	SearchDealer s1 = new SearchDealer();
    	JLabel l1 = new JLabel("");
    	JRadioButton c1=new JRadioButton("Zipcode");
    	JRadioButton c2=new JRadioButton("Dealer Name");
    	JButton c3=new JButton("Search");
    	l1.setBounds(10, 270, 150, 14);
    	c1.setBounds(190, 270, 80, 14);
    	c1.setBounds(290, 270, 80, 14);
    	c3.setBounds(10,300,100,14);
    	ButtonGroup group = new ButtonGroup();  
    	group.add(c1);
    	group.add(c2);  
    	panelLeft.add(l1);
    	panelLeft.add(c1);
    	panelLeft.add(c2);	
    	panelLeft.add(c3);
		c1.addActionListener((ActionEvent ae) ->{signal=0;});
		
		c1.addActionListener((ActionEvent ae) ->{signal=1;});
		c3.addActionListener((ActionEvent ae) ->{
	
			if(signal==0){
				ArrayList<Dealer> searchList1=(ArrayList<Dealer>) s1.searchByZipCode(textFieldZipCode.getText());
				try {
					createTable(searchList1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setJTableColumnsWidth(table, 200);
				}
			if(signal==1){
				
				ArrayList<Dealer> searchList2=(ArrayList<Dealer>)s1.searchByName(textFieldDealerName.getText());
			 try {
				createTable(searchList2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setJTableColumnsWidth(table, 200);
				}	
		});	
		
    }
    
    private void createTable(ArrayList<Dealer> dealerList1) throws Exception { 	
    	
    	 String[] columnNames = { "ID", "Name","ZipCode","Distance","City","State","Address"};
    	 String[][] obj=null;
    	 table=new JTable();		 
    	 int n= dealerList1.size();    	 
    	 for(int i=0;i<n;i++){
    		 obj[i][0]=dealerList1.get(i).getDealerId();
    		 obj[i][1]=dealerList1.get(i).getDealerName();
    		 obj[i][2]=dealerList1.get(i).getDealerAddress().getZipCode();
    		 obj[i][3]=dealerList1.get(i).getDistanceInMiles()+"";
    		 obj[i][4]=dealerList1.get(i).getDealerAddress().getCity();
    		 obj[i][5]=dealerList1.get(i).getDealerAddress().getState();
    		 obj[i][6]=dealerList1.get(i).getDealerAddress().getAddressInfo();
    	 }
    	 TableModel tm = new DefaultTableModel(obj, columnNames); 	 
    	 this.table.setModel(tm);
    	
          panelRight.add(new JScrollPane(table)); 
    	 try {
             if(dealerList1.isEmpty()) {
                 JOptionPane.showMessageDialog(frame, "There is no dealer satisfied your requirement."); 
             }
         }
         catch(Exception e) {
         	e.printStackTrace();
         }
    	 
    }

    public static void setJTableColumnsWidth(JTable table, int tablePreferredWidth) {
    	
    	table.setRowHeight(30);
    	int columncount = table.getColumnCount();

        for (int i = 1; i < columncount; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(tablePreferredWidth);

        }
    } 
    
   private void SelectAndView() {
	    int count=table.getSelectedRow();
	    int getID= Integer.parseInt((String) table.getValueAt(count, 0));
	    JButton jbtn= new JButton("View Inventory");
	    panelRight.add(jbtn);
	    jbtn.setBounds(10, 630, 80, 14);
	    jbtn.addActionListener((ActionEvent ae) ->{
	    	
	    	System.out.println("Dealer"+"getID"+"is selected, and view inventory.");
	    	
	    });
   }       
}