package mx.nic.jool.pool4.analyzer;

import java.net.InetAddress;

/**
 * As stated, just an address and a protocol glued together. Intended as a hash
 * table key.
 */
public class AddressProtocolTuple {

	/** First tuple component. */
	private InetAddress address;
	/** Second tuple component. */
	private Protocol protocol;

	/**
	 * @see #address
	 * @see #protocol
	 */
	public AddressProtocolTuple(InetAddress address, Protocol protocol) {
		if (address == null) {
			throw new NullPointerException("address can't be null.");
		}
		if (protocol == null) {
			throw new NullPointerException("protocol can't be null.");
		}

		this.address = address;
		this.protocol = protocol;
	}

	/**
	 * @param entry
	 *            Thingie where the tuple is going to be inferred from.
	 */
	public AddressProtocolTuple(Pool4Entry entry) {
		this(entry.getRange().getAddress(), entry.getProtocol());
	}

	/**
	 * @param entry
	 *            Thingie where the tuple is going to be inferred from.
	 */
	public AddressProtocolTuple(BibEntry entry) {
		this(entry.getAddress4().getAddress(), entry.getProtocol());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof AddressProtocolTuple)) {
			return false;
		}
		if (this == obj) {
			return true;
		}

		AddressProtocolTuple other = (AddressProtocolTuple) obj;
		return address.equals(other.address) && protocol.equals(other.protocol);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + address.hashCode();
		result = prime * result + protocol.hashCode();

		return result;
	}

}
