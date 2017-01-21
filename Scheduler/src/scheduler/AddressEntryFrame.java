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

public class AddressEntryFrame extends JFrame {
	/*
	 * Constructor
	 * Allows interaction between the program and the user.
	 */
	ArrayList<NewEntry> newEntryList = new ArrayList<>();
	private JButton _addButton, _closeButton, _editButton, _deleteButton;
	private JLabel _firstNameLbl, _lastNameLbl, _phoneLbl, _emailLbl, _addressLbl, _cityLbl, _stateLbl, _zipLbl;
	private JTextField _firstNameTxt, _lastNameTxt, _phoneTxt, _emailTxt, _addressTxt, _cityTxt, _stateTxt, _zipTxt;
	
	public AddressEntryFrame() {
		/*
		 * Sets up GUI text fields, labels, and buttons.
		 */
		super("New Entry");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());

		JPanel textPane = new JPanel(new GridLayout(8, 2));
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
		
		_addressLbl = new JLabel("Address:");
		_addressTxt = new JTextField("");
		textPane.add(_addressLbl);
		textPane.add(_addressTxt);
		
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
		
		_addButton = new JButton("Add");
		buttonPane.add(_addButton);
		_addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEntry();
			}
			
		});
		
		_editButton = new JButton("Edit");
		buttonPane.add(_editButton);
		
		_deleteButton = new JButton("Delete");
		buttonPane.add(_deleteButton);
		
		setSize(500,275);
	}
	
	public void closeWindow() {
		/*
		 * Method closeWindow() prompts the user whether or not they want to
		 * add changes before closing. 
		 * yes - method calls addEntry()
		 * no - method closes window. 
		 */
		int dialogButton = JOptionPane.YES_NO_CANCEL_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to add contact before closing?");
		if (dialogResult == JOptionPane.YES_OPTION) {
			addEntry();
		}
		if (dialogResult == JOptionPane.NO_OPTION) {
			System.exit(0);
		}
	}
	
	public void addEntry() {
		/*
		 * Method addEntry() takes user inputed data from GUI and saves it 
		 * to an array list. 
		 */
		String fn, ln, phone, email, address, city, state, zip;
		fn = _firstNameTxt.getText();
		if (fn.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter First Name");
			return;
		}
		
		ln = _lastNameTxt.getText();
		if (ln.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter Last Name");
			return;
		}
		
		phone = _phoneTxt.getText();
		email = _emailTxt.getText();
		address = _addressTxt.getText();
		city = _cityTxt.getText();
		state = _stateTxt.getText();
		zip = _zipTxt.getText();
		if (zip.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter ZIP code");
			return;
		}
		
		NewEntry ne = new NewEntry(fn, ln, phone, email, address, city, state, zip);
		newEntryList.add(ne);
		JOptionPane.showMessageDialog(null, "Person Saved");
		/*	
		for (int i = 0; i < newEntryList.size(); i++) {
			System.out.println(newEntryList.get(i).getFirstName());
			System.out.println(newEntryList.get(i).getLastName());
			System.out.println(newEntryList.get(i).getPhone());
			System.out.println(newEntryList.get(i).getEmail());
			System.out.println(newEntryList.get(i).getAddress());
		}*/
	}
}