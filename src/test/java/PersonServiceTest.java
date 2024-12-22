package tp;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonServiceTest {

    @Test
    public void testFilterByAddress() {
        String addressToFilter = "123 Rue A";

        List<Person> result = PersonService.filterByAddress(addressToFilter);

        assertThat(result).hasSize(2);
        assertThat(result)
                .extracting(Person::getFirstName)
                .containsExactlyInAnyOrder("Ahlam", "Karim");
    }

    @Test
    public void testFilterAdults() {
        // When
        List<Person> result = PersonService.filterAdults();

        // Then
        assertThat(result).hasSize(2); // Deux adultes : Ahlam (33 ans) et Karim (38 ans)
        assertThat(result)
                .extracting(Person::getFirstName)
                .containsExactlyInAnyOrder("Ahlam", "Karim");
    }

    @Test
    public void testRemoveBobWithoutIterator() {
        Set<Person> result = PersonService.removeBobWithoutIterator();

        assertThat(result).hasSize(2); // Une personne a été supprimée
        assertThat(result)
                .extracting(Person::getFamilyName)
                .doesNotContain("Kenzi");
    }

    @Test
    public void testRemoveBobUsingIterator() {
        Set<Person> result = PersonService.removeBobUsingIterator();

        assertThat(result).hasSize(2);
        assertThat(result)
                .extracting(Person::getFamilyName)
                .doesNotContain("Hamdi");
    }
}
