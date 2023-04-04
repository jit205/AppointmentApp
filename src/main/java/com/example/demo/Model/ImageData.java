package com.example.demo.Model;

import java.util.Arrays;

public class ImageData {
	
    private String name;
    private String contentType;
    @Override
	public String toString() {
		return "ImageData [name=" + name + ", contentType=" + contentType;
	}
	private byte[] data;

    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	
}
