package scheduler;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class TSV_Reader {
	
	public static void reader(AddressBook ab, String filepath) throws Exception {
		// need to assert that filepath ends in .tsv
		Scanner TSVFile = new Scanner(new File(filepath));
		ArrayList<String> dataArray = new ArrayList<String>();
		if (TSVFile.hasNextLine())
			TSVFile.nextLine();
		while (TSVFile.hasNextLine()) {
			String line = TSVFile.nextLine();
			Scanner lineScanner = new Scanner(line);
			if (line.startsWith("\t")) {
				dataArray.add("");
			}
			lineScanner.useDelimiter("\t");
			while(lineScanner.hasNext()) {
				dataArray.add(lineScanner.next().trim());
			}
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
