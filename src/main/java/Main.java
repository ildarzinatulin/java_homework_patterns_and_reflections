import java.util.Arrays;
import java.util.List;

public class Main {
    static class Person {
        private String firstName;
        private String lastName;
        private Address address = new Address();
        private List<String> phoneNumbers = Arrays.asList("One", "Two", "Three");;
        private int age = 5;
        private Integer parentsAge;
    }
    static class Address {
        private String city = "Moscow";
        private String postalCode = "245234";
    }

    public static void main(String[] args) throws IllegalAccessException {
        Person person = new Person();

        SerializerHelper jsonSerializerHelper = new JsonSerializerHelper();
        ClassSerializer jsonSerializer = new ClassSerializer(jsonSerializerHelper);
        System.out.println(jsonSerializer.serialize(person));

        SerializerHelper xmlSerializerHelper = new XMLSerializerHelper();
        ClassSerializer xmlSerializer = new ClassSerializer(xmlSerializerHelper);
        System.out.println(xmlSerializer.serialize(person));
    }
}
