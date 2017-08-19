package com.za.tools.namesepration;

import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @Description: 姓名帮助类
 * @author Paris Tao
 * @date Aug 16, 2017 1:49:39 PM 
 */
public class ZaNameHelper {

	/**
	 * @param name 姓名
	 * @return	姓名分离后的结果
	 * @throws Exception
	 */
	public static String[] sepratorName(String name) throws Exception{
		return NameSeprator.newInstance().separate(name);
	}
	
	public static String[] sepratorName(InputStream is, Charset charset, String name) throws Exception{
		return NameSeprator.newInstance(is, charset).separate(name);
	}
	
	public static String[] sepratorName(String classpath, Charset charset, String name) throws Exception{
		return NameSeprator.newInstance(classpath, charset).separate(name);
	}
}
