package com.prospring.ch3.annotated;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("nonSingleton")
@Scope("prototype")
public class Song {
	private String name =  "unknown";
	
		public Song(@Value("Bohemian Raphsody") String name) {
			this.name = name;
		}
}
