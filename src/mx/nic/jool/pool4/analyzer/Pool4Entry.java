package mx.nic.jool.pool4.analyzer;

import java.io.IOException;
import java.net.InetAddress;

/**
 * A pool4 entry, added to Jool via `jool -4a`.
 * 
 * Represents a handful of IPv4 transport address that can be used to mask an
 * IPv6 client or server.
 */
public class Pool4Entry {

	/** Marks that can be masked by this entry. */
	private int mark;
	/** The protocol {@link #range}'s l4 identifiers refer to. */
	private Protocol protocol;
	/** The potential masks. */
	private Pool4Range range;

	/**
	 * Creates a {@link Pool4Entry} out of `jool --pool4`'s CSV table output.
	 * 
	 * @param currentRow
	 *            One of the table rows from `jool --pool4`'s CSV output.
	 *            Supposed to represent a pool4 entry.
	 * @throws IOException
	 *             Could not parse the row properly.
	 */
	public Pool4Entry(String currentRow) throws IOException {
		String[] pool4Row = currentRow.split(",");

		this.mark = Integer.parseInt(pool4Row[0]);
		this.protocol = Protocol.valueOf(pool4Row[1]);

		InetAddress address = InetAddress.getByName(pool4Row[2]);
		int minPort = Integer.parseInt(pool4Row[3]);
		int maxPort = Integer.parseInt(pool4Row[4]);
		this.range = new Pool4Range(address, minPort, maxPort);
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
	 * @see #range
	 */
	public Pool4Range getRange() {
		return range;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder().append("[Pool4Entry");

		sb.append(" mark=").append(mark);
		sb.append(" protocol=").append(protocol);
		sb.append(" range=").append(range);

		return sb.append("]").toString();
	}

}
