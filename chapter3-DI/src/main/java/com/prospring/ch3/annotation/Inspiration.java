package com.prospring.ch3.annotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Inspiration {
	private String lyric = "Toniiiiight...";
	
	public Inspiration(@Value("Is going be the niiight") String lyric) {
		this.lyric = lyric;
	}
	
	public String getLyric() {
		return lyric;
	}
	
	public void setLyric(String lyric) {
		this.lyric=lyric;
	}
}
