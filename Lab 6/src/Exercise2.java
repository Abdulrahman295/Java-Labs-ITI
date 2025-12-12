//import java.util.Comparator;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.function.BiConsumer;
//import java.util.function.Function;
//import java.util.stream.Collector;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static java.util.stream.Collectors.*;
//
//
//public class Exercise2 {
//    public static Optional<City> getMaxCityInContinent(String continent){
//        if(continent == null){
//            return Optional.empty();
//        }
//
//        CountryDao countryDao = InMemoryWorldDao.getInstance();
//
//        return countryDao.findAllCountries().stream()
//                .filter(country -> country.getContinent().equals(continent))
//                .flatMap(country -> country.getCities().stream())
//                .max(Comparator.comparingInt(City::getPopulation));
//    }
//
//    public static void main(String[] args) {
//        CountryDao countryDao = InMemoryWorldDao.getInstance();
//
//        Comparator<City> populationComparator = Comparator.comparingInt(City::getPopulation);
//
//        Function<Country, Stream<City>> getCitiesStream = country -> country.getCities().stream();
//
//        Map<String, Optional<City>> largestCitiesMap = countryDao.findAllCountries().stream()
//                .collect(groupingBy(Country::getContinent, flatMapping(getCitiesStream, maxBy(populationComparator))));
//
//        largestCitiesMap.values().stream().filter(Optional::isPresent).map(Optional::get).forEach(city -> System.out.println(city.getName()));
//        List<City> largestCitiesInContinents = countryDao.getAllContinents().stream()
//                .map(Exercise2::getMaxCityInContinent)
//                .filter(Optional::isPresent)
//                .map(Optional::get)
//                .toList();
//
//        largestCitiesInContinents.forEach(city -> System.out.println(city.getName()));
//
//    }
//
//}

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;


public class Exercise2 {
    public static void main(String[] args) {
        CountryDao countryDao = InMemoryWorldDao.getInstance();

        Comparator<City> populationComparator = Comparator.comparingInt(City::getPopulation);

        Function<Country, Stream<City>> getCitiesStreamFromCountry = country -> country.getCities().stream();

        Map<String, Optional<City>> largestCitiesMap = countryDao.findAllCountries().stream()
                .collect(groupingBy(
                        Country::getContinent,
                        flatMapping(getCitiesStreamFromCountry, maxBy(populationComparator))
                        )
                );

        largestCitiesMap.values().stream()
                .flatMap(Optional::stream)
                .forEach(city -> System.out.println(city.getName()));

    }

}

