package practice;

import java.util.Scanner;
import java.util.Set;

public class Main {
    public static final PhoneBook phoneBook = new PhoneBook();
    public static Scanner scanner = new Scanner(System.in);
    public static String info = "Имя с большой буквы: Маша. \n" +
            "Номер в форматах: 79123456789/89123456789. \n" +
            "Завершение работы программы: STOP. \n" +
            "Вывод списка контактов: LIST. \n";

    public static void main(String[] args) {
        while (true) {
            System.out.println("Введите имя, номер или команду. Для получения информации по командам напишите ИНФО.");
            String input = scanner.nextLine();
            if (input.equals("LIST")) {
                Set<String> allContact = phoneBook.getAllContacts();
                System.out.println(allContact);
                break;
            } else if (input.equals("ИНФО")) {
                System.out.println(info);
            } else if (input.equals("STOP")) {
                System.out.println("Завершение программы.");
                break;
            } else if (phoneBook.checkName(input)) {
                String phone = phoneBook.getPhone(input);
                if (!phone.equals("")) {
                    System.out.println(input + " - " + phone);
                } else {
                    phone = addPhoneInput();
                    phoneBook.addContact(phone, input);
                    System.out.println(phoneBook.showMessage);
                }
            } else if (phoneBook.checkNumber(input)) {
                String name = phoneBook.getName(input);
                if (!name.equals("")) {
                    System.out.println(name + " - " + input);
                } else {
                    name = addNameInput();
                    phoneBook.addContact(input, name);
                    System.out.println(phoneBook.showMessage);
                }

            }
        }

    }
//метод получения телефона
    public static String addPhoneInput() {
        while (true) {
            System.out.println("введите номер телефона");
            String phone = scanner.nextLine();
            if (phoneBook.checkNumber(phone)) {
                return phone;
            }
        }
    }
//метод получения имени
    public static String addNameInput() {
        while (true) {
            System.out.println("введите имя");
            String name = scanner.nextLine();
            if (phoneBook.checkName(name)) {
                return name;
            }
        }
    }
}

