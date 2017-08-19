package com.za.tools;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.za.tools.heteronym.PinyinLibraryCategory;
import com.za.tools.heteronym.model.PinyinLibrary;
import com.za.tools.heteronym.model.PinyinLibraryItem;
import com.za.tools.namesepration.CompoundSurnameLibrary;

public class ResourceHelper {
	private static String DEFAULT_PINYIN_PATH = "db/pinyin.txt";
	private static String DEFAULT_NAME_PATH = "db/name.txt";
	private static Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	
	/**
	 * 加载拼音字库
	 * @param is
	 * @param charset
	 * @return
	 */
	public static List<PinyinLibrary> loadPinyinLibrary(InputStream is, Charset charset) {
		if (null == is) {
			is = ResourceHelper.class.getResourceAsStream(DEFAULT_PINYIN_PATH);
		}
		if (null == charset) {
			charset = DEFAULT_CHARSET;
		}
		String content = null;
		try {
			byte b[] = new byte[1024];
			int len =0;
			int temp =0;
			while ((temp = is.read())!= -1) {
				b[len] = (byte)temp;
				len++;
			}
			is.close();
			content = new String(b, 0, len);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return transfer2PinyinContent(content);
	}
	
	/**
	 * 加载拼音字库
	 * @param classpath
	 * @param charset
	 * @return
	 */
	public static List<PinyinLibrary> loadPinyinClasspathLibrary(String classpath, Charset charset) {
		if (null == classpath) {
			classpath = DEFAULT_PINYIN_PATH;
		}
		if (null == charset) {
			charset = DEFAULT_CHARSET;
		}
		String content = null;
		try {
			InputStream is = ResourceHelper.class.getClassLoader().getResourceAsStream(classpath);
			byte b[] = new byte[1024];
			int len =0;
			int temp =0;
			while ((temp = is.read())!= -1) {
				b[len] = (byte)temp;
				len++;
			}
			is.close();
			content = new String(b, 0, len);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return transfer2PinyinContent(content);
	}
	
	private static String CAT_START_SYMBOL = "[";
	private static String ITEM_DEL_SYMBOL = ":";
	private static String ITEM_SEPARATOR = "/";
	private static List<PinyinLibrary> transfer2PinyinContent(String content) {
		List<PinyinLibrary> libraries = new ArrayList<PinyinLibrary>();
		String lineSeparator = System.getProperty("line.separator");
		
		String[] libs = content.split(lineSeparator);
		List<Integer> lines = new ArrayList<Integer>();
		for (int i = 0; i < libs.length; i++) {
			String str = libs[i];
			if (str.startsWith(CAT_START_SYMBOL)) {
				lines.add(i);
			}
		}
		lines.add(libs.length);
		
		//第一行是类别，在下个类别之前是数据
		for (int i = 0; i < lines.size() - 1; i++) {
			int start = lines.get(i);
			int end = lines.get(i + 1);
			libraries.add(convert(libs, start, end));
		}
		
		return libraries;
	}
	
	private static PinyinLibrary convert(String[] libs, int startLine, int endLine) {
		PinyinLibrary library = new PinyinLibrary();
		
		String catStr = libs[startLine].replace("[", "").replace("]", "").toUpperCase().trim();
		PinyinLibraryCategory category = PinyinLibraryCategory.valueOf(catStr);
		library.setCategory(category);
		
		List<PinyinLibraryItem> items = new ArrayList<PinyinLibraryItem>();
		for (int i = startLine + 1; i < endLine; i++) {
			PinyinLibraryItem item = new PinyinLibraryItem();
			String[] itemConts = libs[i].split(ITEM_DEL_SYMBOL);
			item.setPinyin(itemConts[0]);
			
			List<String> characters = Arrays.asList(itemConts[1].split(ITEM_SEPARATOR));
			item.setCharacters(characters);
			
			items.add(item);
		}
		
		library.setItems(items);
		return library;
	}
	
	/******************** 复姓 **************/
	
	/**
	 * 加载复姓字库
	 * @param is
	 * @param charset
	 * @return
	 */
	public static List<CompoundSurnameLibrary> loadNameLibrary(InputStream is, Charset charset) {
		if (null == is) {
			is = ResourceHelper.class.getResourceAsStream(DEFAULT_NAME_PATH);
		}
		if (null == charset) {
			charset = DEFAULT_CHARSET;
		}
		String content = null;
		try {
			byte b[] = new byte[1024];
			int len =0;
			int temp =0;
			while ((temp = is.read())!= -1) {
				b[len] = (byte)temp;
				len++;
			}
			is.close();
			content = new String(b, 0, len);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return transfer2NameContent(content);
	}
	
	/**
	 * 加载复姓字库
	 * @param classpath
	 * @param charset
	 * @return
	 */
	public static List<CompoundSurnameLibrary> loadNameClasspathLibrary(String classpath, Charset charset) {
		if (null == classpath) {
			classpath = DEFAULT_NAME_PATH;
		}
		if (null == charset) {
			charset = DEFAULT_CHARSET;
		}
		String content = null;
		try {
			InputStream is = ResourceHelper.class.getClassLoader().getResourceAsStream(classpath);
			byte b[] = new byte[1024];
			int len =0;
			int temp =0;
			while ((temp = is.read())!= -1) {
				b[len] = (byte)temp;
				len++;
			}
			is.close();
			content = new String(b, 0, len);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return transfer2NameContent(content);
	}
	
	private static List<CompoundSurnameLibrary> transfer2NameContent(String content) {
		List<CompoundSurnameLibrary> libraries = new ArrayList<CompoundSurnameLibrary>();
		String lineSeparator = System.getProperty("line.separator");
		
		String[] libs = content.split(lineSeparator);
		for (int i = 1; i < libs.length; i++) {
			CompoundSurnameLibrary lib = new CompoundSurnameLibrary();
			lib.setCompoundSurname(libs[i].trim());
			libraries.add(lib);
		}
		
		return libraries;
	}
	
}
