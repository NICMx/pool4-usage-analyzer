package mx.nic.jool.pool4.analyzer;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;

/**
 * An interval of continuous transport addresses that share an IPv4 address.
 */
public class Pool4Range {

	/** The layer 3 identifier. */
	private InetAddress address;
	/** The lowest layer 4 identifier of this range. */
	private int portMin;
	/** The highest layer 4 identifier of this range. */
	private int portMax;

	/**
	 * @see #address
	 * @see #portMin
	 * @see #portMax
	 * @throws IOException
	 *             `address` is not IPv4.
	 */
	public Pool4Range(InetAddress address, int portMin, int portMax) throws IOException {
		super();

		if (!(address instanceof Inet4Address)) {
			throw new IOException("The third column is supposed to be an IPv4 address.");
		}

		this.address = address;
		this.portMin = portMin;
		this.portMax = portMax;
	}

	/**
	 * @see #address
	 */
	public InetAddress getAddress() {
		return address;
	}

	/**
	 * @see #portMin
	 */
	public int getPortMin() {
		return portMin;
	}

	/**
	 * @see #portMax
	 */
	public int getPortMax() {
		return portMax;
	}

	/**
	 * Returns the number of transport addresses this range contains.
	 * 
	 * @return number of transport addresses this range contains.
	 */
	public int getTotalPorts() {
		return portMax - portMin + 1;
	}

	/**
	 * Does this range contain `transportAddress`?
	 * 
	 * @param transportAddress
	 *            transport address you want to test.
	 * @return whether this range contains `transportAddress`.
	 */
	public boolean contains(TransportAddress transportAddress) {
		if (!address.equals(transportAddress.getAddress()))
			return false;
		if (transportAddress.getPort() < portMin || portMax < transportAddress.getPort())
			return false;

		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder().append("[Pool4Range");

		sb.append(" address=").append(address);
		sb.append(" portMin=").append(portMin);
		sb.append(" portMax=").append(portMax);

		return sb.append("]").toString();
	}

}
