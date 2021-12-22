package vo;

import java.io.Serializable;

/**
 * VO(DTO, Model, Bean)
 * Value Object
 * Data Transfer Object
 * 
 * ##JavaBean 규약##
 * 	1. 값을 가질 수 있는 property 정의
 * 	2. property encapsulation (private)
 * 	3. 캡슐화된 property에 접근 할 수 있는 인터페이스 제공(getter/setter)
 * 	4. !!! property 의 상태를 비교 할 수 있는 방법 제공 !!!
 * 	5. 정렬이 필요한 경우) 정렬의 기준을 제공 (compare to)
 * 	6. property의 상태 확인 방법 제공 (to string)
 * 	7. Serializable 직렬화 가능 객체로 선언
 * 
 * @author pc12
 *
 */
public class MemberVO implements Comparable<MemberVO>, Serializable{
	
	//====================================================
	public MemberVO() {
		super();
	}
	public MemberVO(String mem_id, String mem_pass) {
		super();
		this.mem_id = mem_id;
		this.mem_pass = mem_pass;
	}
	//====================================================

	private String mem_id;
	private transient String mem_pass;
	//transient : 직렬화에서 제외됨 (외부에 노출이 안됨)
	
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
	public String getMem_pass() {
		return mem_pass;
	}
	public void setMem_pass(String mem_pass) {
		this.mem_pass = mem_pass;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mem_id == null) ? 0 : mem_id.hashCode());
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
		MemberVO other = (MemberVO) obj;
		if (mem_id == null) {
			if (other.mem_id != null)
				return false;
		} else if (!mem_id.equals(other.mem_id))
			return false;
		return true;
	}
	
	
	@Override
	public int compareTo(MemberVO o) {
		return 0;
	}
	
	
	@Override
	public String toString() {
		return "MemberVO [mem_id=" + mem_id + "]";
	}
	
	
	
}
