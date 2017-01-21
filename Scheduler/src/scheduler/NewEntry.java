package scheduler;

public class NewEntry {
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

	public NewEntry(String fn, String ln, String phone, String email, String add, String city, String state, String zip) {
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
	
	public String getLastName() {
		return _lastName;
	}
	
	public String getPhone() {
		return _phone;
	}
	
	public String getEmail() {
		return _email;
	}
	
	public String getAddress() {
		return _address;
	}
	
	public String getCity() {
		return _city;
	}
	
	public String getState() {
		return _state;
	}
	
	public String getZip() {
		return _zip;
	}
}