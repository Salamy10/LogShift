package model;

public class Formulas {
	
	int a, b;
	
	public void setA(int a) {
		this.a = a;
	}
	public void setB(int b) {
		this.b = b;
	}
	
	public int add() {
		return a + b;
	}
	public int subtract() {
		return a - b;
	}
	public int divide() {
		return a / b;
	}
	public int multiply() {
		return a * b;
	}

}
