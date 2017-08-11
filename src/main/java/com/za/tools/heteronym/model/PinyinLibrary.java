package com.za.tools.heteronym.model;

import java.util.List;

import com.za.tools.heteronym.PinyinLibraryCategory;

public class PinyinLibrary {

	private PinyinLibraryCategory category;
	private List<PinyinLibraryItem> items;
	public PinyinLibraryCategory getCategory() {
		return category;
	}
	public void setCategory(PinyinLibraryCategory category) {
		this.category = category;
	}
	public List<PinyinLibraryItem> getItems() {
		return items;
	}
	public void setItems(List<PinyinLibraryItem> items) {
		this.items = items;
	}
	
	
}
