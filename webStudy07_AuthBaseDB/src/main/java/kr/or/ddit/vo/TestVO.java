package kr.or.ddit.vo;

import java.util.Arrays;

/**
 * let data = {
		p1:['text', 'text'],
		p2:[23,34],
		p3:'o2'
	}
 *
 */
public class TestVO {
	private String[] p1;
	private Integer[] p2;
	private String p3;
	
	public String[] getP1() {
		return p1;
	}
	public void setP1(String[] p1) {
		this.p1 = p1;
	}
	public Integer[] getP2() {
		return p2;
	}
	public void setP2(Integer[] p2) {
		this.p2 = p2;
	}
	public String getP3() {
		return p3;
	}
	public void setP3(String p3) {
		this.p3 = p3;
	}
	@Override
	public String toString() {
		return "TestVO [p1=" + Arrays.toString(p1) + ", p2=" + Arrays.toString(p2) + ", p3=" + p3 + "]";
	}
	
	
}
