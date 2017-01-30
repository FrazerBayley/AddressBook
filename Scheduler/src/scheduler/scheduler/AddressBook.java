package scheduler;
import java.util.ArrayList;
import java.util.Collections;

public class AddressBook {
	private ArrayList<Contact> AB;
	private String filepath;
	
	AddressBook() {
		AB = new ArrayList<Contact>();
	}
	
	AddressBook(String filepath) {
		AB = new ArrayList<Contact>();
		try {
			TSV_Reader.reader(this, filepath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Open(String filepath) {
		try {
			TSV_Reader.reader(this, filepath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean Save(String filepath) {
		/* If writer is successful at exporting
		 * it will return true, this can be used for
		 * creating user notification and error
		 * handling.
		 */
		if (TSV_Writer.writer(this, filepath))
			return true;
		return false;
	}
	
	public void Add(Contact data) {
		AB.add(data);
	}
	
	public void Delete(int index) {
		AB.remove(index);
	}

	public ArrayList<Contact> getBook() {
		return AB;
	}
	
	public Contact getContact(Integer index) {
		return AB.get(index);
	}
	
	public String getFilePath() {
		return filepath;
	}
	
	public void setFilePath(String fp) {
		filepath = fp;
	}
	
	public ArrayList<Contact> Search(String term) {
		// might be able to rearrange search terms for
		// the most probable terms being searched first
		ArrayList<Contact> rv = new ArrayList<Contact>();
		
		for (Contact c : AB) {
			if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(c.getAddress1(), term)) {
				rv.add(c);
				continue;
			}
			if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(c.getAddress2(), term)) {
				rv.add(c);
				continue;
			}
			if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(c.getCity(), term)) {
				rv.add(c);
				continue;
			}
			if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(c.getState(), term)) {
				rv.add(c);
				continue;
			}
			if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(c.getZip(), term)) {
				rv.add(c);
				continue;
			}
			if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(c.getEmail(), term)) {
				rv.add(c);
				continue;
			}
			if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(c.getFirstName(), term)) {
				rv.add(c);
				continue;
			}
			if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(c.getLastName(), term)){
				//c.getLastName().contains(term)) {
				rv.add(c);
				continue;
			}
			if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(c.getPhone(), term)) {
				rv.add(c);
			}
		}
		return rv;
	}
	
	public void SortByName() {
		/*		
		This method is implemented in the AddressBook class. It uses the COMPARE_BY_NAME comparator in the 
		Contacts class to sort ArrayList<Contact> AB. This doesnt take any inputs or have any outputs, it 
		updates the AddressBook object itself.
		 */
		Collections.sort(AB, Contact.COMPARE_BY_NAME);
	}
	
	public void SortByZip() {
		/*		
		This method is implemented in the AddressBook class. It uses the COMPARE_BY_ZIP comparator 
		in the Contacts class to sort ArrayList<Contact> AB. This doesnt take any inputs or have any outputs, 
		it updates the AddressBook object itself.
		*/
		Collections.sort(AB, Contact.COMPARE_BY_ZIP);
	}
}
