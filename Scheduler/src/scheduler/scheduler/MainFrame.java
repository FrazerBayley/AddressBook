package scheduler;
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
import java.awt.event.FocusEvent;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import org.apache.commons.io.FilenameUtils;

public class MainFrame {

	JFrame frame;
    private JTable table;
    private JMenuBar menuBar;
    private JScrollPane scrollPane;
    public File file;
    public String title;
    public static int txtBookNum = 1;
    private ArrayList<Contact> contactsList;
    // column title
	private String[] columnNames = {
			                "Last Name",
			                "Zip Code",
			                "index"};
	private AddressBook addressBook;
	private JTextField textField;


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
		file =null;
		// set the title
		title = "Address Book "+ txtBookNum;
		frame.setTitle(title);
		setupTable();
		addressBook = new AddressBook();
		
		JButton btnAddNewContact = new JButton("Add new contact");
		btnAddNewContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// open the add window
				addEntry();
			}
		});
		btnAddNewContact.setBounds(300, 240, 149, 29);
		frame.getContentPane().add(btnAddNewContact);
		
		JLabel lblSortBy = new JLabel("Sort By:");
		lblSortBy.setBounds(6, 254, 61, 16);
		frame.getContentPane().add(lblSortBy);
		
		JRadioButton rdbtnName = new JRadioButton("Name");
		rdbtnName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contactsList.sort(Contact.COMPARE_BY_NAME);
				showContacts();
				
			}
		});
		rdbtnName.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		rdbtnName.setBounds(59, 250, 141, 23);
		frame.getContentPane().add(rdbtnName);
		
		JRadioButton rdbtnZipCode = new JRadioButton("Zip Code");
		rdbtnZipCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contactsList.sort(Contact.COMPARE_BY_ZIP);
				showContacts();
			}
		});
		rdbtnZipCode.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		rdbtnZipCode.setBounds(59, 277, 141, 23);
		frame.getContentPane().add(rdbtnZipCode);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnName);
		group.add(rdbtnZipCode);
		
		JLabel lblFind = new JLabel("Find:");
		lblFind.setBounds(31, 6, 36, 16);
		frame.getContentPane().add(lblFind);
		
		textField = new JTextField();
		textField.setBounds(65, 1, 158, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.addFocusListener(new java.awt.event.FocusListener() {
			@Override
		    public void focusLost(java.awt.event.FocusEvent focusEvent) {
		            if (textField.getText().equals("")) {
		            	contactsList = addressBook.getBook();
		            	showContacts();
		            }
		    }
			@Override
			public void focusGained(FocusEvent e) {}
		}
		);
		
		JButton btnFind = new JButton("Search");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		btnFind.setBounds(216, 1, 117, 29);
		frame.getContentPane().add(btnFind);
		
		JLabel lblNoteDoubleClick = new JLabel("note: double click to veiw a contact");
		lblNoteDoubleClick.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblNoteDoubleClick.setBounds(6, 36, 186, 16);
		frame.getContentPane().add(lblNoteDoubleClick);
	}
	
	protected void search() {
		// TODO Auto-generated method stub
		String text = textField.getText().trim();
		if (text.equals("")){
			contactsList = addressBook.getBook();
        	showContacts();
		}
		else{
			contactsList = addressBook.Search(text);
        	showContacts();
		}
	}

	private void setupTable() {
		// TODO Auto-generated method stub
		scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 64, 385, 167);
		frame.getContentPane().add(scrollPane);
		//String s[][] = {{"No Entry","",""}};
		table = new JTable(null, columnNames);
		populateTable(null);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && !e.isConsumed()) {
				     e.consume();
				     //handle double click event.
				     veiwItem();
				}
			}
		});
		// disable the ability to drag the columns
		table.getTableHeader().setReorderingAllowed(false);
		
		// how to hide a column
		//table.removeColumn(table.getColumnModel().getColumn(1));		
		
		scrollPane.setViewportView(table);
	}

	protected void veiwItem() {
		// TODO Auto-generated method stub
		int selectedRowIndex = table.getSelectedRow();
		//int selectedColumnIndex = table.getSelectedColumn();
		String selectedObject = (String) table.getModel().getValueAt(selectedRowIndex, 0);
			//index = selectedObject;
			//new secFrame().setVisible(true);
//		JOptionPane.showMessageDialog(frame,
//			    "you selected "+selectedObject);
//		System.out.println(AB.getBook().get(selectedRowIndex).toString());
		new EditContactFrame(this, contactsList.get(selectedRowIndex)).setVisible(true);
	}

	protected void addEntry() {
		// TODO Auto-generated method stub
		new AddEntryFrame(this).setVisible(true);
	}


	
	public void showContacts(){
				// make the table scrollable
				
				
				// get all the contacts here
				
				Object[][] data = new Object[contactsList.size()][3];
				int key = 0;
				for (Contact c : contactsList){
					data[key][0] = c.getLastName();
					if (c.getLastName().equals("")){
						data[key][0] = c.getFirstName();
					}
					data[key][1] = c.getZip();
					//data[key][2] = key;
					key++;
				}
				populateTable(data);
				
			
	}
	
	private void populateTable(Object[][] data) {
		// TODO Auto-generated method stub
		
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
	    		JFileChooser openChooser = new JFileChooser();
	    		File workingDirectory = new File(System.getProperty("user.home"));
	    		

	    		openChooser.setCurrentDirectory(workingDirectory);
	    		openChooser.setDialogTitle("Open a file");
	    		//chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    		//chooser.setAcceptAllFileFilterUsed(false);
	    		FileNameExtensionFilter tsvfilter = new FileNameExtensionFilter(
	    			     "tsv files (*.tsv)", "tsv");
	    		openChooser.setFileFilter(tsvfilter);

	    		if (openChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {	    			
	    			addressBook = new AddressBook(openChooser.getSelectedFile().toString());
	    			contactsList = addressBook.getBook();
	    			showContacts();
	    			setFile(openChooser.getSelectedFile());
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
	    		// save
	    		if (file == null){
	    			saveAs();
	    		}
	    		else{
	    			addressBook.Save(file.getAbsolutePath().toString());
	    		}

	    	}
	    });
	    fileMenu.add(saveMenuItem);
	    
	    // File->Save As, N - Mnemonic
	    JMenuItem saveasMenuItem = new JMenuItem("Save As", null);
	    saveasMenuItem.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		// save as
	    		saveAs();
	    	}
	    });
	    fileMenu.add(saveasMenuItem);
	    
	    // File->Close, N - Mnemonic
	    JMenuItem closeMenuItem = new JMenuItem("Close", null);
	    closeMenuItem.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		frame.dispose();
	    	}
	    });
	    fileMenu.add(closeMenuItem);
	    
	    // File->Close, N - Mnemonic
	    JMenuItem quitMenuItem = new JMenuItem("Quit", null);
	    quitMenuItem.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		// new file
	    		System.exit(JFrame.EXIT_ON_CLOSE);
	    	}
	    });
	    fileMenu.add(quitMenuItem);
	    
	}
	
	private void saveAs() {
		// TODO Auto-generated method stub
		// source: http://stackoverflow.com/questions/17010647/set-default-saving-extention-with-jfilechooser
		JFileChooser saveAsChooser = new JFileChooser();
		saveAsChooser.setSelectedFile(new File(title+".tsv"));
		FileNameExtensionFilter tsvfilter = new FileNameExtensionFilter(
			     "tsv files (*.tsv)", "tsv");
		saveAsChooser.setFileFilter(tsvfilter);
		saveAsChooser.setDialogTitle("Specify a file to save");   
		 
		int userSelection = saveAsChooser.showSaveDialog(frame);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = saveAsChooser.getSelectedFile();
		    if (FilenameUtils.getExtension(fileToSave.getName()).equalsIgnoreCase("tsv")) {
		        // filename is OK as-is
		    } else {
		    	fileToSave = new File(fileToSave.toString() + ".tsv");
		    	fileToSave = new File(fileToSave.getParentFile(), FilenameUtils.getBaseName(fileToSave.getName())+".tsv");
		    }
		    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
		    addressBook.Save(fileToSave.getAbsolutePath().toString());
		    setFile(fileToSave);
		    //File w = new File(fileToSave.getAbsolutePath().toString());
		    //System.out.println("file: " + FilenameUtils.getBaseName(w.getName()));
		}
	}


	protected void setFile(File f) {
		// TODO Auto-generated method stub
		file = f;
		title = FilenameUtils.getBaseName(file.getName());
		frame.setTitle(title);
	}

	public AddressBook getAB() {
		return addressBook;
	}

	public void setAB(AddressBook aB) {
		addressBook = aB;
	}
	
	public void refreshAB() {
		// Search the contactsList again
		search();
	}
	
	
	
}




