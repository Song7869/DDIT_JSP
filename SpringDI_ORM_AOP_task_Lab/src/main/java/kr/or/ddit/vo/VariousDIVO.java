package kr.or.ddit.vo;

import java.io.File;
import java.util.Date;

import kr.or.ddit.sample.service.SampleService;

public class VariousDIVO {
	private boolean bool;
	private int number;
	private char ch;
	private String str;
	
	private Date date;
	private File res;
	
	private SampleService service;
	
	public VariousDIVO(boolean bool, int number, char ch, String str, Date date, File res) {
		super();
		this.bool = bool;
		this.number = number;
		this.ch = ch;
		this.str = str;
		this.date = date;
		this.res = res;
	}

	public VariousDIVO() {
	}

	public boolean isBool() {
		return bool;
	}

	public void setBool(boolean bool) {
		this.bool = bool;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public char getCh() {
		return ch;
	}

	public void setCh(char ch) {
		this.ch = ch;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public File getRes() {
		return res;
	}

	public void setRes(File res) {
		this.res = res;
	}

	public SampleService getService() {
		return service;
	}

	public void setService(SampleService service) {
		this.service = service;
	}

	@Override
	public String toString() {
		return "VariousDIVO [bool=" + bool + ", number=" + number + ", ch=" + ch + ", str=" + str + ", date=" + date
				+ ", res=" + res + "]";
	}
}
