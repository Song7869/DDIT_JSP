package kr.or.ddit.vo;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor // 기본생성자
@Setter // 세터
@AllArgsConstructor // 생성자
@ToString
@Getter
public class CollectionDIVO {
	private List<Date> dateList;
	private Set<Object> sampleSet;
	private Map<Object, Object> sampleMap;
	private Properties sampleProps;
	
	private String[] array;
}
