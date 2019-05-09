package sossim.core;

public class SOSProcess {

	private int AT; // Arrival Time
	private int RT; // Response Time
	private int TT; // TurnaroundTime
	private String code; // instructions each takes 1 tick to complete * = normal instruction $ = exit @
							// = block for IO
	private int size; // size in MBs
	private int counter;
	private String name;

	public SOSProcess(int time, String c, int s, String n) {
		AT = time;
		code = c;
		size = s;
		counter = 0;
		name = n;
	}

	public char execute(long t) {
		if (counter == 0)
			RT = (int) (t - AT);
		char command = code.charAt(counter);
		if (command == '$')
			TT = (int) (t - AT);
		counter++;
		return command;
	}

	public int getSize() {
		return size;
	}

	public int getAT() {
		return AT;
	}

	public int getRT() {
		return RT;
	}

	public int getTT() {
		return TT;
	}

	public String getName() {
		return name;
	}

	public int getCounter() {
		return counter;
	}

	public double getProgress() {
		return (100.0 * counter) / code.length();
	}

	public int getCodeSize() {
		return code.length();
	}

	public String toString() {
		return "Proc: " + name + " program counter=" + counter;
	}
}