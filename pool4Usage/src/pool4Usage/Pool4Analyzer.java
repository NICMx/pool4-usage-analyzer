package pool4Usage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Pool4Analyzer{
	
	public static void main(String args[]) throws IOException {
	ArrayList<String[]> pool4Data = new ArrayList<String[]>();
	ArrayList<String[]> bibData = new ArrayList<String[]>();
	Runtime runtime = Runtime.getRuntime();
	Process process = runtime.exec("jool --pool4 --display --csv");
	@SuppressWarnings("resource")
	Scanner scanner = new Scanner(process.getInputStream());
		String splitCsvBy = ",";	
		while(scanner.hasNext()){
			String currentRow = scanner.nextLine();
			String[] pool4Row = currentRow.split(splitCsvBy);
			pool4Data.add(pool4Row);
		}
	
	process = runtime.exec("sudo jool --bib --display --numeric --csv");
	scanner = new Scanner(process.getInputStream());	
		while(scanner.hasNext()){
			String currentRow = scanner.nextLine();
			String[] bibRow = currentRow.split(splitCsvBy);
			bibData.add(bibRow);
		}
	
	quickSort(pool4Data, 0, (pool4Data.size()-1));	
	System.out.println("Mark	Proto	Total	Used	used%");
	for (int i = 0; i < (pool4Data.size()-1); i++) {
		String[] dataPool = pool4Data.get(i);
		int range = Integer.parseInt(dataPool[4]) - Integer.parseInt(dataPool[3]);
		int rangeAux = 0;
		for (int j = 0; j < (bibData.size()-1); j++) { 
			String [] dataBIB = bibData.get(j);
			if ( dataPool[1].equals(dataBIB[0])){
				if ( dataBIB[3].equals(dataPool[2])) {
					rangeAux++;
				}
				}
			}
		int used = (rangeAux * 100) / range;
		System.out.println(dataPool[0]+"	"+dataPool[1]+"	"+range+"	"+rangeAux
				+"	"+used);
		}		
	}
	
	public static void quickSort(ArrayList<String[]> data, int low, int high)
	{
		if (data.size() == 0)
			return;
		
		if (low >= high)
			return;
		
		int middle = low + high;
		String[] pivot = data.get(middle);
		String pivotValue = pivot[0];
		int pivotInt = Integer.parseInt(pivotValue);
		int i= low;
		int j= high;
		while (i <= j) {
			while (Integer.parseInt(data.get(i)[0]) < pivotInt) {
				i++;
			}
			while (Integer.parseInt(data.get(j)[0]) < pivotInt) {
				i++;
			}	
			if ( i <= j) {
				String[] temp = data.get(i);
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