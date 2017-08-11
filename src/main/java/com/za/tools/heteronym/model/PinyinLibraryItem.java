package com.za.tools.heteronym.model;

import java.util.List;

public class PinyinLibraryItem {

	private String pinyin;
	private List<String> characters;
	
	public String getPinyin() {
		return pinyin.toUpperCase();
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public List<String> getCharacters() {
		return characters;
	}
	public void setCharacters(List<String> characters) {
		this.characters = characters;
	}
	
	
}
