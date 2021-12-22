package kr.or.ddit.vo;

import java.io.Serializable;

public class BTSVO implements Serializable{
	
	
	public BTSVO() {
		super();
	}
	
	public BTSVO(String code, String name, String page) {
		super();
		this.code = code;
		this.name = name;
		this.page = page;
	}


	private String code;
	private String name;
	private String page;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	@Override
	public String toString() {
		return "BTSVO [code=" + code + ", name=" + name + ", page=" + page + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BTSVO other = (BTSVO) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	
	
}
