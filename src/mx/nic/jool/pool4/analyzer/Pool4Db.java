package mx.nic.jool.pool4.analyzer;

import java.util.ArrayList;
import java.util.List;

public class Pool4Db {

	private List<Pool4Table> pool4 = new ArrayList<Pool4Table>();

	public void add(Pool4Entry entry) {
		for (Pool4Table table : pool4) {
			if (table.matches(entry)) {
				table.add(entry);
				return;
			}
		}
		
		pool4.add(new Pool4Table(entry));
	}

	public boolean use(BibEntry bib) {
		boolean used = false;
		
		for (Pool4Table table : pool4) {
			if (table.contains(bib)) {
				table.use();
				used = true;
			}
		}
		
		return used;
	}

	public List<Pool4Table> getTables() {
		return pool4;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Pool4Table table : pool4) {
			sb.append(table).append("\n");
		}
		return sb.toString();
	}
	
}
