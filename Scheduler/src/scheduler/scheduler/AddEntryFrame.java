package scheduler;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class AddEntryFrame extends JFrame {
	/*
	 * Constructor
	 * Allows interaction between the program and the user.
	 */
	AddressBook addressBook;
	MainFrame mainFrame;
	Contact tempContact;
	boolean entryAdded = false;
	private JButton _saveButton, _closeButton, _editButton, _deleteButton;
	private JLabel _firstNameLbl, _lastNameLbl, _phoneLbl, _emailLbl, _address1Lbl, _address2Lbl, _cityLbl, _stateLbl, _zipLbl;
	private JTextField _firstNameTxt, _lastNameTxt, _phoneTxt, _emailTxt, _address1Txt, _address2Txt, _cityTxt, _stateTxt, _zipTxt;
	
	public AddEntryFrame(MainFrame _mainFrame) {
		/*
		 * Sets up GUI text fields, labels, and buttons.
		 */
		super("New Entry");
		mainFrame = _mainFrame;
		addressBook = mainFrame.getAB();
		
		/*
		 * Prompts user before closing window after the red 'x' is pressed. 
		 */
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new java.awt.event.WindowAdapter() {	
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				boolean flag;
				Contact newContact = makeContactNow();
				flag = compareContacts(newContact, tempContact);
				newContact = tempContact;
				if (flag) {
					int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to save contact before closing?");
					if (dialogResult == JOptionPane.YES_OPTION) {
						addEntry();
					}
					if (dialogResult == JOptionPane.NO_OPTION) {
						dispose();
					}
				} else {
					dispose();
				}
			}
			
		});
		
		setLayout(new BorderLayout());

		JPanel textPane = new JPanel(new GridLayout(9, 2));
		add(textPane, BorderLayout.NORTH);
		
		_firstNameLbl = new JLabel("First Name:");
		_firstNameTxt = new JTextField("");
		textPane.add(_firstNameLbl);
		textPane.add(_firstNameTxt);
		
		_lastNameLbl  = new JLabel("Last Name:");
		_lastNameTxt  = new JTextField("");
		textPane.add(_lastNameLbl);
		textPane.add(_lastNameTxt);
		
		_phoneLbl = new JLabel("Phone:"); 
		_phoneTxt = new JTextField("");
		textPane.add(_phoneLbl);
		textPane.add(_phoneTxt);
		
		_emailLbl = new JLabel("Email:");
		_emailTxt = new JTextField("");
		textPane.add(_emailLbl);
		textPane.add(_emailTxt);
		
		_address1Lbl = new JLabel("Address 1:");
		_address1Txt = new JTextField("");
		textPane.add(_address1Lbl);
		textPane.add(_address1Txt);

		_address2Lbl = new JLabel("Address 2:");
		_address2Txt = new JTextField("");
		textPane.add(_address2Lbl);
		textPane.add(_address2Txt);		
		
		_cityLbl  = new JLabel("City:"); 
		_cityTxt  = new JTextField("");
		textPane.add(_cityLbl);
		textPane.add(_cityTxt);
		
		_stateLbl = new JLabel("State:");
		_stateTxt = new JTextField("");
		textPane.add(_stateLbl);
		textPane.add(_stateTxt);
		
		_zipLbl = new JLabel("ZIP:");
		_zipTxt = new JTextField("");
		textPane.add(_zipLbl);
		textPane.add(_zipTxt);
		
		JPanel buttonPane = new JPanel(new GridLayout(1, 4));
		add(buttonPane, BorderLayout.SOUTH);
		
		_closeButton = new JButton("Close");
		buttonPane.add(_closeButton);
		_closeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
			
		});
		
		_saveButton = new JButton("Save");
		buttonPane.add(_saveButton);
		_saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEntry();
			}
			
		});
		
		_editButton = new JButton("Edit");
		buttonPane.add(_editButton);
		_editButton.setEnabled(false);
		
		_deleteButton = new JButton("Delete");
		buttonPane.add(_deleteButton);
		_deleteButton.setEnabled(false);
		
		tempContact = makeContactNow();
		setSize(500,300);
	}
	
	public void closeWindow() {
		/*
		 * Method closeWindow() prompts the user whether or not they want to add changes before closing. 
		 * If user selects yes, method calls addEntry(). If user selects no, method closes window. 
		 */
		boolean flag;
		Contact newContact = makeContactNow();
		flag = compareContacts(newContact, tempContact);
		tempContact = newContact;
		
		if (flag) {
			int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to save contact before closing?");
			if (dialogResult == JOptionPane.YES_OPTION) {
				addEntry();
			}
			if (dialogResult == JOptionPane.NO_OPTION) {
				this.dispose();
			}
		}
		else{
			this.dispose();
			}
		
	}
	
	public void addEntry() {
		/*
		 * Method addEntry() takes user inputed data from GUI and saves it to an array list. 
		 */
		String fn, ln, phone, email, address1, address2, city, state, zip;
		fn = _firstNameTxt.getText();
		ln = _lastNameTxt.getText();
		if (ln.equals("") && fn.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter a first or last name.");
			return;
		}
		
		phone = _phoneTxt.getText();
		email = _emailTxt.getText();
		address1 = _address1Txt.getText();
		address2 = _address2Txt.getText();
		city = _cityTxt.getText();
		state = _stateTxt.getText();
		zip = _zipTxt.getText();
		if (phone.equals("") && email.equals("") && address1.equals("") && address2.equals("") && city.equals("") && state.equals("") && zip.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter at least one additional category");
			return;
		}
		
		/*
		 * Checks for valid zipcode, prompts user if zipcode is not in the right format. 
		 */
		if (!_zipTxt.getText().equals("") && ((_zipTxt.getText().length() != 5) || (!isNumber(_zipTxt.getText())))) {
		
			int dialogResult = JOptionPane.YES_NO_OPTION;
			dialogResult = JOptionPane.showConfirmDialog(null, "Your zip code is not in a valid format, save anyway?", "Warning", dialogResult);
			if (dialogResult == JOptionPane.YES_OPTION) {
			}
			if (dialogResult == JOptionPane.NO_OPTION) {
				return;
			}
		}
		
		Contact ne = new Contact(fn, ln, phone, email, address1, address2, city, state, zip);
		tempContact = makeContactNow();
		addressBook.Add(ne);
		mainFrame.refreshAB();
		JOptionPane.showMessageDialog(null, "Contact Saved");
		entryAdded = true;
		
	}
	
	boolean entriesAreEmpty(){
		/*
	 	 * Method entriesAreEmpty() checks if textboxes are empty and returns a boolean value:
		 *	true  - if all entries are empty.
		 *	false - if at least one entry is not empty. 
		 */
		if (	_firstNameTxt.getText().equals("")
				&& _lastNameTxt.getText().equals("")
				&& _phoneTxt.getText().equals("")
				&& _emailTxt.getText().equals("")
				&& _address1Txt.getText().equals("")
				&& _address2Txt.getText().equals("")
				&& _cityTxt.getText().equals("")
				&& _stateTxt.getText().equals("")
				&& _zipTxt.getText().equals("")
				)
			return true;
		return false;
	}
	
	
	public Contact makeContactNow() {
		/*
		 * Method makeContactNow() pulls whatever is currently in text boxes and stores it in a new Contact object.
		 * Returns:
		 * 		Contact object
		 */
		Contact cont;
		String fn, ln, phone, email, address1, address2, city, state, zip;
		fn = _firstNameTxt.getText();
		ln = _lastNameTxt.getText();
		phone = _phoneTxt.getText();
		email = _emailTxt.getText();
		address1 = _address1Txt.getText();
		address2 = _address2Txt.getText();
		city = _emailTxt.getText();
		state = _stateTxt.getText();
		zip = _zipTxt.getText();
		
		cont = new Contact(fn, ln, phone, email, address1, address2, city, state, zip);
		return cont;
	}
	
	public boolean compareContacts(Contact one, Contact two){
		/*
		 * Method compareContacts(Contact, Contact) compares contact from when save was pressed to contact
		 * when close is pressed. 
		 * Returns:
		 * 		true  - changes between two contacts
		 * 		false - no changes between two contacts 
		 */
		boolean flag = false;
	    	if (!one.getFirstName().equals(two.getFirstName())){
			flag = true; 
	        	return flag;
	   	 }
	   	if (!one.getLastName().equals(two.getLastName())){
	        	flag = true;
	       		return flag;
	    	}
	    	if (!one.getPhone().equals(two.getPhone())){
	        	flag = true;
	        	return flag;
	    	}
	    	if (!one.getEmail().equals(two.getEmail())){
	        	flag = true;
	        	return flag;
	    	}
	    	if (!one.getAddress1().equals(two.getAddress1())){
	        	flag = true;
	        	return flag;
	    	}
	    	if (!one.getAddress2().equals(two.getAddress2())){
	        	flag = true;
	        	return flag;
	    	}
	    	if (!one.getCity().equals(two.getCity())){
	        	flag = true;
	        	return flag;
	    	}
	    	if (!one.getState().equals(two.getState())){
	        	flag = true;
	        	return flag;
	    	}
	    	if (!one.getZip().equals(two.getZip())){
	        	flag = true;
	        	return flag;
	    	}
	    	return flag;
	}
	
	public boolean isNumber(String str) {
		/*
		 * Source: http://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
		 * Method isNumber(String) takes a string as an arguement and returns a boolean value:
		 * 	true  - if the string is a number.
		 *	false - if the string contains a character that is not a number. 
		 */ 
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
