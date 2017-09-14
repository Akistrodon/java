package com.ngm01.grouplanguages.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ngm01.grouplanguages.models.Language;

@Service
public class LanguageService {
	List<Language> languages = new ArrayList(Arrays.asList(
			new Language("Java", "James Gosling", "1.8")
			));
	
	public List<Language> all(){
		return languages;
	}
	
	public void addLanguage(Language language) {
		languages.add(language);
	}
	
	public Language getLanguage(int id) {
		if(id < languages.size()) {
			return languages.get(id);
		}
		else {
			return null;
		}
	}
	
	public void updateLanguage(int id, Language language) {
		if (id < languages.size()) {
			languages.set(id, language);
		}
	}
	
	public void deleteLanguage(int id) {
		if (id < languages.size()) {
			languages.remove(id);
		}
	}
}
