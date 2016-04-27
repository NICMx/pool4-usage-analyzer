package pool4Usage;

/**
 * BibEntry BibEntry is an instance of a BIB Database containing all the
 * corresponding attributes of it.
 */
public class BibEntry {

	public String protocol;
	public String ipv6_address;
	public String ipv6_l4id;
	public String ipv4_address;
	public int ipv4_l4id;
	public int isStatic;

	/**
	 * Constructor method that can receive all the BibEntry attributes
	 * 
	 * @param protocol
	 *            Protocol of the BibEntry
	 * @param ipv6_address
	 *            Ipv6 Address of the BibEntry
	 * @param ipv6_l4id
	 *            Ipv6 l4id of the BibEntry
	 * @param ipv4_address
	 *            Ipv4 Address of the BibEntry
	 * @param ipv4_l4id
	 *            Ipv4 l4id of the BibEntry
	 * @param isStatic
	 *            static value of the BibEntry
	 */
	public BibEntry(String protocol, String ipv6_address, String ipv6_l4id, String ipv4_address, int ipv4_l4id,
			int isStatic) {
		this.protocol = protocol;
		this.ipv6_address = ipv6_address;
		this.ipv6_l4id = ipv6_l4id;
		this.ipv4_address = ipv4_address;
		this.ipv4_l4id = ipv4_l4id;
		this.isStatic = isStatic;
	}

	/**
	 * Constructor method that receives the read line from the command line and
	 * it's split and stored within a String array. Each element of the array is
	 * a corresponding attribute of the BibEntry. They are assigned to each
	 * corresponding attribute.
	 * 
	 * @param currentRow
	 *            String from the Command Line
	 */
	public BibEntry(String currentRow) {
		String splitCsvBy = ",";
		String[] bibRow = currentRow.split(splitCsvBy);
		this.protocol = bibRow[0];
		this.ipv6_address = bibRow[1];
		this.ipv6_l4id = bibRow[2];
		this.ipv4_address = bibRow[3];
		this.ipv4_l4id = Integer.parseInt(bibRow[4]);
		this.isStatic = Integer.parseInt(bibRow[5]);
	}

	/**
	 * Gets the Protocol of the BibEntry
	 * 
	 * @return this BibEntry protocol
	 */
	public String getProtocol() {
		return protocol;
	}

	/**
	 * Sets the Protocol of the Bib Entry
	 * 
	 * @param protocol
	 *            new protocol value
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	/**
	 * Gets the Ipv6 Address of the BibEntry
	 * 
	 * @return this BibEntry Ipv6 Address
	 */
	public String getIpv6_address() {
		return ipv6_address;
	}

	/**
	 * Sets the Ipv6 Address of the Bib Entry
	 * 
	 * @param ipv6_addess
	 *            new ipv6_address value
	 */
	public void setIpv6_address(String ipv6_address) {
		this.ipv6_address = ipv6_address;
	}

	/**
	 * Gets the Ipv6 l4id of the BibEntry
	 * 
	 * @return this BibEntry Ipv6 l4id
	 */
	public String getIpv6_l4id() {
		return ipv6_l4id;
	}

	/**
	 * Sets the ipv6 l4id of the Bib Entry
	 * 
	 * @param ipv6_l4id
	 *            new Ipv6 l4id value
	 */
	public void setIpv6_l4id(String ipv6_l4id) {
		this.ipv6_l4id = ipv6_l4id;
	}

	/**
	 * Gets the Ipv4 Address of the BibEntry
	 * 
	 * @return this BibEntry Ipv4 Address
	 */
	public String getIpv4_address() {
		return ipv4_address;
	}

	/**
	 * Sets the Ipv4 Address of the Bib Entry
	 * 
	 * @param ipv4_address
	 *            new Ipv4 Address value
	 */
	public void setIpv4_address(String ipv4_address) {
		this.ipv4_address = ipv4_address;
	}

	/**
	 * Gets the Ipv4 l4id of the BibEntry
	 * 
	 * @return this BibEntry Ipv4 l4id
	 */
	public int getIpv4_l4id() {
		return ipv4_l4id;
	}

	/**
	 * Sets the Ipv4 l4id of the Bib Entry
	 * 
	 * @param ipv4_l4id
	 *            new Ipv4 l4id value
	 */
	public void setIpv4_l4id(int ipv4_l4id) {
		this.ipv4_l4id = ipv4_l4id;
	}

	/**
	 * Gets the static value of the BibEntry
	 * 
	 * @return this BibEntry static value
	 */
	public int getIsStatic() {
		return isStatic;
	}

	/**
	 * Sets the Static value of the Bib Entry
	 * 
	 * @param isStatic
	 *            new static value
	 */
	public void setIsStatic(int isStatic) {
		this.isStatic = isStatic;
	}
}
