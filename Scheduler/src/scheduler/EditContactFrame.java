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

public class EditContactFrame extends JFrame {
	/*
	 * Constructor
	 * Allows interaction between the program and the user.
	 */
	boolean test;
	ArrayList<Contact> addressBook;
	int index;
	private JButton _saveButton, _closeButton, _editButton, _deleteButton;
	private JLabel _firstNameLbl, _lastNameLbl, _phoneLbl, _emailLbl, _address1Lbl, _address2Lbl, _cityLbl, _stateLbl, _zipLbl;
	private JTextField _firstNameTxt, _lastNameTxt, _phoneTxt, _emailTxt, _address1Txt, _address2Txt, _cityTxt, _stateTxt, _zipTxt;
	
	public EditContactFrame(ArrayList<Contact> arrayList, int ind) {
		/*
		 * Sets up GUI text fields, labels, and buttons.
		 */
		super("New Entry");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addressBook = arrayList;
		
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
				//saveEntry();
			}
			
		});
		
		_editButton = new JButton("Edit");
		buttonPane.add(_editButton);
		_editButton.setEnabled(true);
		_editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//editEntry();
			}
		});
		
		_deleteButton = new JButton("Delete");
		buttonPane.add(_deleteButton);
		_deleteButton.setEnabled(true);
		_deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//deleteEntry();
			}
		});
		
		setEditable(false);
		setSize(500,300);
	}
	
	public void closeWindow() {
		/*
		 * Method closeWindow() prompts the user whether or not they want to
		 * add changes before closing. 
		 * yes - method calls addEntry()
		 * no - method closes window. 
		 */
		if (test == true) {
			int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to save changes before closing?");
			if (dialogResult == JOptionPane.YES_OPTION) {
				//saveEntry();
			}
			if (dialogResult == JOptionPane.NO_OPTION) {
				System.exit(0);
			}
		}
		else {
			System.exit(0);
		}
	}
	
	public void saveEntry() {
		/*
		 * Method addEntry() takes user inputed data from GUI and saves it 
		 * to an array list. 
		 */
		Contact ne = addressBook.get(index);
		if ((_firstNameTxt.getText() == "") && (_lastNameTxt.getText()== "")) {
			JOptionPane.showMessageDialog(null, "Please enter a first or last name.");
			return;
		}
		ne.setFirstName(_firstNameTxt.getText());
		ne.setLastName(_lastNameTxt.getText());
		
		if ((_phoneTxt.getText() == "") && (_emailTxt.getText() == "") && (_address1Txt.getText() == "") && (_address2Txt.getText() == "") && (_cityTxt.getText() == "") && (_stateTxt.getText() == "") && (_zipTxt.getText() == "")) {
			JOptionPane.showMessageDialog(null, "Please enter at least one additional category");
			return;
		}
		ne.setPhone(_phoneTxt.getText());
		ne.setEmail(_emailTxt.getText());
		ne.setAddress1(_address1Txt.getText());
		ne.setAddress2(_address2Txt.getText());
		ne.setCity(_cityTxt.getText());
		ne.setState(_stateTxt.getText());
		ne.setZip(_zipTxt.getText());
		
		JOptionPane.showMessageDialog(null, "Contact saved");
	}
	
	
	public void editEntry() {
		/*
		 * Method takes Contact to edit and uses getters to set text boxes with data 
		 * from Contact. 
		 */		
		setEditable(true);
		Contact ne = addressBook.get(index);
		_firstNameTxt.setText(ne.getFirstName());
		_lastNameTxt.setText(ne.getLastName());
		_phoneTxt.setText(ne.getPhone());
		_emailTxt.setText(ne.getEmail());
		_address1Txt.setText(ne.getAddress1());
		_address2Txt.setText(ne.getAddress1());
		_cityTxt.setText(ne.getCity());
		_stateTxt.setText(ne.getState());
		_zipTxt.setText(ne.getZip());
	}
	
	public void deleteEntry() {
		Contact ne = addressBook.get(index);
		addressBook.remove(ne);
	}
	
	public void setEditable(boolean b) {
		/*
		 * Method that makes text fields editable.
		 */
		test = b;
		_firstNameTxt.setEditable(b);
		_lastNameTxt.setEditable(b);
		_phoneTxt.setEditable(b);
		_emailTxt.setEditable(b);
		_address1Txt.setEditable(b);
		_address2Txt.setEditable(b);
		_cityTxt.setEditable(b);
		_stateTxt.setEditable(b);
		_zipTxt.setEditable(b);
	}
}