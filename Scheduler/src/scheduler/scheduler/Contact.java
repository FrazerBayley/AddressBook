package scheduler;

import java.awt.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Contact {
	/*
	 * Class NewEntry formats user inputed data from GUI into proper categories before storing in arrayList,
	 * it also provides getters.
	 */
	private String _firstName;
	private String _lastName;
	private String _phone;
	private String _email;
	private String _address1;
	private String _address2;
	private String _city;
	private String _state;
	private String _zip;
	
	// aziz: added a _address1 and _address2 insted of _address
	public Contact(String fn, String ln, String phone, String email, String add1, String add2, String city, String state, String zip) {
		_firstName = fn;
		_lastName = ln;
		_phone = phone;
		_email = email;
		_address1 = add1;
		_address2 = add2;
		_city = city;
		_state = state;
		_zip = zip;
	}
	
	// aziz: added a _address1 and _address2 insted of _address
	public Contact(ArrayList<String> list){
		_city = list.get(0);
		_state = list.get(1);
		_zip = list.get(2);
		_email = list.get(3);
		_address1 = list.get(4);
		_address2 = list.get(5);		
		_lastName = list.get(6);
		_firstName = list.get(7);
		_phone = list.get(8);
	}

    public static Comparator<Contact> COMPARE_BY_NAME = new Comparator<Contact>() {
        public int compare(Contact one, Contact other) {
        	/*
        	Returns an integer less than zero, zero, or greater than zero, depending on the lexigraphical comparison of the two strings. First name is used to break ties.
        	*/
        	if ((isEmpty(one.getLastName()) && isEmpty(other.getLastName())) || (one.getLastName().equals(other.getLastName()))){
        		if (isEmpty(one.getFirstName()) && isEmpty(other.getFirstName())){
        			return 0; //both names are completely empty
        		}
        		if (isNotEmpty(one.getFirstName()) && isNotEmpty(other.getFirstName())){
        			return one.getFirstName().compareTo(other.getFirstName());
        		}
        		if (isNotEmpty(one.getFirstName())){
        			return -1; //one is greater
        		}
        		else {
        			return 1; //other is greater
        		}
        	}
        	if (isNotEmpty(one.getLastName()) && isNotEmpty(other.getLastName())){
        		return one.getLastName().compareTo(other.getLastName());
        	}
        	if (isNotEmpty(one.getLastName())){
        		return -1;
        	}
        	else {
        		return 1;
        	}
        }
    };
    
    private static boolean isEmpty(String str) {
    	/*
    	Input: String
		Output: bool
		*/
        return str == null || str.isEmpty(); // is empty
    }
    
    private static boolean isNotEmpty(String str) {
       	/*
    	Input: String
		Output: bool
		*/
        return !isEmpty(str); // is not empty
    }

    public static Comparator<Contact> COMPARE_BY_ZIP = new Comparator<Contact>() {
        public int compare(Contact one, Contact other) {
        	/*
        	Returns an integer less than zero, zero, or greater than zero, depending on the lexigraphical comparison of the two strings. 
        	*/
        	if (isEmpty(one.getZip())){
        		if(isEmpty(other.getZip())){
        			return 0;
        		}
        		else{
        			return 1;
        		}
        	}
        	if (isEmpty(other.getZip())){
        		if (isEmpty(one.getZip())){
        			return 0;
        		}
        		else {
        			return -1;
        		}
        	}
            return one.getZip().compareTo(other.getZip());
        }
    };
	
	
	public String getFirstName() {
		return _firstName;
	}
	public void setFirstName(String value) {
		_firstName = value;
	}
	
	public String getLastName() {
		return _lastName;
	}
	public void setLastName(String value) {
		_lastName = value;
	}

	public String getPhone() {
		return _phone;
	}
	public void setPhone(String value) {
		_phone = value;
	}
	
	public String getEmail() {
		return _email;
	}
	public void setEmail(String value) {
		_email = value;
	}
	// aziz: added a _address1 and _address2 insted of _address
	public String getAddress1() {
		return _address1;
	}
	public void setAddress1(String value) {
		_address1 = value;
	}
	
	public String getAddress2() {
		return _address2;
	}
	public void setAddress2(String value) {
		_address2 = value;
	}
	
	public String getCity() {
		return _city;
	}
	public void setCity(String value) {
		_city = value;
	}
	
	public String getState() {
		return _state;
	}
	public void setState(String value) {
		_state = value;
	}
	
	public String getZip() {
		return _zip;
	}
	public void setZip(String value) {
		_zip = value;
	}
}
