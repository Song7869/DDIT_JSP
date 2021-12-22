package kr.or.ddit.security.auth.vo;

import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of="authority")
@ToString(of="authority")
public class AuthorityVO implements GrantedAuthority{
	
	public AuthorityVO(String authority) {
		super();
		this.authority = authority;
	}

	@NotBlank
	private String authority; 
	private String roleName; 
	private String description;
	private String createDate; 
	private String modifyDate; 
	
	private String[] resourceId;
}
