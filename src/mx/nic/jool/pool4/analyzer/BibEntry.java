package mx.nic.jool.pool4.analyzer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * A mapping between an IPv4 transport address and an IPv6 one, though more to
 * the point a signal that an IPv4 transport address was borrowed from pool4.
 */
public class BibEntry {

	/** The protocol {@link #address4}'s l4 identifier refers to. */
	public Protocol protocol;
	/** IPv4 transport address that was borrowed from pool4. */
	private TransportAddress address4;

	/**
	 * Creates a {@link BibEntry} out of `jool --bib`'s CSV table output.
	 * 
	 * @param currentRow
	 *            One of the table rows from `jool --bib`'s CSV output. Supposed
	 *            to represent a BIB entry.
	 * @throws IOException
	 *             Could not parse the row properly.
	 */
	public BibEntry(String currentRow) throws UnknownHostException {
		String[] bibRow = currentRow.split(",");
		this.protocol = Protocol.valueOf(bibRow[0]);

		// Don't need this.
		// this.ipv6Address = bibRow[1];
		// this.ipv6Port = bibRow[2];

		InetAddress address = InetAddress.getByName(bibRow[3]);
		int port = Integer.parseInt(bibRow[4]);
		this.address4 = new TransportAddress(address, port);

		// this.isStatic = Integer.parseInt(bibRow[5]);
	}

	/**
	 * @see #protocol
	 */
	public Protocol getProtocol() {
		return protocol;
	}

	/**
	 * @see #address4
	 */
	public TransportAddress getAddress4() {
		return address4;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder().append("[BibEntry");

		sb.append(" protocol=").append(protocol);
		sb.append(" address4=").append(address4);

		return sb.append("]").toString();
	}

}
