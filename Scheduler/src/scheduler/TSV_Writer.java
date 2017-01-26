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
		    writer.write("FirstName");
		    writer.write("LastName");
		    writer.write("Phone");
		    writer.write("Email");
		    writer.write("Address");
		    writer.write("State");
		    writer.write("Zip");
		    
		    for (Contact c : book) {
		    	writer.write(c.getFirstName() + "\t");
		    	writer.write(c.getLastName() + "\t");
		    	writer.write(c.getPhone() + "\t");
		    	writer.write(c.getEmail() + "\t");
		    	writer.write(c.getAddress() + "\t");
		    	writer.write(c.getState() + "\t");
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
