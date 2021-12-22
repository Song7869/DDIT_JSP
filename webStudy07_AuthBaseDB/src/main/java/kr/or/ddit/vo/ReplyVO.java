package kr.or.ddit.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="repNo")
public class ReplyVO implements Serializable{
	private int rnum;
	@NotNull(groups=UpdateGroup.class)
	private Integer repNo;
	private Integer boNo;
	private String repContent;
	private String displayContent;
	@NotBlank
	private String repWriter;
	private String repMail;
	@NotBlank
	private String repPass;
	private String repDate;
	private Integer repParent;
}
