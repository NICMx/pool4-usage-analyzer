package mx.nic.jool.pool4.analyzer;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;

public class Pool4Range {

	private InetAddress address;
	private int portMin;
	private int portMax;

	public Pool4Range(InetAddress address, int portMin, int portMax) throws IOException {
		super();
		
		if (!(address instanceof Inet4Address)) {
			throw new IOException("The third column is supposed to be an IPv4 address.");
		}
		
		this.address = address;
		this.portMin = portMin;
		this.portMax = portMax;
	}

	public Pool4Range(TransportAddress address4) {
		this.address = address4.getAddress();
		this.portMin = address4.getPort();
		this.portMin = this.portMax;
	}
	
	public InetAddress getAddress() {
		return address;
	}
	
	public int getPortMin() {
		return portMin;
	}
	
	public int getPortMax() {
		return portMax;
	}
	
	public int getTotalPorts() {
		return portMax - portMin + 1;
	}

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
