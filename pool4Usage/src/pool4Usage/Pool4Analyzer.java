package pool4Usage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Pool4Analyzer {

	public static void main(String args[]) throws IOException {
		ArrayList<Pool4Entry> pool4Data = new ArrayList<Pool4Entry>();
		ArrayList<BibEntry> bibData = new ArrayList<BibEntry>();

		Runtime runtime = Runtime.getRuntime();
		Process process = runtime.exec("jool --pool4 --display --csv");
		Scanner scanner = null;
		String splitCsvBy = ",";
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

		System.out.println("Mark	Proto	Total	Used	used%");
		for (int i = 0; i < (pool4Data.size() - 1); i++) {
			Pool4Entry dataPool = pool4Data.get(i);
			int range = dataPool.getMax_port() - dataPool.getMin_port();
			int rangeAux = 0;
			for (int j = 0; j < (bibData.size() - 1); j++) {
				BibEntry dataBIB = bibData.get(j);
				if (dataPool.getProtocol().equals(dataBIB.getProtocol())) {
					if (dataBIB.getIpv4_address().equals(dataPool.getAddress())) {
						rangeAux++;
					}
				}
			}
			int used = (rangeAux * 100) / range;
			System.out.println(dataPool.getMark() + "	" + dataPool.getProtocol() + "	" + range + "	" + rangeAux
					+ "	" + used);
		}

	}
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