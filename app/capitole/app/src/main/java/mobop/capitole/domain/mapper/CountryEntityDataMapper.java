package mobop.capitole.domain.mapper;

import mobop.capitole.domain.model.Country;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Mapper class used to transform {@link CountryEntity} (in the data layer) to {@link Country} in the
 * domain layer.
 */
public class CountryEntityDataMapper {

    public CountryEntityDataMapper() {}

    /**
     * Transform a {@link CountryEntity} into an {@link Country}.
     *
     * @param countryEntity Object to be transformed.
     * @return {@link Country} if valid {@link CountryEntity} otherwise null.
     */
    public Country transform(CountryEntity countryEntity) {
        Country country = null;
        if (countryEntity != null) {
            country = new Country();
            country.setCountry(countryEntity.getCountry());
        }
        return country;
    }

    /**
     * Transform a Collection of {@link CountryEntity} into a Collection of {@link Country}.
     *
     * @param countryEntityCollection Object Collection to be transformed.
     * @return {@link Country} if valid {@link CountryEntity} otherwise null.
     */
    public Collection<Country> transform(Collection<CountryEntity> countryEntityCollection) {
        Collection<Country> countryCollection = new ArrayList<>();
        Country country;
        for (CountryEntity countryEntity : countryEntityCollection) {
            country = transform(countryEntity);
            if (country != null) {
                countryCollection.add(country);
            }
        }

        return countryCollection;
    }
}
