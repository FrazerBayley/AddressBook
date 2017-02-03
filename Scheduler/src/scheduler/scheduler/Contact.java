package scheduler;

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
	
	public Contact(String fn, String ln, String phone, String email, String add1, String add2, String city, String state, String zip) {
		_firstName = fn;
		_lastName  = ln;
		_phone = phone;
		_email = email;
		_address1 = add1;
		_address2 = add2;
		_city  = city;
		_state = state;
		_zip   = zip;
	}
	
	
	public Contact(ArrayList<String> list){
		_city  = list.get(0);
		_state = list.get(1);
		_zip   = list.get(2);
		_email = list.get(3);
		_address1  = list.get(4);
		_address2  = list.get(5);		
		_lastName  = list.get(6);
		_firstName = list.get(7);
		_phone = list.get(8);
	}

    public static Comparator<Contact> COMPARE_BY_NAME = new Comparator<Contact>() {
        public int compare(Contact one, Contact other) {
        	/*
        	 * Returns an integer less than zero, zero, or greater than zero, depending on the lexigraphical comparison of the two strings. 
		 * First name is used to break ties.
        	 */
        	if ((isEmpty(one.getLastName()) && isEmpty(other.getLastName())) || (one.getLastName().equals(other.getLastName()))){
        		if (isEmpty(one.getFirstName()) && isEmpty(other.getFirstName())){
        			//Returns 0 if both names are completely empty
				return 0;
        		}
        		if (isNotEmpty(one.getFirstName()) && isNotEmpty(other.getFirstName())){
        			return one.getFirstName().compareTo(other.getFirstName());
        		}
        		if (isNotEmpty(one.getFirstName())){
        			//Returns -1 if one is greater
				return -1;
        		}
        		else {
        			//Returns 1 if other is greater
				return 1;
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
    	 * Method isEmpty(String) takes a string as an argument and returns a boolean value:
	 *	true  - if string is null or empty.
	 *	false - if string is not null or empty.  
	 */
        return str == null || str.isEmpty();
    }
    
    private static boolean isNotEmpty(String str) {
    	/*
    	 * Method isNotEmpty(String) takes a string as an argument and returns a boolean value:
	 *	true  - if string is not null or empty.
	 *	false - if string is null or empty.  
	 */
        return !isEmpty(str);
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
	
	@Override
	public String toString(){
		
		return "firstName: "+ _firstName
				+ "\nlastName: "+ _lastName
				+ "\nphone: "+ _firstName
				+ "\nemail: "+ _phone
				+ "\naddress1: "+ _address1
				+ "\naddress2: "+ _address2
				+ "\ncity: "+ _city
				+ "\nstate: "+ _state
				+ "\nzip: "+ _zip;
	}
	
	
	public String getFirstName() {
		/* 
		 * Method getFirstName() returns a string. 
		 */
		return _firstName;
	}
	public void setFirstName(String value) {
		/*
		 * Method setFirstName() accepts a string as a value.
		 */
		_firstName = value;
	}
	
	public String getLastName() {
		/* 
		 * Method getLastName() returns a string. 
		 */		
		return _lastName;
	}
	public void setLastName(String value) {
		/*
		 * Method setLastName() accepts a string as a value.
		 */
		_lastName = value;
	}

	public String getPhone() {
		/* 
		 * Method getPhone() returns a string. 
		 */		
		return _phone;
	}
	public void setPhone(String value) {
		/*
		 * Method setPhone() accepts a string as a value.
		 */
		_phone = value;
	}
	
	public String getEmail() {
		/* 
		 * Method getEmail() returns a string. 
		 */
		return _email;
	}
	public void setEmail(String value) {
		/*
		 * Method setEmail() accepts a string as a value.
		 */
		_email = value;
	}

	public String getAddress1() {
		/* 
		 * Method getAddress1() returns a string. 
		 */
		return _address1;
	}
	public void setAddress1(String value) {
		/*
		 * Method setAddress1() accepts a string as a value.
		 */
		_address1 = value;
	}
	
	public String getAddress2() {
		/* 
		 * Method getAddress2() returns a string. 
		 */
		return _address2;
	}
	public void setAddress2(String value) {
		/*
		 * Method setAddress2() accepts a string as a value.
		 */
		_address2 = value;
	}
	
	public String getCity() {
		/* 
		 * Method getCity() returns a string. 
		 */
		return _city;
	}
	public void setCity(String value) {
		/*
		 * Method setCity() accepts a string as a value.
		 */
		_city = value;
	}
	
	public String getState() {
		/* 
		 * Method getState() returns a string. 
		 */
		return _state;
	}
	public void setState(String value) {
		/*
		 * Method setState() accepts a string as a value.
		 */
		_state = value;
	}
	
	public String getZip() {
		/* 
		 * Method getZip() returns a string. 
		 */
		return _zip;
	}
	public void setZip(String value) {
		/*
		 * Method setZip() accepts a string as a value.
		 */
		_zip = value;
	}
}
