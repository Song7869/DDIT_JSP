package kr.or.ddit.generic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class GenericTest {
	
	//Object 로 전부 받는 방법
	public static class Box {
	    private Object object;

	    public void set(Object object) { this.object = object; }
	    public Object get() { return object; }
	}
	
	
	//제너릭으로 받는 방법
	public static class Box2<T, E>{
		private T object2;
		
		public void set(T object2) { this.object2 = object2; }
		public T get() { return object2; }

		
		private List<E> contents = new ArrayList<>(); //다이아몬드 문법 : 앞의 타입이 정해지면 뒤에가 자동으로 정해짐(뒤에 지워도 됨)
		public void add(E element) {
			contents.add(element);
		}
		public E getFromList(int index) {
			return contents.get(index);
		}
		
		
		//public static T prop; //static에는 제너릭을 사용할 수 없다.
		// 제너릭은 객체를 사용할 때 타입이 정해지지만, static은 객체 사용전에 메모리에 올리고 사용하기 때문에 사용이 불가능하다.
		
		
		public static <P> void method(P param) {
			// static이라서 타입을 받을 수 없을 때 <> 를 사용해서 받아준다.
			System.out.println(param.getClass());// String, Int 둘다 나옴
			// 메서드를 호출할 때 입력한 타입이 타입이 된다.
		}
		
		
		// 처음 컴파일 될 때에는 Object로 컴파일 될거야
		// 어퍼바운드 를 사용해서 상위의 타입을 제한 할 수 있다. <C extends Collection>
		public static <C extends Collection> int estimateSize(C param) {
			return param.size();
			
		}
	}
	
	
	
	public static void main(String[] args) {
		
		//Object
		Box box = new Box();
		box.set(new Date());
		Date date = (Date)box.get();
		Object obj = box.get();
		if(obj instanceof Date) {
			//instanceof : 특정 개체의 타입을 확인 할 때
			date = (Date) obj;
		}
		
		
		
		//제너릭
		Box2<Date, Calendar> box2 = new Box2<>(); // T 자리에 Date를 넣어주는 순간 Date 타입 박스가 된다.
		box2.set(new Date());
		Date date2 = box2.get(); // Date로 뽑아도 문제 없음(캐스팅 필요치 않음)
		
		box2.add(Calendar.getInstance());
		Calendar cal = box2.getFromList(0);
		
		
		
		Box2.method("text");
		box2.method(4);
		// 무엇을 넣어도 에러가 나지 않아.
		
		
		//Box2.estimateSize(new Date()); // 컬렉션이 아니라서 에러가 난다.
		Box2.estimateSize(new ArrayList<>());
		Box2.estimateSize(new HashSet<>());
		//Box2.estimateSize(new HashMap<>());
	}
}




















