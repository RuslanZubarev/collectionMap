import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import practice.PhoneBook;

/**
 * Test for homework Phonebook app
 */

@DisplayName("Программа для хранения списка телефонных номеров")
class PhoneBookTest {

    private PhoneBook phoneBook;

    @BeforeEach
    public void setUp() {
        phoneBook = new PhoneBook();
    }

    @Test
    @DisplayName("Передан неверный формат телефонного номера")
    void addNotValidName() {
        phoneBook.addContact("79001234567", "79001234567");
        assertEquals(Collections.emptySet(), phoneBook.getAllContacts());
    }

    @Test
    @DisplayName("Передано пустая строка в Имени абонента")
    void addEmptyPhone() {
        phoneBook.addContact("79991234567", "");
        assertEquals(Collections.emptySet(), phoneBook.getAllContacts());
    }

    @Test
    @DisplayName("Передано пустая строка в телефоне абонента")
    void addEmptyName() {
        phoneBook.addContact("", "Маша");
        assertEquals(Collections.emptySet(), phoneBook.getAllContacts());
    }

    @Test
    @DisplayName("Передан неверный формат телефонного номера")
    void addNotValidPhone() {
        phoneBook.addContact("Маша", "Маша");
        assertEquals(Collections.emptySet(), phoneBook.getAllContacts());
    }

    @Test
    @DisplayName("Добавление контактов с одинаковым номером, владелец должен быть перезаписан")
    void addAndRewriteContact() {
        phoneBook.addContact("79001234567", "Маша");
        phoneBook.addContact("79001234567", "Миша");
        Set<String> expected = Set.of("Миша - 79001234567");
        assertEquals(expected, phoneBook.getAllContacts());
    }

    @Test
    @DisplayName("Корректное добавление контакта")
    void addingContactByName() {
        phoneBook.addContact("79001234567", "Маша");
        phoneBook.addContact("79991234567", "Миша");
        Set<String> expected = Set.of("Маша - 79001234567", "Миша - 79991234567");
        assertEquals(expected, phoneBook.getAllContacts());
    }

    @Test
    @DisplayName("Добавление контакта, владеющий двумя номерами")
    void addContactWithTwoNumbers() {
        phoneBook.addContact("79001234567", "Маша");
        phoneBook.addContact("79007654321", "Маша");
        Set<String> expected = new TreeSet<>(List.of("Маша - 79001234567, 79007654321"));
        assertEquals(expected, phoneBook.getAllContacts());
    }

    @Test
    @DisplayName("Поиск контакта по имени")
    void searchContactByName() {
        phoneBook.addContact("79001234567", "Маша");
        phoneBook.addContact("79991234567", "Миша");
        Set<String> expected = Set.of("Маша - 79001234567");
        assertEquals(expected, phoneBook.getContactByName("Маша"));
    }

    @Test
    @DisplayName("Поиск контакта по номеру")
    void searchContactByNumber() {
        phoneBook.addContact("79001234567", "Маша");
        phoneBook.addContact("79991234567", "Миша");
        assertEquals("Маша - 79001234567", phoneBook.getContactByPhone("79001234567"));
    }
}
