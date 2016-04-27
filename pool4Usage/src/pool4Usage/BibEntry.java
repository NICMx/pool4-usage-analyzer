package pool4Usage;

public class BibEntry {
	
	public String protocol;
	public String ipv6_address;
	public String ipv6_l4id;
	public String ipv4_address;
	public int ipv4_l4id;
	public int isStatic;
	
	public BibEntry(String protocol, String ipv6_address, String ipv6_l4id,
			String ipv4_address, int ipv4_l4id, int isStatic){
		this.protocol = protocol;
		this.ipv6_address = ipv6_address;
		this.ipv6_l4id = ipv6_l4id;
		this.ipv4_address = ipv4_address;
		this.ipv4_l4id = ipv4_l4id;
		this.isStatic = isStatic;
	}
	public BibEntry(String currentRow){
		String splitCsvBy = ",";
		String[] bibRow = currentRow.split(splitCsvBy);
		this.protocol = bibRow[0];
		this.ipv6_address = bibRow[1];
		this.ipv6_l4id = bibRow[2];
		this.ipv4_address = bibRow[3];
		this.ipv4_l4id = Integer.parseInt(bibRow[4]);
		this.isStatic = Integer.parseInt(bibRow[5]);
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getIpv6_address() {
		return ipv6_address;
	}

	public void setIpv6_address(String ipv6_address) {
		this.ipv6_address = ipv6_address;
	}

	public String getIpv6_l4id() {
		return ipv6_l4id;
	}

	public void setIpv6_l4id(String ipv6_l4id) {
		this.ipv6_l4id = ipv6_l4id;
	}

	public String getIpv4_address() {
		return ipv4_address;
	}

	public void setIpv4_address(String ipv4_address) {
		this.ipv4_address = ipv4_address;
	}

	public int getIpv4_l4id() {
		return ipv4_l4id;
	}

	public void setIpv4_l4id(int ipv4_l4id) {
		this.ipv4_l4id = ipv4_l4id;
	}

	public int getIsStatic() {
		return isStatic;
	}

	public void setIsStatic(int isStatic) {
		this.isStatic = isStatic;
	}
	
}
