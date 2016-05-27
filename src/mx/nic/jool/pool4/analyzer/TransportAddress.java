package mx.nic.jool.pool4.analyzer;

import java.net.InetAddress;

public class TransportAddress {

	private InetAddress address;
	private int port;

	public TransportAddress(InetAddress address, int port) {
		this.address = address;
		this.port = port;
	}

	public InetAddress getAddress() {
		return address;
	}

	public int getPort() {
		return port;
	}
	
	@Override
	public String toString() {
		return address + "#" + port;
	}

}
