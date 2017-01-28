package scheduler;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.awt.event.ActionEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame {

	private JFrame frame;
    private JTable table;
    private JMenuBar menuBar;
    JScrollPane scrollPane;
    
    public static String index;
    public static int txtBookNum = 1;
 // column title
	private String[] columnNames = {
			                "Last Name",
			                "Zip Code",
			                "index"};
	private AddressBook AB;


	public AddressBook getAB() {
		return AB;
	}

	public void setAB(AddressBook aB) {
		AB = aB;
		showContacts(AB.getBook());
	}
	
	public void refreshAB() {
		showContacts(AB.getBook());
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		menuBar = new JMenuBar();
		setupMenuBar();

	    frame.setJMenuBar(menuBar);
		frame.setBounds(200, 200, 455, 350);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		// set the title
		String fileName = "Address Book "+ txtBookNum;
		frame.setTitle(fileName);
		AB = new AddressBook();
		ArrayList<Contact> contacts = AB.getBook();
		scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 64, 385, 167);
		frame.getContentPane().add(scrollPane);
		String s[][] = {{"No Entry","",""}};
		table = new JTable(s, columnNames);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && !e.isConsumed()) {
				     e.consume();
				     //handle double click event.
				     int selectedRowIndex = table.getSelectedRow();
					int selectedColumnIndex = table.getSelectedColumn();
						String selectedObject = (String) table.getModel().getValueAt(selectedRowIndex, 0);
						index = selectedObject;
						//new secFrame().setVisible(true);
						JOptionPane.showMessageDialog(frame,
							    "you selected "+selectedObject);
				}
			}
		});
		// disable the ability to drag the columns
		table.getTableHeader().setReorderingAllowed(false);

		
		// how to hide a column
		//table.removeColumn(table.getColumnModel().getColumn(1));
		
		
		// make column sortable
		//TableRowSorter<TableModel> sorter 
		//= new TableRowSorter<TableModel>(table.getModel());
		//table.setRowSorter(sorter);		
		
		scrollPane.setViewportView(table);
		
		
		JButton btnAddNewContact = new JButton("Add new contact");
		btnAddNewContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// open the add window
				addEntry();
				
			}
		});
		btnAddNewContact.setBounds(295, 23, 149, 29);
		frame.getContentPane().add(btnAddNewContact);
		
		JButton btnViewSelectedContact = new JButton("View selected contact");
		btnViewSelectedContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = table.getSelectedRow();
				int selectedColumnIndex = table.getSelectedColumn();
				String selectedObject = (String) table.getModel().getValueAt(selectedRowIndex, 0);
				index = selectedObject;
				//new secFrame().setVisible(true);
				JOptionPane.showMessageDialog(frame,
					    "you selected "+selectedObject);
			}
		});
		btnViewSelectedContact.setBounds(295, 243, 161, 29);
		frame.getContentPane().add(btnViewSelectedContact);
		
		JLabel lblSortBy = new JLabel("Sort By:");
		lblSortBy.setBounds(6, 6, 61, 16);
		frame.getContentPane().add(lblSortBy);
		
		JRadioButton rdbtnName = new JRadioButton("Name");
		rdbtnName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Contact> c = AB.getBook();
				c.sort(COMPARE_BY_NAME);
				showContacts(c);
			}
		});
		rdbtnName.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		rdbtnName.setBounds(59, 2, 141, 23);
		frame.getContentPane().add(rdbtnName);
		
		JRadioButton rdbtnZipCode = new JRadioButton("Zip Code");
		rdbtnZipCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Contact> c = AB.getBook();
				c.sort(COMPARE_BY_ZIP);
				showContacts(c);
			}
		});
		rdbtnZipCode.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		rdbtnZipCode.setBounds(59, 29, 141, 23);
		frame.getContentPane().add(rdbtnZipCode);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnName);
		group.add(rdbtnZipCode);
	}
	
	protected void addEntry() {
		// TODO Auto-generated method stub
		new AddEntryFrame(this).setVisible(true);
	}

	public static Comparator<Contact> COMPARE_BY_NAME = new Comparator<Contact>() {
        public int compare(Contact one, Contact other) {
        	/*
		Returns an integer less than zero, zero, or greater than zero, 
		depending on the lexigraphical comparison of the two strings. First name is used to break ties.
        	 */
        	
        	String oneName = one.getLastName();
        	if (oneName.equals("")){
        		oneName = one.getFirstName();
        	}
        	String otherName = other.getLastName();
        	if (otherName.equals("")){
        		otherName = other.getFirstName();
        	}
            int dif = one.getLastName().compareTo(other.getLastName());
            if(dif == 0){
            	dif = one.getFirstName().compareTo(other.getFirstName());
            }
            return dif;
        }
        };
        
        public static Comparator<Contact> COMPARE_BY_ZIP = new Comparator<Contact>() {
            public int compare(Contact one, Contact other) {
            	/*
    		Returns an integer less than zero, zero, or greater than zero, depending on the lexigraphical comparison of the two strings. 
            	 */
                return one.getZip().compareTo(other.getZip());
            }
        };


	
	public void showContacts(ArrayList<Contact> contacts){
				// make the table scrollable
				
				
				// get all the contacts here
				Object[][] data = new Object[contacts.size()][3];
				int key = 0;
				for (Contact c : contacts){
					data[key][0] = c.getLastName();
					if (c.getLastName().equals("")){
						data[key][0] = c.getFirstName();
					}
					data[key][1] = c.getZip();
					//data[key][2] = key;
					key++;
				}
				
				// to disable cell edit
				DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {

				    @Override
				    public boolean isCellEditable(int row, int column) {
				       //all cells false
				       return false;
				    }
				};
				
				table.setModel(tableModel);
	}
	
	// setup the menu bar
	private void setupMenuBar() {
		// File Menu, F - Mnemonic
	    JMenu fileMenu = new JMenu("File");
	    menuBar.add(fileMenu);

	    // File->New, N - Mnemonic
	    JMenuItem newMenuItem = new JMenuItem("New", null);
	    newMenuItem.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		// new file
	    		txtBookNum++;
	    		new MainFrame().frame.setVisible(true);
	    		
	    	}
	    });
	    fileMenu.add(newMenuItem);
	    
	    // File->Open, N - Mnemonic
	    JMenuItem openMenuItem = new JMenuItem("Open", null);
	    openMenuItem.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		// open
	    		JFileChooser chooser = new JFileChooser();
	    		File workingDirectory = new File(System.getProperty("user.home"));

	    		chooser.setCurrentDirectory(workingDirectory);
	    		chooser.setDialogTitle("Open a file");
	    		//chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    		//chooser.setAcceptAllFileFilterUsed(false);
	    		FileNameExtensionFilter tsvfilter = new FileNameExtensionFilter(
	    			     "tsv files (*.tsv)", "tsv");
	    		chooser.setFileFilter(tsvfilter);

	    		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
