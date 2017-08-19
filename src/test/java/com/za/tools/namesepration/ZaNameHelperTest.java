package com.za.tools.namesepration;

import junit.framework.TestCase;

public class ZaNameHelperTest extends TestCase {

	public void testSepratorNameString() {
		try {
			String[] n1 = ZaNameHelper.sepratorName("司马已");
			System.out.println("********** n1 **********");
			System.out.println(n1[0]);
			System.out.println(n1[1]);
			
			
			String[] n2 = ZaNameHelper.sepratorName("司已");
			System.out.println("********** n2 **********");
			System.out.println(n2[0]);
			System.out.println(n2[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public void testSepratorNameInputStreamCharsetString() {
//		fail("Not yet implemented");
//	}
//
//	public void testSepratorNameStringCharsetString() {
//		fail("Not yet implemented");
//	}

}
