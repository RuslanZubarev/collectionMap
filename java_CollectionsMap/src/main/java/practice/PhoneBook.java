
package practice;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook {
    String regexName = "[A-ZА-ЯЁ][a-zа-яё]+";
    Pattern patterName = Pattern.compile(regexName);
    String regexNumber = "[78]9[0-9]{9}";
    Pattern patternNumber = Pattern.compile(regexNumber);
    String showMessage;


    private final Map<String, String> phoneBook = new TreeMap<>();// ключ-имя, значение-номер. Сюда складываются все номера и имена.

    public void addContact(String phone, String name) {
        String firstName = getName(phone);
        String firstPhone = getPhone(name);
        String contactToSave = name + " - " + phone;
        if (!checkNumber(phone) || !checkName(name)) {
            showMessage = "Неверный формат ввода";
        } else if (!firstName.equals(name) && !firstName.equals("")) {
            phoneBook.remove(firstName);
            phoneBook.put(name, phone);
            showMessage = contactToSave + "\r\nКонтакт изменен!";
        } else if (!firstPhone.equals(phone) && !firstPhone.equals("")) {
            phoneBook.put(name, firstPhone + ", " + phone);
            showMessage = contactToSave + "\r\nКонтакт изменен!!";
        } else {
            phoneBook.put(name, phone);
            showMessage = contactToSave + "\r\nКонтакт сохранен!";
        }
    }

    // проверьте корректность формата имени и телефона
    // (рекомендуется написать отдельные методы для проверки является строка именем/телефоном)
    // если такой номер уже есть в списке, то перезаписать имя абонента
    //Проверка имени на соответствие регулярки
    public boolean checkName(String name) {
        Matcher matcherName = patterName.matcher(name);
        return matcherName.matches();
    }

    //Проверка номера на соответствие регулярки
    public boolean checkNumber(String number) {
        Matcher matcherNumber = patternNumber.matcher(number);
        return matcherNumber.matches();
    }

    //получение имени по номеру
    public String getName(String phone) {
        for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
            if (entry.getValue().contains(phone)) {
                return entry.getKey();
            }
        }
        return "";
    }

    //получение номера по имени
    public String getPhone(String name) {
        for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
            if (entry.getKey().contains(name)) {
                return entry.getValue();
            }
        }
        return "";
    }

    public String getContactByPhone(String phone) {
        for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
            String key = entry.getKey(); // получение ключа (имя)
            if (phone.equals(entry.getValue())) {
                return key + " - " + phone;
            }
        }
        return "";
    }

    public Set<String> getContactByName(String name) {
        TreeSet<String> stringTreeSet = new TreeSet<>();
        for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
            String key = entry.getKey(); // получение ключа
            String value = entry.getValue();// получение номера
            if (name.equals(key) && stringTreeSet.isEmpty()) {
                stringTreeSet.add(name + " - " + value);
            } else if (name.equals(key)) {
                stringTreeSet.add(value);
            }
        }
        return stringTreeSet;
    }

    public Set<String> getAllContacts() {
        Set<String> allContacts = new TreeSet<>();
        for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
            String key = entry.getKey(); // получения ключа
            String value = entry.getValue();// получение значения
            allContacts.add(key + " - " + value);
        }
        return allContacts;
    }
    // формат одного контакта "Имя - Телефон"
    // если контактов нет в телефонной книге - вернуть пустой TreeSet
}
// для обхода Map используйте получение пары ключ->значение Map.Entry<String,String>
// это поможет вам найти все ключи (key) по значению (value)
    /*
          for (Map.Entry<String, String> entry : map.entrySet()){
              String key = entry.getKey(); // получения ключа
            String value = entry.getValue(); // получение значения
    */
