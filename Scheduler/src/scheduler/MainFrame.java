import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainFrame {

	private JFrame frame;
    private JTable table;
    private JMenuBar menuBar;
    public static String index;
    public static int txtBookNum = 1;


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
		
		// make the table scrollable
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 64, 385, 167);
		frame.getContentPane().add(scrollPane);
		
		
		// column title
		String[] columnNames = {
				                "Last Name",
				                "Zip Code",
				                "index"};
		
		// get all the contacts here
		Object[][] data = {
			    {"aziz", "97401", new Integer(123)},
			    {"John", "97405", new Integer(125)}
			};
		
		// to disable cell edit
		DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		table = new JTable();
		table.setModel(tableModel);
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
				//new secFrame().setVisible(true);
			}
		});
		btnAddNewContact.setBounds(295, 23, 149, 29);
		frame.getContentPane().add(btnAddNewContact);
		
		JButton btnViewSelectedContact = new JButton("View selected contact");
		btnViewSelectedContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = table.getSelectedRow();
				int selectedColumnIndex = table.getSelectedColumn();
				String selectedObject = (String) table.getModel().getValueAt(selectedRowIndex, 1);
				index = selectedObject;
				//new secFrame().setVisible(true);
			}
		});
		btnViewSelectedContact.setBounds(295, 243, 161, 29);
		frame.getContentPane().add(btnViewSelectedContact);
		
		
		
		

	}
	
	
	// setup the menu bar
	private void setupMenuBar() {
		// TODO Auto-generated method stub
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
	    		chooser.setCurrentDirectory(new java.io.File("."));
	    		chooser.setDialogTitle("Open a file");
	    		//chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    		//chooser.setAcceptAllFileFilterUsed(false);
	    		FileNameExtensionFilter tsvfilter = new FileNameExtensionFilter(
	    			     "tsv files (*.tsv)", "tsv");
	    		chooser.setFileFilter(tsvfilter);

	    		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	    		  System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
	    		  System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
	    		} else {
	    		  System.out.println("No Selection ");
	    		}

	    	}
	    });
	    fileMenu.add(openMenuItem);
	    
	    // File->Save, N - Mnemonic
	    JMenuItem saveMenuItem = new JMenuItem("Save", null);
	    saveMenuItem.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		// new file
	    	}
	    });
	    fileMenu.add(saveMenuItem);
	    
	    // File->Save As, N - Mnemonic
	    JMenuItem saveasMenuItem = new JMenuItem("Save As", null);
	    saveMenuItem.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		// new file
	    	}
	    });
	    fileMenu.add(saveasMenuItem);
	    
	    // File->Close, N - Mnemonic
	    JMenuItem closeMenuItem = new JMenuItem("Close", null);
	    saveMenuItem.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		// new file
	    	}
	    });
	    fileMenu.add(closeMenuItem);
	    
	    // File->Close, N - Mnemonic
	    JMenuItem quitMenuItem = new JMenuItem("Quit", null);
	    saveMenuItem.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		// new file
	    	}
	    });
	    fileMenu.add(quitMenuItem);
	}
}
