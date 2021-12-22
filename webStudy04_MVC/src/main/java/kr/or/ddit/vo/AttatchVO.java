package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

import org.apache.commons.io.FileUtils;

import kr.or.ddit.mvc.fileupload.MultipartFile;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(of= {"attNo"})
@NoArgsConstructor
public class AttatchVO {
	
	private MultipartFile boFile;
	
	public AttatchVO(MultipartFile boFile) {
		super();
		this.boFile = boFile;
		this.attFilename = boFile.getOriginalFilename();
		this.attMime = boFile.getContentType();
		this.attFilesize = boFile.getSize();
		this.attFancysize = FileUtils.byteCountToDisplaySize(attFilesize);
		this.attSavename = UUID.randomUUID().toString();
	}
	
	public void saveTo(File saveFolder) throws IOException {
		if(boFile!=null) {
			boFile.transferTo(new File(saveFolder, attSavename));
		}
	}
	
	@NotBlank
	private Integer attNo;
	private Integer boNo;
	@NotBlank
	private String attFilename;
	@NotBlank
	private String attSavename;
	private String attMime;
	@NotBlank
	private Long attFilesize;
	@NotBlank
	private String attFancysize;
	private Integer attDownload;
}
