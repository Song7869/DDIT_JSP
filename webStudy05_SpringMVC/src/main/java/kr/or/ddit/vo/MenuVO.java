package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class MenuVO implements Serializable{
	private String menuURL;
	private String menutext;
	private String menuColor;
	private String menuId;
	private String menuClass;
}
