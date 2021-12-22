package kr.or.ddit.vo;

import java.util.List;

public interface FancyTreeNode {
	public String getTitle();
	public String getKey();
	public boolean isFolder();
	public boolean isLazy();
	default public Object getData() {
		return null;
	}
	default public List<FancyTreeNode> getChildren(){
		return null;
	}
}