//	    		  System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
//	    		  System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
	    			
	    			
	    			AB = new AddressBook(chooser.getSelectedFile().toString());
	    			
	    			showContacts(AB.getBook());
	    		} else {
	    		  System.out.println("No Selection ");
	    		}

	    	}
	    });
	    fileMenu.add(openMenuItem);
	    
	    // File->Save, N - Mnemonic
	    JMenuItem saveMenuItem = new JMenuItem("Save As", null);
	    saveMenuItem.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		// save
	    		// TODO have to figure out if the file is opened
	    		JFileChooser fileChooser = new JFileChooser();
	    		fileChooser.setDialogTitle("Specify a file to save");   
	    		 
	    		int userSelection = fileChooser.showSaveDialog(frame);
	    		 
	    		if (userSelection == JFileChooser.APPROVE_OPTION) {
	    		    File fileToSave = fileChooser.getSelectedFile();
	    		    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
	    		    AB.Save(fileToSave.getAbsolutePath().toString());
	    		}
	    	}
	    });
	    fileMenu.add(saveMenuItem);
	    
//	    // File->Save As, N - Mnemonic
//	    JMenuItem saveasMenuItem = new JMenuItem("Save As", null);
//	    saveMenuItem.addActionListener(new ActionListener() {
//	    	public void actionPerformed(ActionEvent e) {
//	    		// save as
//	    		JFileChooser fileChooserSaveAs = new JFileChooser();
//	    		fileChooserSaveAs.setDialogTitle("Specify a file to save");   
//	    		 
//	    		int userSelectio = fileChooserSaveAs.showSaveDialog(frame);
//	    		 
//	    		if (userSelectio == JFileChooser.APPROVE_OPTION) {
//	    		    File fileToSave = fileChooserSaveAs.getSelectedFile();
//	    		    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
//	    		}
//	    	}
//	    });
//	    fileMenu.add(saveasMenuItem);
//	    
//	    // File->Close, N - Mnemonic
//	    JMenuItem closeMenuItem = new JMenuItem("Close", null);
//	    saveMenuItem.addActionListener(new ActionListener() {
//	    	public void actionPerformed(ActionEvent e) {
//	    		frame.dispose();
//	    	}
//	    });
//	    fileMenu.add(closeMenuItem);
//	    
//	    // File->Close, N - Mnemonic
//	    JMenuItem quitMenuItem = new JMenuItem("Quit", null);
//	    saveMenuItem.addActionListener(new ActionListener() {
//	    	public void actionPerformed(ActionEvent e) {
//	    		// new file
//	    		System.exit(JFrame.EXIT_ON_CLOSE);
//	    	}
//	    });
//	    fileMenu.add(quitMenuItem);
	}
}



