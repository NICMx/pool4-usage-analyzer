package mx.nic.jool.pool4.analyzer;

import java.io.IOException;
import java.util.Scanner;

/**
 * Pool4Analyzer Pool4Analyzer reports the used percentage of a Pool4 database
 * in comparison with the BIB database. Each Pool4 entry will be displayed with
 * its corresponding Mark, Protocol, Total, Used and Used Percentage(Comparison
 * between Total and Used)
 */
public class Pool4Analyzer {

	private Runtime runtime = Runtime.getRuntime();
	private Pool4Db pool4 = new Pool4Db();
	private int orphanBibs = 0;

	public static void main(String args[]) throws IOException, InterruptedException {
		new Pool4Analyzer().analyze();
	}

	private void analyze() throws IOException, InterruptedException {
		loadPool4();
		handleBib();
		printResults();
	}

	/**
	 * Returns Jool's current pool4.
	 */
	private Pool4Db loadPool4() throws IOException, InterruptedException {
		String command = "cat test/pool4.output";
		// String command = "jool --pool4 --display --csv";
		Process process = runtime.exec(command);
		Scanner scanner = null;

		try {
			scanner = new Scanner(process.getInputStream());
			while (scanner.hasNext()) {
				String currentRow = scanner.nextLine();

				if (currentRow.startsWith("Mark")) {
					continue; // Just a table header; skip.
				}

				Pool4Entry pool4entry = new Pool4Entry(currentRow);
				pool4.add(pool4entry);
			}
		} finally {
			if (scanner != null)
				scanner.close();
		}

		handleProcessError(command, process);
		return pool4;
	}

	/**
	 * Walks through Jool's current BIB, collecting the pool4 usage stats.
	 */
	private void handleBib() throws IOException, InterruptedException {
		String command = "cat test/bib.output";
		// String command = "jool --bib --display --numeric --csv";
		Process process = runtime.exec(command);
		Scanner scanner = null;

		try {
			scanner = new Scanner(process.getInputStream());
			while (scanner.hasNext()) {
				String currentRow = scanner.nextLine();

				if (currentRow.startsWith("Protocol")) {
					continue; // Just a table header; skip.
				}

				BibEntry bib = new BibEntry(currentRow);
				boolean pool4EntryExists = pool4.use(bib);
				if (!pool4EntryExists) {
					orphanBibs++;
				}
			}
		} finally {
			if (scanner != null)
				scanner.close();
		}

		handleProcessError(command, process);
	}

	/**
	 * Waits until the command has terminated and throws exceptions if it spewed
	 * errors.
	 */
	private void handleProcessError(String command, Process process) throws IOException, InterruptedException {
		if (process.waitFor() == 0) {
			return;
		}

		StringBuilder sb = new StringBuilder();

		sb.append("'").append(command).append("' threw error code ").append(process.exitValue()).append(".\n");
		sb.append("Stderr shows:\n");

		Scanner scanner = new Scanner(process.getErrorStream());
		try {
			while (scanner.hasNext()) {
				sb.append(scanner.nextLine()).append("\n");
			}
		} finally {
			scanner.close();
		}

		throw new IOException(sb.toString());
	}

	private void printResults() {
		System.out.println("Mark	Proto	Total	Used	Used %");

		for (Pool4Table table : pool4.getTables()) {
			System.out.print(table.getMark());
			System.out.print("\t");
			System.out.print(table.getProtocol());
			System.out.print("\t");
			System.out.print(table.getTotalTransportAddresses());
			System.out.print("\t");
			System.out.print(table.getUsedTransportAddresses());
			System.out.print("\t");
			System.out.print(table.getUsedTransportAddresses() * 100 / table.getTotalTransportAddresses());
			System.out.println();
		}

		System.out.println();
		System.out.println("Orphan BIB entries: " + orphanBibs);
	}

}