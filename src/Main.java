import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long minor = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();

        List<String> recruit = persons.stream()
                .filter(x -> x.getSex() == Sex.MAN)
                .filter(x -> x.getAge() >= 18 && x.getAge() < 27)
                .map(x -> x.getFamily())
                .collect(Collectors.toList());

        List<Person> workers = persons.stream()
                .filter(x -> x.getEducation() == Education.HIGHER)
                .filter(x -> x.getAge() >= 18)
                .filter(x -> (x.getAge() < 60 && x.getSex() == Sex.WOMAN) ||
                        (x.getAge() < 65 && x.getSex() == Sex.MAN))
                .sorted(Comparator.comparing(x -> x.getFamily()))
                .collect(Collectors.toList());

    }
}