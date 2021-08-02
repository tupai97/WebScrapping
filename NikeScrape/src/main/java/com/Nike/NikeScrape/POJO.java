package com.Nike.NikeScrape;

public class POJO {
	
	private String ShoeName;
    private String ShoeType;
    private int ShoePrice;
    private String linkName;
	public String getShoeName() {
		return ShoeName;
	}
	public void setShoeName(String shoeName) {
		ShoeName = shoeName;
	}
	public String getShoeType() {
		return ShoeType;
	}
	public void setShoeType(String shoeType) {
		ShoeType = shoeType;
	}
	public int getShoePrice() {
		return ShoePrice;
	}
	public void setShoePrice(int shoePrice) {
		ShoePrice = shoePrice;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
}
