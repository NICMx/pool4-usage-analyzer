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

	/** Thingo Java provides so we can run commands as if we were a console. */
	private Runtime runtime = Runtime.getRuntime();
	/** The output of `jool -4`, parsed and loaded into memory. */
	private Pool4Db pool4 = new Pool4Db();
	/** BIB entries that don't belong to any pool4 entries. */
	private int orphanBibs = 0;

	/** Name of the `jool` binary. The user can rename it. */
	private String joolBinary = "jool";
	/** Do not print table header and {@link #orphanBibs}? */
	private boolean printMinimal = false;

	public static void main(String args[]) throws IOException, InterruptedException {
		new Pool4Analyzer().analyze(args);
	}

	private void analyze(String args[]) throws IOException, InterruptedException {
		parseArgs(args);
		loadPool4();
		handleBib();
		printResults();
	}

	/**
	 * Parses program arguments, configuring this object.
	 */
	private void parseArgs(String args[]) {
		for (int i = 0; i < args.length; i++) {
			if ("--minimal".equals(args[i])) {
				printMinimal = true;
			} else if (i + 1 != args.length && "--jool-binary".equals(args[i])) {
				joolBinary = args[i + 1];
				i++;
			} else {
				System.err.println("I don't know what '" + args[i] + "' is; ignoring.");
			}
		}
	}

	/**
	 * Loads Jool's current pool4 into {@link #pool4}.
	 */
	private void loadPool4() throws IOException, InterruptedException {
		// String command = "cat test/pool4.output";
		String command = joolBinary + " --pool4 --display --csv";
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
	}

	/**
	 * Walks through Jool's current BIB, collecting the pool4 usage stats.
	 */
	private void handleBib() throws IOException, InterruptedException {
		// String command = "cat test/bib.output";
		String command = "sudo " + joolBinary + " --bib --display --numeric --csv";
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

	/**
	 * Prints the information gathered by the other methods.
	 */
	private void printResults() {
		if (!printMinimal) {
			System.out.println("Mark	Proto	Total	Used	Used %");
		}

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

		if (!printMinimal) {
			System.out.println();
			System.out.println("Orphan BIB entries: " + orphanBibs);
		}
	}

}