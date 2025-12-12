import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;

import static java.lang.System.out;

public class Exercise5 {

    public static void main(String[] args) {
        CountryDao countryDao = InMemoryWorldDao.getInstance();
        CityDao cityDao = InMemoryWorldDao.getInstance();

        Comparator<City> populationComparator = Comparator.comparingInt(City::getPopulation);

        Function<Country, Optional<City>> getCapitalCityFromCountry
                = country -> Optional.ofNullable(cityDao.findCityById(country.getCapital()));

        Optional<City> cityOptional = countryDao.findAllCountries().stream()
                .map(getCapitalCityFromCountry)
                .flatMap(Optional::stream)
                .max(populationComparator);

        cityOptional.ifPresent(city -> out.println(city.getName()));

    }

}