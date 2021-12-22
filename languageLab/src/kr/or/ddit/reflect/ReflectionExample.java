package kr.or.ddit.reflect;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionExample {
	
	public static void main(String[] args) {
		TestVO target = new TestVO();
		target.setProp1("텍스트");
		target.setProp2(23);
		
		String json = writeValueAsString(target);
		System.out.println(json);
		
	}
	
	public static String writeValueAsString(Object target) {
		
		// marshalling to json
				String propPattern = "\"%s\":%s,";
				StringBuffer json = new StringBuffer("{") ;  
					
				Class targetType =  target.getClass();
				Field[] fields =  targetType.getDeclaredFields();
				for(Field fld : fields) {
					String propertyName = fld.getName();
					try {
						PropertyDescriptor pd = new PropertyDescriptor(propertyName, targetType);
						Method getter =  pd.getReadMethod();
						Object propertyValue = getter.invoke(target);
						Class propertyType = propertyValue.getClass();
						String propValueToJson = null;
						
						if(propertyType.equals(Integer.class)) {
							propValueToJson = propertyValue.toString();
						}
						else {
							propValueToJson = "\""+propertyValue.toString()+"\"";
						}
						
						json.append(String.format(propPattern, propertyName, propValueToJson));
						
					} catch (IntrospectionException e) {
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
					} // try end
					
				} // for end
				
				
				int lastIdx =  json.lastIndexOf(",");
				if(lastIdx != -1) {
					json.deleteCharAt(lastIdx);
				}
				
				json.append("}");
				
				
				System.out.println(json);
				
		
				return json.toString();
		
	}
	
	
	
	public static class TestVO{
		private String prop1;
		private Integer prop2;
		
		
		public String getProp1() {
			return prop1;
		}
		public void setProp1(String prop1) {
			this.prop1 = prop1;
		}
		public Integer getProp2() {
			return prop2;
		}
		public void setProp2(Integer prop2) {
			this.prop2 = prop2;
		}
		
		
		@Override
		public String toString() {
			return "TestVO [prop1=" + prop1 + ", prop2=" + prop2 + "]";
		}
		
		
		
		
	}
}
