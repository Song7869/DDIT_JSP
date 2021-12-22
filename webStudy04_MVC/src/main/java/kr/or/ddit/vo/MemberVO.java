package kr.or.ddit.vo;

import java.io.IOException;
import java.io.Serializable;
import java.util.Set;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.apache.commons.codec.binary.Base64;

import kr.or.ddit.mvc.fileupload.MultipartFile;
import kr.or.ddit.validate.constraints.FileMimeChecker;
import kr.or.ddit.validate.constraints.TelNumber;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * ValueObject(DataTransferObject, Model, Bean)
 * JavaBean 규약
 * 1. 값을 가질수 있는 property 정의
 * 2. property encapsulation
 * 3. 캡슐화된 property에 접근할 수 있는 인터페이스 제공(getter/setter)
 * 4. property 의 상태를 비교할 수 있는 방법 제공.
 * 5. 정렬의 기준을 제공
 * 6. property의 상태 확인 방법 제공
 * 7. 직렬화 가능 객체로 선언.
 * 
 * Data Mapper(MyBatis)를 이용한 다중 테이블 조인
 * 1. 메인 데이터를 기준으로 사용할 테이블 간의 관계 파악(MEMBER, PROD - 1:N)
 * 2. 각 테이블로부터 데이터 바인딩하기 위한 VO 정의(MemberVO, ProdVO).
 * 3. 테이블간의 관계성을 자바 객체 구조에 반영
 *    1:N -> has many , ex) MemberVO has many ProdVO (List, Set)
 *    1:1 -> has a , ex) ProdVO has a BuyerVO
 * 4. resultType 대신 resultMap 을 이용한 수동 바인딩 설정.
 * 	  1:N -> collection 으로 바인딩
 *    1:1 -> association 으로 바인딩  (strict alias 를 활용 가능) 
 * 
 * 
 * 
 */
@Data
@ToString(exclude= {"memRegno1", "memRegno2", "memPass"})
@EqualsAndHashCode(of= {"memId"})
@NoArgsConstructor
public class MemberVO implements Serializable, HttpSessionBindingListener {
	
	public MemberVO(String memId, String memPass) {
		super();
		this.memId = memId;
		this.memPass = memPass;
	}
	@NotBlank(message="아이디 누락", groups= {DeleteGroup.class, Default.class})
	private String memId;
	@NotBlank(groups= {DeleteGroup.class, Default.class})
	private String memPass;
	@NotBlank
	private String memName;
	@NotBlank(groups=InsertGroup.class)
	@Size(min=6, max=6, groups=InsertGroup.class)
	private String memRegno1;
	@NotBlank(groups=InsertGroup.class)
	@Size(min=7, max=7, groups=InsertGroup.class)
	private String memRegno2;
	@Pattern(regexp="\\d{4}-\\d{2}-\\d{2}")
	private String memBir;
	@NotBlank
	private String memZip;
	@NotBlank
	private String memAdd1;
	@NotBlank
	private String memAdd2;
	
	@NotBlank
	@TelNumber
	private String memHometel;
	@NotBlank
	@TelNumber
	private String memComtel;
	@TelNumber
	private String memHp;
	
	@NotBlank
	@Email
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	@Pattern(regexp="\\d{4}-\\d{2}-\\d{2}")
	private String memMemorialday;
//	@NotNull
//	@Min(0)
	private Integer memMileage;
	private String memDelete;
	
	private Set<ProdVO> prodList; // 구매 상품 목록, has many 관계(1:N)
	
	private String memRole;
	
	private byte[] memImg;
	
	@FileMimeChecker(mime="image")
	private MultipartFile memImage;
	public void setMemImage(MultipartFile memImage) throws IOException {
		if(memImage==null || memImage.isEmpty()) return;
		this.memImage = memImage;
		memImg = memImage.getBytes();
	}
	
	public String getBase64Image() {
		if(memImg==null) return null;
		else
			return Base64.encodeBase64String(memImg);
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		String attrName = event.getName();
		if("authMember".equals(attrName)) {
			Set<MemberVO> userList = 
					(Set) event.getSession().getServletContext().getAttribute("userList");
			userList.add(this);
		}
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		String attrName = event.getName();
		if("authMember".equals(attrName)) {
			Set<MemberVO> userList = 
					(Set) event.getSession().getServletContext().getAttribute("userList");
			userList.remove(this);
		}
	}
}


























