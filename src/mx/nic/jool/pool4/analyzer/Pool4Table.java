package mx.nic.jool.pool4.analyzer;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection of {@link Pool4Entry}s that share a mark and a protocol.
 * 
 * Each one of these will yield a row in the analyzer's output.
 */
public class Pool4Table {

	/** ip6tables mark that all entries in this table share. */
	private int mark;
	/** Protocol that all entries in this table share. */
	private Protocol protocol;
	/** This table's rows. */
	private List<Pool4Entry> entries;
	/**
	 * Number of transport addresses in this table that have been borrowed by a
	 * BIB entry.
	 */
	private int usedTransportAddresses;

	/**
	 * @param entry
	 *            Every table must have at least one entry. Use `entry` as the
	 *            starting point.
	 */
	public Pool4Table(Pool4Entry entry) {
		mark = entry.getMark();
		protocol = entry.getProtocol();
		entries = new ArrayList<Pool4Entry>();
		entries.add(entry);
	}

	/**
	 * @see #mark
	 */
	public int getMark() {
		return mark;
	}

	/**
	 * @see #protocol
	 */
	public Protocol getProtocol() {
		return protocol;
	}

	/**
	 * Stores `entry` in this table.
	 * 
	 * @param entry
	 *            row to add to this table.
	 */
	public void add(Pool4Entry entry) {
		entries.add(entry);
	}

	/**
	 * Should `entry` be a member of this table?
	 * 
	 * @param entry
	 *            Candidate.
	 * @return Whether `entry` is or should be added to this table.
	 */
	public boolean matches(Pool4Entry entry) {
		return mark == entry.getMark() && protocol == entry.getProtocol();
	}

	/**
	 * Was `bib` borrowed from this table?
	 * 
	 * @param bib
	 *            Candidate.
	 * @return Whether Jool borrowed `bib` from this table.
	 */
	public boolean contains(BibEntry bib) {
		if (protocol != bib.getProtocol())
			return false;

		for (Pool4Entry entry : entries) {
			if (entry.getRange().contains(bib.getAddress4())) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Returns the number of transport addresses this table's entries translate
	 * into.
	 * 
	 * That number is also the amount of BIB entries that can be borrowed from
	 * this table.
	 * 
	 * @return Number of BIB entries that can be borrowed from this table.
	 */
	public int getTotalTransportAddresses() {
		int total = 0;
		for (Pool4Entry entry : entries) {
			total += entry.getRange().getTotalPorts();
		}
		return total;
	}

	/**
	 * @see #usedTransportAddresses
	 */
	public int getUsedTransportAddresses() {
		return usedTransportAddresses;
	}

	/**
	 * Makes this table recall that a BIB entry was borrowed from it.
	 */
	public void use() {
		usedTransportAddresses++;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder().append("[Pool4Table");

		sb.append(" mark=").append(mark);
		sb.append(" protocol=").append(protocol);
		sb.append(" used Transport Addresses=").append(usedTransportAddresses);
		sb.append("\n");
		for (Pool4Entry entry : entries) {
			sb.append("\t").append(entry).append("\n");
		}

		return sb.append("]").toString();
	}

}
