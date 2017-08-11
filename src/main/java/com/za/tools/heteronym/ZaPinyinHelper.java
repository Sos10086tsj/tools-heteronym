package com.za.tools.heteronym;

import java.io.InputStream;
import java.nio.charset.Charset;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;

public class ZaPinyinHelper {

	/**
	 * 获取拼音
	 * @param str	汉字
	 * @return
	 * @throws Exception
	 */
	public static String convertToPinyinString(String str) throws Exception{
		return convertToPinyinString(str, "", PinyinFormat.WITHOUT_TONE);
	}
	
	/**
	 * 获取拼音
	 * @param str			汉字
	 * @param categories	命中类型
	 * @return
	 * @throws Exception
	 */
	public static String convertToPinyinString(String str, PinyinLibraryCategory... categories) throws Exception{
		return convertToPinyinString(str, "", PinyinFormat.WITHOUT_TONE, categories);
	}
	
	public static String convertToPinyinString(InputStream is,String str) throws Exception{
		return convertToPinyinString(is, null, str, "", PinyinFormat.WITHOUT_TONE);
	}
	
	public static String convertToPinyinString(InputStream is,String str, PinyinLibraryCategory... categories) throws Exception{
		return convertToPinyinString(is, null, str, "", PinyinFormat.WITHOUT_TONE, categories);
	}
	
	public static String convertToPinyinString(String resourcePath,String str) throws Exception{
		return convertToPinyinString(resourcePath, null, str, "", PinyinFormat.WITHOUT_TONE);
	}
	
	public static String convertToPinyinString(String resourcePath,String str, PinyinLibraryCategory... categories) throws Exception{
		return convertToPinyinString(resourcePath, null, str, "", PinyinFormat.WITHOUT_TONE,categories);
	}
	/**
	 * 获取拼音字符串
	 * 
	 * @param str
	 * @param separator
	 * @param pinyinFormat
	 * @return
	 * @throws PinyinException
	 */
	public static String convertToPinyinString(String str, String separator, PinyinFormat pinyinFormat)
			throws Exception {
		return PinyinInstance.newInstance().getPinyin(str, separator, pinyinFormat);
	}
	
	public static String convertToPinyinString(String str, String separator, PinyinFormat pinyinFormat, PinyinLibraryCategory... categories)
			throws Exception {
		return PinyinInstance.newInstance().getPinyin(str, separator, pinyinFormat, categories);
	}
	
	public static String convertToPinyinString(InputStream is, Charset charset, String str, String separator, PinyinFormat pinyinFormat)
			throws Exception {
		return PinyinInstance.newInstance(is, charset).getPinyin(str, separator, pinyinFormat);
	}
	
	public static String convertToPinyinString(InputStream is, Charset charset, String str, String separator, PinyinFormat pinyinFormat, PinyinLibraryCategory... categories)
			throws Exception {
		return PinyinInstance.newInstance(is, charset).getPinyin(str, separator, pinyinFormat, categories);
	}
	
	public static String convertToPinyinString(String resourcePath, Charset charset, String str, String separator, PinyinFormat pinyinFormat)
			throws Exception {
		return PinyinInstance.newInstance(resourcePath, charset).getPinyin(str, separator, pinyinFormat);
	}
	
	public static String convertToPinyinString(String resourcePath, Charset charset, String str, String separator, PinyinFormat pinyinFormat, PinyinLibraryCategory... categories)
			throws Exception {
		return PinyinInstance.newInstance(resourcePath, charset).getPinyin(str, separator, pinyinFormat, categories);
	}
}
