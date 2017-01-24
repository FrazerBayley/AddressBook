package scheduler;

public class Contact {
	/*
	 * Class NewEntry formats user inputed data from GUI into proper categories before storing in arrayList,
	 * it also provides getters.
	 */
	private String _firstName;
	private String _lastName;
	private String _phone;
	private String _email;
	private String _address;
	private String _city;
	private String _state;
	private String _zip;

	public Contact(String fn, String ln, String phone, String email, String add, String city, String state, String zip) {
		_firstName = fn;
		_lastName = ln;
		_phone = phone;
		_email = email;
		_address = add;
		_city = city;
		_state = state;
		_zip = zip;
	}
	
	
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
	
	public String getAddress() {
		return _address;
	}
	public void setAddress(String value) {
		_address = value;
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