

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Exercise1 {

   public static void main(String[] args) {
      CountryDao countryDao= InMemoryWorldDao.getInstance();

      Comparator<City> populationComparator = Comparator.comparingInt(City::getPopulation);

       Function<Country, Optional<City>> getLargestCityInCountry
               = country -> country.getCities().stream().max(populationComparator);

        List<City> largestCities = countryDao.findAllCountries().stream()
                .map(getLargestCityInCountry)
                .flatMap(Optional::stream)
                .toList();

        largestCities.forEach(city -> System.out.println(city.getName()));
   }

}