package mx.nic.jool.pool4.analyzer;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection of {@link Pool4Table}s.
 * 
 * This is the whole output of `jool -4`.
 */
public class Pool4Db {

	/** The tables. */
	private List<Pool4Table> pool4 = new ArrayList<Pool4Table>();

	/**
	 * @param entry
	 *            Every table must have at least one entry and every database
	 *            must have at least one table. Use `entry` as the starting
	 *            point.
	 */
	public void add(Pool4Entry entry) {
		for (Pool4Table table : pool4) {
			if (table.matches(entry)) {
				table.add(entry);
				return;
			}
		}

		pool4.add(new Pool4Table(entry));
	}

	/**
	 * If `bib` was borrowed from one of this db's tables, makes the table
	 * recall that a BIB entry was borrowed from it.
	 * 
	 * @param bib
	 *            mapping that contains the transport address that's borrowed.
	 * @return whether `bib` belongs to this database or not.
	 */
	public boolean use(BibEntry bib) {
		boolean used = false;

		for (Pool4Table table : pool4) {
			if (table.contains(bib)) {
				table.use();
				used = true;
			}
		}

		return used;
	}

	/**
	 * @see #pool4
	 */
	public List<Pool4Table> getTables() {
		return pool4;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Pool4Table table : pool4) {
			sb.append(table).append("\n");
		}
		return sb.toString();
	}

}
