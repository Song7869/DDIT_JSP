package kr.or.ddit.util;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ReflectionUtils {
	private static String rootPath;
	static {
		URL rootURL = ReflectionUtils.class.getClassLoader().getResource("");
		File rootFolder = new File(rootURL.getFile());
		rootPath = rootFolder.getAbsolutePath();
	}
	
	/**
	 * 클래스 내의 필드 중 특정 어노테이션이 선언된 필드 수집
	 * @param targetClz
	 * @param annotationType
	 * @return
	 */
	public static <T extends Annotation> Map<Field, T> getFieldsWithAnnotationWithClass(Class<?> targetClz, Class<T> annotationType){
		Map<Field, T> result = new LinkedHashMap<>();
		List<Field> fields = getFieldsAtClass(targetClz);
		for(Field fld : fields) {
			T annotation = fld.getAnnotation(annotationType);
			if(annotation!=null) {
				result.put(fld, annotation);
			}
		}
		return result;
	}
	
	/**
	 * 클래스 내의 모든 필드 수집
	 * @param targetClz
	 * @return
	 */
	public static List<Field> getFieldsAtClass(Class<?> targetClz){
		Field[] fields = targetClz.getDeclaredFields();
		return Arrays.asList(fields);
	}
	
	/**
	 * 클래스 내의 메소드 중 특정 어노테이션으로 선언된 메소드 수집
	 * @param <T>
	 * @param clz 스캔 대상 클래스
	 * @param annotationType 스캔할 어노테이션 타입
	 * @param returnType 리턴 타입
	 * @param parameterTypes 파라미터 타입
	 * @return key:Method, value:어노테이션객체  엔트리로 구성된 Map
	 */
	public static <T extends Annotation> Map<Method,T> getMethodsWithAnnotationAtClass(Class<?> targetClz, Class<T> annotationType, 
			Class<?> returnType, Class<?>...parameterTypes){
		Map<Method, T> result = new LinkedHashMap<>();
		List<Method> methods = getMethodsAtClass(targetClz, returnType, parameterTypes);
		for(Method mtd : methods) {
			T annotation = mtd.getAnnotation(annotationType);
			if(annotation!=null) {
				result.put(mtd, annotation);
			}
		}
		return result;
	}
	
	/**
	 * 클래스내에서 조건(파라미터타입, 리턴타입)에 맞는 메소드 수집
	 * @param targetClz 시캔 대상 클래스
	 * @param returnType 리턴 타입
	 * @param parameterTypes 파라미터 타입
	 * @return
	 */
	public static List<Method> getMethodsAtClass(Class<?> targetClz, Class<?> returnType, Class<?>...parameterTypes) {
		List<Method> methodList = new ArrayList<>();
		methodList.addAll(Arrays.asList(targetClz.getDeclaredMethods()));
		for(int i=methodList.size()-1; i>=0; i--){
			Method temp = methodList.get(i);
			boolean matched = parameterTypes==null || parameterTypes.length == 0 ? true : Arrays.deepEquals(temp.getParameterTypes(), parameterTypes);
			if(!matched || !Objects.equals(temp.getReturnType(), returnType)) {
				methodList.remove(i);
			}
		}
		return methodList;
	}
	
	/**
	 * 여러 패키지내의 클래스중 특정 어노테이션이 선언된 클래스 수집
	 * @param annotationType 스캔할 어노테이션
	 * @param basePackages 스캔 대상 패키지들의 qualified name 배열
	 * @return key:Class, value:어노테이션객체  엔트리로 구성된 Map
	 */
	public static <T extends Annotation> Map<Class<?>, T> getClassesWithAnnotationAtBasePackages(Class<T> annotationType, String...basePackages) {
		Map<Class<?>, T> result = new LinkedHashMap<>();
		if(basePackages==null) return result;
		for(String pkgName : basePackages){
			result.putAll(getClassesWithAnnotation(annotationType, pkgName));
		}
		return result;
	}
	
	/**
	 * 여러 패키지 내의 클래스들을 모두 수집
	 * @param basePackages 스캔 대상이 되는 패키지의 qualified name 배열
	 * @return
	 */
	public static List<Class<?>> getAllClassesAtBasePackages(String...basePackages){
		List<Class<?>> classList = new ArrayList<>();
		if(basePackages==null) return classList;
		for(String pkgName : basePackages){
			classList.addAll(scan(pkgName));
		}
		return classList;
	}
	
	private static <T extends Annotation> Map<Class<?>, T> getClassesWithAnnotation(Class<T> annotationType, String pkgName) {
		Map<Class<?>, T> result = new LinkedHashMap<>();
		List<Class<?>> classList = scan(pkgName);
		for(Class<?> tmp : classList) {
			T annotation = tmp.getAnnotation(annotationType);
			if(annotation!=null) {
				result.put(tmp, annotation);
			}
		}
		return result;
	}
	
	private static List<Class<?>> scan(String pkgName) {
		try {
			File[] fileArray = getFileList(pkgName);
			List<Class<?>> classList = getClasses(fileArray);
			return classList;
		}catch (Exception e) {
			throw new CustomReflectException(e);
		}
	}

	private static List<Class<?>> getClasses(File[] fileArray){
		List<Class<?>> classList = new ArrayList<>();
		for(File tmp : fileArray) {
			String qualified = getQulifiedName(tmp);
			try {
				classList.add( Class.forName(qualified) );
			} catch (ClassNotFoundException e) {
				System.err.println(e.getMessage());
				continue;
			}
		}
		return classList;
	}

	private static String getQulifiedName(File classFile) {
		String abPath = classFile.getAbsolutePath();
		abPath = abPath.substring(rootPath.length());
		if(abPath.indexOf(File.separatorChar)==0) { 
			abPath = abPath.substring(1);
		}
		int endIndex = abPath.lastIndexOf(".class");
		abPath = abPath.substring(0, endIndex);
		return abPath.replace(File.separatorChar, '.');
	}

	private static File[] getFileList(String pkgName) throws Exception {
		URL folderURL = ReflectionUtils.class.getClassLoader().getResource(pkgName.trim().replace('.', '/'));
		if(folderURL==null)
			throw new CustomReflectException(String.format("[%s] 패키지가 존재하지 않습니다.", pkgName));
		URI folderURI = folderURL.toURI();
		Path folderPath = Paths.get(folderURI);
		List<File> fileList = new ArrayList<File>();
		Files.walkFileTree(folderPath, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				if(file.toString().endsWith(".class")) {
					fileList.add(file.toFile());
				}
				return super.visitFile(file, attrs);
			}
		});
		File[] array = new File[fileList.size()];
		return fileList.toArray(array);
	}
	
	@SuppressWarnings("serial")
	public static class CustomReflectException extends RuntimeException{
		public CustomReflectException() {
			super();
		}

		public CustomReflectException(String message, Throwable cause, boolean enableSuppression,
				boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
		}

		public CustomReflectException(String message, Throwable cause) {
			super(message, cause);
		}

		public CustomReflectException(String message) {
			super(message);
		}

		public CustomReflectException(Throwable cause) {
			super(cause);
		}
	}
}
