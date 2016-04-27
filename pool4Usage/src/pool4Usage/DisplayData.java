package pool4Usage;

import java.util.ArrayList;

/**
 * Display Daya main function is to display the usage of a pool4Entry array in
 * comparison with a bibEntry array
 */
public class DisplayData {

	public DisplayData() {
	}

	/**
	 * display main function is to display the corresponding data of the
	 * pool4Entry array into the command line
	 * 
	 * @param pool4Data
	 *            Pool4Entry array
	 * @param bibData
	 *            BibEntry array
	 */
	public void display(ArrayList<Pool4Entry> pool4Data, ArrayList<BibEntry> bibData) {
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

}
