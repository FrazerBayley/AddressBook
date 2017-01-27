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
	private JButton _saveButton, _closeButton, _editButton, _deleteButton;
	private JLabel _firstNameLbl, _lastNameLbl, _phoneLbl, _emailLbl, _address1Lbl, _address2Lbl, _cityLbl, _stateLbl, _zipLbl;
	private JTextField _firstNameTxt, _lastNameTxt, _phoneTxt, _emailTxt, _address1Txt, _address2Txt, _cityTxt, _stateTxt, _zipTxt;
	
	public AddEntryFrame() {
		/*
		 * Sets up GUI text fields, labels, and buttons.
		 */
		super("New Entry");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
				//addEntry();
			}
			
		});
		
		_editButton = new JButton("Edit");
		buttonPane.add(_editButton);
		_editButton.setEnabled(false);
		
		_deleteButton = new JButton("Delete");
		buttonPane.add(_deleteButton);
		_deleteButton.setEnabled(false);
		
		setSize(500,300);
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
			//addEntry();
		}
		if (dialogResult == JOptionPane.NO_OPTION) {
			System.exit(0);
		}
	}
	
	public void addEntry(ArrayList<Contact> addressBook) {
		/*
		 * Method addEntry() takes user inputed data from GUI and saves it 
		 * to an array list. 
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
		
		Contact ne = new Contact(fn, ln, phone, email, address1, address2, city, state, zip);
		addressBook.add(ne);
		JOptionPane.showMessageDialog(null, "Person Saved");
		
	}
}