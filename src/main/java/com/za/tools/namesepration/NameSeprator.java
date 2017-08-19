package com.za.tools.namesepration;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

import com.za.tools.ResourceHelper;

public class NameSeprator {
	private static NameSeprator instance;
	private static List<CompoundSurnameLibrary> libraries;
	public static NameSeprator newInstance() {
		if (null == instance) {
			instance = new NameSeprator();
		}
		if (null == libraries) {
			libraries = ResourceHelper.loadNameLibrary(null, null);
		}
		return instance;
	}
	public static NameSeprator newInstance(InputStream is, Charset charset) {
		if (null == instance) {
			instance = new NameSeprator();
		}
		libraries = ResourceHelper.loadNameLibrary(is, charset);
		return instance;
	}
	public static NameSeprator newInstance(String classpath, Charset charset) {
		if (null == instance) {
			instance = new NameSeprator();
		}
		libraries = ResourceHelper.loadNameClasspathLibrary(classpath, charset);
		return instance;
	}
	
	public String[] separate(String name) throws Exception {
		String[] n = new String[2];
		boolean match = false;
		for (CompoundSurnameLibrary lib : libraries) {
			if (name.startsWith(lib.getCompoundSurname())) {
				n[0] = lib.getCompoundSurname();
				n[1] = name.substring(lib.getCompoundSurname().length());
				match = true;
				break;
			}
		}
		if (!match) {
			this.normal(n, name);
		}
		return n;
	}
	
	private void normal(String[] n, String name) throws Exception {
		n[0] = name.substring(0, 1);
		n[1] = name.substring(1);
	} 
}
