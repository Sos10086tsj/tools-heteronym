package com.za.tools.heteronym;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.za.tools.heteronym.model.PinyinLibrary;
import com.za.tools.heteronym.model.PinyinLibraryItem;

public class PinyinInstance {

	private static PinyinInstance instance;
	private static List<PinyinLibrary> libraries;

	public static PinyinInstance getInstance() {
		return instance;
	}

	public static void setInstance(PinyinInstance instance) {
		PinyinInstance.instance = instance;
	}
	
	public static List<PinyinLibrary> getLibraries() {
		return libraries;
	}

	public static void setLibraries(List<PinyinLibrary> libraries) {
		PinyinInstance.libraries = libraries;
	}

	public static PinyinInstance newInstance() {
		if (null == instance) {
			instance = new PinyinInstance();
		}
		if (null == libraries) {
			libraries = ResourceHelper.loadLibrary(null, null);
		}
		return instance;
	}
	
	public static PinyinInstance newInstance(InputStream is, Charset charset) {
		instance = new PinyinInstance();
		libraries = ResourceHelper.loadLibrary(is, charset);
		return instance;
	}
	
	public static PinyinInstance newInstance(String classpath, Charset charset) {
		instance = new PinyinInstance();
		libraries = ResourceHelper.loadClasspathLibrary(classpath, charset);
		return instance;
	}
	
	public String getPinyin(String str, String separator, PinyinFormat format) throws Exception {
		return getPinyin(str,separator, format, PinyinLibraryCategory.WORD);
	}
	
	/**
	 * 是否命中字体库 如果命中，返回拼音；如果未命中，返回null
	 * @param str
	 * @param categories
	 * @return
	 * @throws Exception 
	 */
	public String getPinyin(String str, String separator, PinyinFormat format, PinyinLibraryCategory... categories) throws Exception {
		boolean hasWord = false;
		for (PinyinLibraryCategory category : categories) {
			if (category.equals(PinyinLibraryCategory.WORD)) {
				hasWord = true;
				break;
			}
		}
		for (PinyinLibraryCategory category : categories) {
			String pinyin = hitLibrary(str, category, separator, format);
			if (null != pinyin) {
				return pinyin;
			}
		}
		if (!hasWord) {
			return hitLibrary(str, PinyinLibraryCategory.WORD, separator, format);
		}
		return null;
	}
	
	/**
	 * 命中字体库返回拼音，未命中返回null
	 * @param str
	 * @param category
	 * @return
	 * @throws Exception 
	 */
	private String hitLibrary(String str, PinyinLibraryCategory category, String separator, PinyinFormat format) throws Exception {
		String pinyin = null;
		for (PinyinLibrary library : libraries) {
			if (!library.getCategory().equals(category)) {
				continue;
			}
			switch (category) {
			case WORD://全词匹配
				pinyin = handerFullMatch(library, str, separator, format);
				break;
			default:
				pinyin = handerPartyMatch(library, str, separator, format);
				break;
			}
		}
		return pinyin;
	}
	
	/**
	 * 全词匹配
	 * @param library
	 * @param str
	 * @return
	 */
	private String handerFullMatch(PinyinLibrary library, String str, String separator, PinyinFormat format) throws Exception{
		for (PinyinLibraryItem lib : library.getItems()) {
			if (lib.getCharacters().contains(str)) {
				return lib.getPinyin();
			}
		}
		return PinyinHelper.convertToPinyinString(str, separator, format).toUpperCase();
	}
	
	private String handerPartyMatch(PinyinLibrary library, String str, String separator, PinyinFormat format) throws Exception {
		int length = str.length();
		String[] pinyin = new String[length];
		for (int i = 0; i < length; i++) {
			String s = String.valueOf(str.charAt(i));
			for (PinyinLibraryItem lib : library.getItems()) {
				if (lib.getCharacters().contains(s)) {
					pinyin[i] = lib.getPinyin();
				}
			}
		}
		for (int i = 0; i < length; i++) {
			if (StringUtils.isEmpty(pinyin[i])) {
				pinyin[i] = PinyinHelper.convertToPinyinString(String.valueOf(str.charAt(i)), separator, format).toUpperCase();
			}
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			builder.append(pinyin[i]);
		}
		return builder.toString();
	}
}
