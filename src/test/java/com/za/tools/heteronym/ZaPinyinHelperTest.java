package com.za.tools.heteronym;

import com.github.stuxuhai.jpinyin.PinyinFormat;

import junit.framework.TestCase;

public class ZaPinyinHelperTest extends TestCase {

	public void testConvertToPinyinString() {
		try {
			String s1 = ZaPinyinHelper.convertToPinyinString("李娜", "", PinyinFormat.WITHOUT_TONE);
			System.out.println("s1:" + s1);
			
			String s2 = ZaPinyinHelper.convertToPinyinString("李娜", "", PinyinFormat.WITHOUT_TONE, PinyinLibraryCategory.NAME);
			System.out.println("s2:" + s2);
			
			String s3 = ZaPinyinHelper.convertToPinyinString("婀娜", "", PinyinFormat.WITHOUT_TONE, PinyinLibraryCategory.WORD);
			System.out.println("s3:" + s3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
