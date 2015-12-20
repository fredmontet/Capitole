package mobop.capitole.domain.omdbMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import io.realm.RealmList;
import mobop.capitole.domain.model.Language;

public class LanguageJsonMapper {

    /**
     *  Small utility to create a person object
     *  with a role based on the key
     *  of the json String
     */
    public RealmList<Language> transform(String languagesStr){
        RealmList<Language> languageList = new RealmList<>();
        List<String> languageStrList = Arrays.asList(languagesStr.split(", "));
        Language language;
        for(String languageStr : languageStrList){
            language = new Language();
            language.setLanguage(languageStr);
            languageList.add(language);
        }
        return languageList;
    }

}
