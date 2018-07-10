package edu.ilstu;

public class Main {
	public static void main(String[] args) {
		Cycle cycle = new Cycle();
		
		for(int i=0; i<10; i++) {
			System.out.println("CYCLE "+(i+1));
			cycle.performCycle();
			System.out.println();
		}
	}
}
