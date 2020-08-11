package org.cloud.hikvision.common.annotation;

import java.lang.reflect.Field;

/**
 * 注解处理器
 * @author fzz
 *
 */
public class FruiInfoUtil {

	public static void getFruitInfo(Class<?> clazz) {
		String strFruitName = " 水果名称：";
		String strFruitColor = " 水果颜色：";
		String strFruitProvicer = " 供应商信息：";
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields) {
			if(field.isAnnotationPresent(FruitName.class)){
				FruitName fruitName = field.getAnnotation(FruitName.class);
				strFruitName = strFruitName + fruitName.value();
				System.out.println(strFruitName);
			}else if(field.isAnnotationPresent(FruitColor.class)) {
				FruitColor fruitColor = field.getAnnotation(FruitColor.class);
				strFruitColor = strFruitColor + fruitColor.fruitColor().toString();
				System.out.println(strFruitColor);
			}else if(field.isAnnotationPresent(FruitProcider.class)){
				FruitProcider p = field.getAnnotation(FruitProcider.class);
				strFruitProvicer = "供应商编号："+p.id() + " 供应商名称："+p.name() +" 供应商地址："+p.address();
				System.out.println(strFruitProvicer);
			}
		}
	}
}


















