import java.util.*;
import java.util.stream.Collectors;

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

        long underAge = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Количество несовршеннолетних: " + underAge);

        //Список фамилий призовников
        List<String> recruits = persons.stream()
                .filter(person -> person.getAge() > 18)
                .filter(person -> person.getAge() < 27)
                .map(person -> person.getFamily())
                .collect(Collectors.toList());

        //Список работоспособных женщин
        List<Person> potentiallyWorkableWoman = persons.stream()
                .filter(person -> person.getAge() > 18)
                .filter(person -> person.getAge() < 60)
                .filter(person -> person.getSex().equals(Sex.WOMAN))
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .sorted(Comparator.comparing(person -> person.getFamily()))
                .collect(Collectors.toList());

        //Список работоспособных мужчин
        List<Person> potentiallyWorkableMen = persons.stream()
                .filter(person -> person.getAge() > 18)
                .filter(person -> person.getAge() < 65)
                .filter(person -> person.getSex().equals(Sex.MAN))
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .sorted(Comparator.comparing(person -> person.getFamily()))
                .collect(Collectors.toList());
    }
}