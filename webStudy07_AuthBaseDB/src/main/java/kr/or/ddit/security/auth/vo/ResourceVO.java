package kr.or.ddit.security.auth.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="resourceId")
public class ResourceVO {
	private Integer level;
	private String resourceId;
	private String resourceName; 
	private String resourceType; 
	private String resourcePattern; 
	private String httpMethod; 
	private String description; 
	private String resourceParent; 
	private Integer sortOrder; 
	private String createDate; 
	private String modifyDate; 
	
	@JsonIgnore
	private List<AuthorityVO> authorities;
}
