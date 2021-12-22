package kr.or.ddit.reflection;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import kr.or.ddit.reflect.ReflectionTest;

/**
 * Reflection
 * : 인스턴스를 통해 해당 인스턴스의 타입, 속성, 행동에 대한 정보를 추측하는 과정. (java.lang.reflect)
 * @author pc12
 *
 */
public class ReflectionDesc {
	public static void main(String[] args) {
		
		Object obj = ReflectionTest.getObject();
		System.out.println(obj);
		
		//타입 찾기
		Class<? extends Object> type = obj.getClass();
		System.out.println(type.getName());
		
		//속성 찾기
		Field[] fields =  type.getDeclaredFields();
		for(Field fld : fields) {
			String name = fld.getName();
			Class fldType = fld.getType();
			String getterName = "get"+name.substring(0,1).toUpperCase()+name.substring(1);
			
			try {
				Method getter = type.getMethod(getterName);
				Object propertyValue = getter.invoke(obj);
				System.out.printf("%s %s = %s;\n", fldType.getSimpleName(), name, propertyValue );
				
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			
		}
		
		//행동 찾기
		Method[] methods = type.getMethods();
		for(Method mtd: methods) {
			System.out.println(mtd.getName());
		}
		
		Field[] fields2 = type.getDeclaredFields();
		for(Field fld : fields2) {
			String name = fld.getName();
			try {
				PropertyDescriptor pd = new PropertyDescriptor(name, type);
				
				Class propertyType = pd.getPropertyType();
				Method getter =  pd.getReadMethod();
				Object propertyValue = getter.invoke(obj);
				
				System.out.printf("%s %s = %s;\n", propertyType.getSimpleName(), name, propertyValue );
			} catch (IntrospectionException e) {
				// 자바bean 규약을 어긴 Vo일 경우 발생되는 에러
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}
