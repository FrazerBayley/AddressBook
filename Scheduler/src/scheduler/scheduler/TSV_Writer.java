package scheduler;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class TSV_Writer {

	public static boolean writer(AddressBook ab, String filepath) {
		// need to assert filepath ends in .tsv
		Writer writer = null;

		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream(filepath), "utf-8"));
		    
		    ArrayList<Contact> book = ab.getBook();
		    writer.write("CITY" + "\t");
		    writer.write("STATE" + "\t");
		    writer.write("ZIP" + "\t");
		    writer.write("Email" + "\t");
		    writer.write("Delivery" + "\t");
		    writer.write("Second" + "\t");
		    writer.write("FirstName" + "\t");
		    writer.write("LastName" + "\t");
		    writer.write("Phone" + "\n");
		    // aziz : address 1 and 2 added


		    
		    for (Contact c : book) {
		    	writer.write(c.getFirstName() + "\t");
		    	writer.write(c.getLastName() + "\t");
		    	writer.write(c.getPhone() + "\t");
		    	writer.write(c.getEmail() + "\t");
		    	// aziz : address 1 and 2 added
		    	writer.write(c.getAddress1() + "\t");
		    	writer.write(c.getAddress2() + "\t");
		    	writer.write(c.getState() + "\t");
		    	writer.write(c.getCity() + "\t");
		    	writer.write(c.getZip()  + "\n");
		    }
		} catch (IOException ex) {
			return false;
		} finally {
		   try {writer.close();} catch (Exception ex) {}
		}
		return true;
	}
}
