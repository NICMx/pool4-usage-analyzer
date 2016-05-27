package mx.nic.jool.pool4.analyzer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Pool4Entry Pool4Entry is an instance of a Pool4 Database containing all the
 * corresponding attributes of it.
 */
public class Pool4Entry {

	private int mark;
	private Protocol protocol;
	private Pool4Range range;

	/**
	 * Constructor method that receives the read line from the command line and
	 * it's split and stored within a String array. Each element of the array is
	 * a corresponding attribute of the Pool4Entry. They are assigned to each
	 * corresponding attribute.
	 * 
	 * @param currentRow
	 *            String from the Command Line
	 * @throws IOException
	 * @throws UnknownHostException
	 * @throws NumberFormatException
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
	 * Gets the Mark of the Pool4Entry
	 * 
	 * @return this pool4Entry Mark
	 */
	public int getMark() {
		return mark;
	}

	/**
	 * Gets the Protocol of the Pool4Entry
	 * 
	 * @return this pool4Entry protocol
	 */
	public Protocol getProtocol() {
		return protocol;
	}

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
