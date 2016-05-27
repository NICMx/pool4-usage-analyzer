package mx.nic.jool.pool4.analyzer;

import java.util.ArrayList;
import java.util.List;

public class Pool4Table {

	private int mark;
	private Protocol protocol;
	private List<Pool4Entry> entries;
	private int usedTransportAddresses;

	public Pool4Table(Pool4Entry entry) {
		mark = entry.getMark();
		protocol = entry.getProtocol();
		entries = new ArrayList<Pool4Entry>();
		entries.add(entry);
	}

	public int getMark() {
		return mark;
	}

	public Protocol getProtocol() {
		return protocol;
	}
	
	public void add(Pool4Entry entry) {
		entries.add(entry);
	}

	public boolean matches(Pool4Entry entry) {
		return mark == entry.getMark() && protocol == entry.getProtocol();
	}

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

	public int getTotalTransportAddresses() {
		int total = 0;
		for (Pool4Entry entry : entries) {
			total += entry.getRange().getTotalPorts();
		}
		return total;
	}
	
	public int getUsedTransportAddresses() {
		return usedTransportAddresses;
	}
	
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
