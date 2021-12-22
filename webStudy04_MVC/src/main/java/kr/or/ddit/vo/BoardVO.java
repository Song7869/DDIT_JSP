package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.mvc.fileupload.MultipartFile;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="boNo")
@ToString(exclude= {"replyList", "attatchList"})
public class BoardVO implements Serializable{
	int rnum;
	@NotNull(groups=UpdateGroup.class)
	private Integer boNo;
	@NotBlank
	private String boTitle;
	@NotBlank
	private String boWriter;
	@NotBlank
	private String boIp;
	@Email
	private String boMail;
	@NotBlank
	private String boPass;
	private String boContent;
	private String boDate;
	private Integer boRep;
	private Integer boHit;
	private Integer boRec;
	private Integer boParent;
	
	private List<ReplyVO> replyList;
	
	private MultipartFile[] boFiles;
	public void setBoFiles(MultipartFile[] boFiles) {
		if(boFiles==null) return;
		this.boFiles = boFiles;
		this.attatchList = new ArrayList<>();
		for(MultipartFile tmp : boFiles) {
			if(tmp.isEmpty()) continue;
			attatchList.add(new AttatchVO(tmp));
		}
	}
	
	private List<AttatchVO> attatchList;
	
	private int startAttNo;

	private int[] delAttNos;
	
	private int repCnt;
	
	private int atchCnt;
	
}
