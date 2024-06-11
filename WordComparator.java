
// Raika Roy Choudhury, APCS Peterson P2
// Programming Project #11; 01 April 2022

import java.util.Comparator;

public class WordComparator implements Comparator<String> {
	public WordComparator() {
	}

	// overrides compare method to sort based on length and alphabetize it
	@Override
	public int compare(String arg0, String arg1) {
		if (arg0.length() > arg1.length()) {
			return -1;
		} else if (arg0.length() < arg1.length()) {
			return 1;
		}
		for (int i = 0; i < arg0.length(); i++) {
			if (arg0.charAt(i) > arg1.charAt(i)) {
				return 1;
			} else if (arg0.charAt(i) < arg1.charAt(i)) {
				return -1;
			}
		}
		return 0;
	}
}
