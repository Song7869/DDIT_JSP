package kr.or.ddit.vo;

public class EmployeeWrapper implements FancyTreeNode {
	
	private EmployeeVO employee;
	
	public EmployeeWrapper(EmployeeVO employee) {
		this.employee = employee;
	}

	@Override
	public String getTitle() {
		return employee.getFirstName()+" "+employee.getLastName();
	}

	@Override
	public String getKey() {
		return employee.getEmployeeId().toString();
	}

	@Override
	public boolean isFolder() {
		return employee.getChildCount() > 0;
	}
	
	@Override
	public boolean isLazy() {
		return isFolder();
	}

}











