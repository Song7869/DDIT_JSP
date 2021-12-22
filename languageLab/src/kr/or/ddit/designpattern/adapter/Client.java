package kr.or.ddit.designpattern.adapter;

public class Client {
	static Target target= new Adapter(new Adaptee());
	public static void main(String[] args) {
		target.request();
	}
}
