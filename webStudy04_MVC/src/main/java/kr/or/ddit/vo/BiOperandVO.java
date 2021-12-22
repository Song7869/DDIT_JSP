package kr.or.ddit.vo;

import kr.or.ddit.enumpkg.OperatorType;

public class BiOperandVO {
	private Double leftOp;
	private Double rightOp;
	private OperatorType operator;
	
	public String getExpression() {
		return operator.makeExpression(leftOp, rightOp);
	}
	
	public Double getLeftOp() {
		return leftOp;
	}
	public void setLeftOp(Double leftOp) {
		this.leftOp = leftOp;
	}
	public Double getRightOp() {
		return rightOp;
	}
	public void setRightOp(Double rightOp) {
		this.rightOp = rightOp;
	}
	public OperatorType getOperator() {
		return operator;
	}
	public void setOperator(OperatorType operator) {
		this.operator = operator;
	}
	
	@Override
	public String toString() {
		return "BiOperandVO [leftOp=" + leftOp + ", rightOp=" + rightOp + ", operator=" + operator + ", expression="
				+  "]";
	}
}
