package com.prospring.ch10;

import org.springframework.core.convert.converter.Converter;

import com.prospring.ch10.entities.AnotherSinger;
import com.prospring.ch10.entities.Singer;
public class SingerToAnotherSingerConverter implements Converter<Singer, AnotherSinger> {
	 @Override
	 public AnotherSinger convert(Singer singer) {
		 AnotherSinger anotherSinger = new AnotherSinger();
		 anotherSinger.setFirstName(singer.getLastName()); //switched with lastname
		 anotherSinger.setLastName(singer.getFirstName()); //switched with firstname
		 anotherSinger.setBirthDate(singer.getBirthDate());
		 anotherSinger.setPersonalSite(singer.getPersonalSite());
		 return anotherSinger;
	 }
}
