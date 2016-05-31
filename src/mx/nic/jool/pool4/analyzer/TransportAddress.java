package mx.nic.jool.pool4.analyzer;

import java.net.InetAddress;

/**
 * An IP socket identifier consisting of a layer 3 identifier and a layer 4
 * identifier.
 */
public class TransportAddress {

	/** The layer-3 identifier; an IPv4/6 address. */
	private InetAddress address;
	/**
	 * The layer-4 identifier; a port or ICMP id.<br />
	 * (Yes, ICMP is l4. Shut up)
	 */
	private int port;

	public TransportAddress(InetAddress address, int port) {
		this.address = address;
		this.port = port;
	}

	/**
	 * @see #address
	 */
	public InetAddress getAddress() {
		return address;
	}

	/**
	 * @see #port
	 */
	public int getPort() {
		return port;
	}

	@Override
	public String toString() {
		return address + "#" + port;
	}

}
