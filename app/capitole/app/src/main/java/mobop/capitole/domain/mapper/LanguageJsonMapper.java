package mobop.capitole.domain.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import mobop.capitole.domain.model.Language;

public class LanguageJsonMapper {

    /**
     *  Small utility to create a person object
     *  with a role based on the key
     *  of the json String
     */
    public Collection<Language> transform(String languagesStr){
        Collection<Language> languageColl = new ArrayList<>();
        List<String> languageList = Arrays.asList(languagesStr.split(", "));
        Language language;
        for(String languageStr : languageList){
            language = new Language();
            language.setLanguage(languageStr);
            languageColl.add(language);
        }
        return languageColl;
    }

}
