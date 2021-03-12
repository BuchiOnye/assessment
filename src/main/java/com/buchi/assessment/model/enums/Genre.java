package com.buchi.assessment.model.enums;

public enum Genre {
	ROMANCE, CLASSICS, ACTION, ADVENTURE;

	public static Genre findGenreByName(String name) {
		for(Genre genre: Genre.values()) {
			if(genre.name().equalsIgnoreCase(name)) {
				return genre;
			}
		}
		
		return null;
	}
}
