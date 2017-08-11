package com.za.tools.heteronym;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.za.tools.heteronym.model.PinyinLibrary;
import com.za.tools.heteronym.model.PinyinLibraryItem;

public class ResourceHelper {
	private static String DEFAULT_RESOURCE_PATH = "db/pinyin.txt";
	private static Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	
	public static List<PinyinLibrary> loadLibrary(InputStream is, Charset charset) {
		if (null == is) {
			is = ResourceHelper.class.getResourceAsStream(DEFAULT_RESOURCE_PATH);
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
		
		return transferContent(content);
	}
	
	public static List<PinyinLibrary> loadClasspathLibrary(String classpath, Charset charset) {
		if (null == classpath) {
			classpath = DEFAULT_RESOURCE_PATH;
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
		
		return transferContent(content);
	}
	
	private static String CAT_START_SYMBOL = "[";
	private static String ITEM_DEL_SYMBOL = ":";
	private static String ITEM_SEPARATOR = "/";
	private static List<PinyinLibrary> transferContent(String content) {
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
		
		String catStr = libs[startLine].replace("[", "").replace("]", "").toUpperCase();
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
	
//	[name]
//			na:娜
//			shan:单
//			[word]
//			lina:李娜/贺娜
//			enuo:婀娜
}
