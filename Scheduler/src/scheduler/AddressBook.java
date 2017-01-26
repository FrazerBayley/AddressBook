import java.util.ArrayList;

public class AddressBook {
	private ArrayList<Contact> AB;
	
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
	
	public void Save(String filepath) {
		//TSV_Writer(filepath)
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
	
	public void Sort(Boolean key) {
		// 0 = zip
		// 1 = lastname
		if (key) {
			//AB.sort(Comparator<? super Contact> c); ie: Contact.lastname < OtherContact.lastname
		}
		else {
			//AB.sort(Comparator<? super Contact> c); ie: Contact.zipcode < OtherContact.zipcode
		}
	}
}
