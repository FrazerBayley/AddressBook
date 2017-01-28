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
		// intention: tsv reader will read saved tsv and create contacts to be
		// added.  Essentially this function is the Open call in the form of
		// a constructor.  There's no Open method specifically because that
		// functionality creates a new Addressbook, so Open will belong to the
		// main program and I assume make a call to Save
	}
	
	public ArrayList<Contact> getBook() {
		return AB;
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
		if (TSV_Writer.writer(this, filepath))
			return true;
		return false;
		// intention: write out the contacts in TSV format to be retrieved later.
	}
	
	public void Add(Contact data) {
		AB.add(data);
	}
	
	public void Delete(Contact data) {
		// since we don't know how it is sorted we must check
		// all elements for the matching key
		// hash table would be great for adding / removing but
		// you can't sort a hash table, which is a required feature.
		// so removing right now is a VERY slow operation when the
		// data set gets large.
		//AB.removeIf( i -> { return i.key == data.key; });
	}
	
	public Contact getContact(Integer index) {
		return AB.get(index);
	}
	
	public Contact SearchBy(Contact key) {
		// not sure if the values are sorted in the reference key so
		// linear search ~ also not sure what functionality to provide here
		// should I return index location so we can edit the item in place?
		// should I return the contact object? Should I return the AB key value?
		for (Contact c : AB) {
			if (c == key) {
				return c;
			}
		}
		return null; // should throw an error or something / prompt target not found
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
	public String getFilePath() {
		return filepath;
	}
	public void setFilePath(String fp) {
		filepath = fp;
	}
}
