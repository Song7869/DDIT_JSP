package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import kr.or.ddit.mvc.fileupload.MultipartFile;
import kr.or.ddit.validate.constraints.FileMimeChecker;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 상품관리용 Domain Layer
 *
 */
@Data
@EqualsAndHashCode(of="prodId")
public class ProdVO implements Serializable{
	private int rnum;
	@NotBlank(groups=UpdateGroup.class)
	private String prodId;
	@NotBlank
	private String prodName;
	@NotBlank
	private String prodLgu;
	private String lprodNm;
	@NotBlank
	private String prodBuyer;
	private String buyerName;
	@NotNull
	@Min(0)
	private Integer prodCost;
	@NotNull
	@Min(0)
	private Integer prodPrice;
	@NotNull
	@Min(0)
	private Integer prodSale;
	@NotBlank
	private String prodOutline;
	private String prodDetail;
	
	@NotNull(groups=InsertGroup.class)
	@FileMimeChecker(mime="image")
	private MultipartFile prodImage;
	public void setProdImage(MultipartFile prodImage) {
		if(prodImage.isEmpty()) return;
		this.prodImage = prodImage;
	}
	
	@NotBlank(groups=InsertGroup.class)
	private String prodImg;
	@NotNull
	@Min(0)
	private Integer prodTotalstock;
	@Pattern(regexp="\\d{4}-\\d{2}-\\d{2}")
	private String prodInsdate;
	@NotNull
	@Min(0)
	private Integer prodProperstock;
	private String prodSize;
	private String prodColor;
	private String prodDelivery;
	private String prodUnit;
	private Integer prodQtyin;
	private Integer prodQtysale;
	private Integer prodMileage;
	
	private BuyerVO buyer; // has a 관계(1:1)
	
	private List<MemberVO> memberList; // has many 관계(1:N)
	
	private Integer memCount;
}













