public class Record {
	private String name;
	private int tries;
	private double time;
	private boolean real;
	
	public static final String nullName = "----------";
	public static final int nullTries = 999;
	public static final double nullTime = 999;
	public Record(String givenName, int givenTries, double givenTime, boolean realPlayer) {
		if (givenName.length() > 10) {
			name = givenName.substring(0, 10);
		} else {
			name = givenName;
		}
		tries = givenTries;
		time = givenTime;
		real = realPlayer;
	}
	
	public int getTries() {
		return tries;
	}
	
	public double getTime() {
		return time;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean getStatus() {
		return real;
	}
	
	public void printString() {
		if (!real) {
			System.out.printf("*Name: %1$-10s Tries: %2$-3s Time: %3$-7s*", "----------", "---", "-------");
		} else {
			System.out.printf("*Name: %1$-10s Tries: %2$03d Time: %3$07.3f*", name, tries, time);
		}
	}
	
	public Record[] sort(Record[] top10) {
		for (int i = 0; i < top10.length; i++) {
			if (this.real && !top10[i].getStatus()) {
				top10[i] = this;
				return top10;
			}
			if (this.tries < top10[i].getTries()) {
				return this.swap(i, top10);
			} else {
				if (this.tries == top10[i].getTries() && this.time <= top10[i].getTime()) {
					return this.swap(i, top10);
				} else {
					
				}
			}
		}
		return top10;
	}
	
	public Record[] swap(int position, Record[] top10) {
		if (position >= top10.length) {
			return top10;
		} else {
			if (position == (top10.length - 1)) {
				top10[position] = this;
				return top10;
			} else {
				Record tmp;
				Record temp = top10[position];
				top10[position] = this;
				for (int i = position + 1; i < top10.length - 1; i++) {
					tmp = top10[i];
					top10[i] = temp;
					temp = tmp;
				}
				top10[top10.length - 1] = temp;
			}
		}
		return top10;
	}
}
