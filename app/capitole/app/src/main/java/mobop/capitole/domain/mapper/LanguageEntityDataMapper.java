package mobop.capitole.data.entity.mapper;

import mobop.capitole.data.entity.LanguageEntity;
import mobop.capitole.domain.Language;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link LanguageEntity} (in the data layer) to {@link Language} in the
 * domain layer.
 */
@Singleton
public class LanguageEntityDataMapper {

    @Inject
    public LanguageEntityDataMapper() {}

    /**
     * Transform a {@link LanguageEntity} into an {@link Language}.
     *
     * @param languageEntity Object to be transformed.
     * @return {@link Language} if valid {@link LanguageEntity} otherwise null.
     */
    public Language transform(LanguageEntity languageEntity) {
        Language language = null;
        if (languageEntity != null) {
            language = new Language();
            language.setLanguage(languageEntity.getLanguage());
        }
        return language;
    }

    /**
     * Transform a Collection of {@link LanguageEntity} into a Collection of {@link Language}.
     *
     * @param languageEntityCollection Object Collection to be transformed.
     * @return {@link Language} if valid {@link LanguageEntity} otherwise null.
     */
    public Collection<Language> transform(Collection<LanguageEntity> languageEntityCollection) {
        Collection<Language> languageCollection = new ArrayList<>();
        Language language;
        for (LanguageEntity languageEntity : languageEntityCollection) {
            language = transform(languageEntity);
            if (language != null) {
                languageCollection.add(language);
            }
        }

        return languageCollection;
    }
}
