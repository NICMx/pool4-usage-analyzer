package pool4Usage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Pool4Analyzer Pool4Analyzer reports the used percentage of a Pool4 database
 * in comparison with the BIB database. Each Pool4 entry will be displayed with
 * its corresponding Mark, Protocol, Total, Used and Used Percentage(Comparison
 * between Total and Used)
 * 
 * @version 1.0 (current version number of program)
 * @since 27-04-2016 (the version of the package this class was first added to)
 */

public class Pool4Analyzer {

	/**
	 * The main method is called at the moment the program is executed. Pool4
	 * entries are read by executing the command "jool --pool4 --display --csv"
	 * into the command line of the user. Each resulting line is analyzed, split
	 * and stored within a Pool4Entry. This Pool4Entry will be added into the
	 * Pool4Entry array(pool4Data).
	 * 
	 * BIB entries are read by executing the command
	 * "sudo jool --bib --display --numeric --csv" into the command line of the
	 * user. Each resulting line is analyzed, split and stored within a
	 * BibEntry. This BibEntry will be added into the BibEntry array(bibData).
	 * 
	 * pool4Data array is reorganized with a quicksort algorithm DisplayData is
	 * called and the pool4 usage will be displayed into the command line.
	 */

	public static void main(String args[]) throws IOException {
		ArrayList<Pool4Entry> pool4Data = new ArrayList<Pool4Entry>();
		ArrayList<BibEntry> bibData = new ArrayList<BibEntry>();

		Runtime runtime = Runtime.getRuntime();
		Process process = runtime.exec("jool --pool4 --display --csv");
		Scanner scanner = null;
		try {
			scanner = new Scanner(process.getInputStream());
			while (scanner.hasNext()) {
				String currentRow = scanner.nextLine();
				Pool4Entry pool4entry = new Pool4Entry(currentRow);
				pool4Data.add(pool4entry);
			}
		} finally {
			if (scanner != null)
				scanner.close();
		}
		try {
			process = runtime.exec("sudo jool --bib --display --numeric --csv");
			scanner = new Scanner(process.getInputStream());
			while (scanner.hasNext()) {
				String currentRow = scanner.nextLine();
				BibEntry bibentry = new BibEntry(currentRow);
				bibData.add(bibentry);
			}
		} finally {
			if (scanner != null)
				scanner.close();
		}

		quickSort(pool4Data, 0, (pool4Data.size() - 1));
		DisplayData display = new DisplayData();
		display.display(pool4Data, bibData);

	}

	/**
	 * QuickSort is called to organize Pool4Entry array. Making it incremental
	 * in the mark
	 * 
	 * @param data
	 *            Pool4Entry Array that will be sorted
	 * @param low
	 *            low pivot in the sorting algorithm
	 * @param high
	 *            high pivot in the sorting algorithm
	 */
	public static void quickSort(ArrayList<Pool4Entry> data, int low, int high) {
		if (data.size() == 0)
			return;
		if (low >= high)
			return;

		int middle = low + high;
		Pool4Entry pivot = data.get(middle);
		String pivotValue = pivot.getMark();
		int pivotInt = Integer.parseInt(pivotValue);
		int i = low;
		int j = high;
		while (i <= j) {
			while (Integer.parseInt(data.get(i).getMark()) < pivotInt) {
				i++;
			}
			while (Integer.parseInt(data.get(j).getMark()) < pivotInt) {
				i++;
			}
			if (i <= j) {
				Pool4Entry temp = data.get(i);
				data.set(i, data.get(j));
				data.set(j, temp);
				i++;
				j++;
			}
		}
		if (low < j)
			quickSort(data, low, j);
		if (high > i)
			quickSort(data, i, high);
	}
}