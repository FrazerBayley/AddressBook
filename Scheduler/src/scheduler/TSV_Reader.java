import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TSV_Reader {
	// currently testing data input hence the main function
	// this will be turned into a helper for the main program
	// ie Open call back will pass in a file path of a TSV to
	// parse and this function will create a local AddressBook
	// and populate it with the proper contacts.
	
	public static void main(String[] arg) throws Exception {
		StringTokenizer st;
		BufferedReader TSVFile = new BufferedReader(new FileReader("/Users/mez/Documents/workspace/CIS422/src/book.tsv"));
		List<String>dataArray = new ArrayList<String>();
		String dataRow = TSVFile.readLine(); // read first line
		
		while (dataRow != null) {
			st = new StringTokenizer(dataRow, "\t");
			while(st.hasMoreElements()) {
				dataArray.add(st.nextElement().toString());
			}
			for (String item:dataArray) {
				System.out.print(item + " ");
				// this is where it would create the contact
				// and do contact.firstname = dataArray[0] etc...
				// see issue below
			}
			System.out.println(); // print the data line.
			// this is where (instead of printing the contact would be added to the AdressBook
			
			System.out.println("There were " + dataArray.size() + " elements on this line.");
			// FIXME: there's a problem when no data is entered the string tokenizer doesn't add
			// anything to the data array and as such there's no guaranteed structure for algorithmically
			// adding contacts by field value... unless I can figure out a way to ensure 6 tokens are always
			// created -- even if it is a blank entry.
			dataArray.clear(); // otherwise old data repeats
			dataRow = TSVFile.readLine(); // read next line of data
		}
		TSVFile.close();
	}
}
