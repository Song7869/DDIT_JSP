package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class EmployeeVO implements Serializable{
	private String jobId;
	private Integer salary;
	private Integer commissionPct;
	private Integer managerId;
	private Integer departmentId;
	
	private DepartmentVO department; //HAS A
	
	private String retireDate;
	private Integer employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String hireDate;
	
	private int childCount;
}












