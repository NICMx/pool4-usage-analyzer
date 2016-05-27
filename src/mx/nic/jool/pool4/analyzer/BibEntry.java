package mx.nic.jool.pool4.analyzer;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * BibEntry BibEntry is an instance of a BIB Database containing all the
 * corresponding attributes of it.
 */
public class BibEntry {

	public Protocol protocol;
	private TransportAddress address4;

	/**
	 * Constructor method that receives the read line from the command line and
	 * it's split and stored within a String array. Each element of the array is
	 * a corresponding attribute of the BibEntry. They are assigned to each
	 * corresponding attribute.
	 * 
	 * @param currentRow
	 *            String from the Command Line
	 * @throws UnknownHostException
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
	 * Gets the Protocol of the BibEntry
	 * 
	 * @return this BibEntry protocol
	 */
	public Protocol getProtocol() {
		return protocol;
	}

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
