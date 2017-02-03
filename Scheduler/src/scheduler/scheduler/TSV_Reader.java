package scheduler;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class TSV_Reader {
	
	public static void reader(AddressBook ab, String filepath) throws Exception {
		/* Reader to import .tsv address books.  This importer assumes
		 * user is trying to import a correctly formatted address book
		 * .tsv file.  File path enforcement is handled by the GUI file
		 * explorer.
		 */
		Scanner TSVFile = new Scanner(new File(filepath));
		ArrayList<String> dataArray = new ArrayList<String>();
		
		/* Skip the first line as it will contain the column headers */
		if (TSVFile.hasNextLine())
			TSVFile.nextLine();
		
		/* Process the entries, create contacts, add contacts to address book */
		while (TSVFile.hasNextLine()) {
			String line = TSVFile.nextLine();
			Scanner lineScanner = new Scanner(line);
			/* Edge case, when the first entry is blank we must manually add it */
			if (line.startsWith("\t")) {
				dataArray.add("");
			}
			lineScanner.useDelimiter("\t");
			while(lineScanner.hasNext()) {
				dataArray.add(lineScanner.next().trim());
			}
			/* Edge case, when the last element is empty the tab will truncate */
			if (dataArray.size() == 8) {
				dataArray.add("");
			}
			Contact ce = new Contact(dataArray);
			ab.Add(ce);
			dataArray.clear();
			lineScanner.close();
		}
		TSVFile.close();	
	}
}
