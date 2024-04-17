package streams;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    List<Account> userAccounts = DataGenerator.makeAccounts();
    // 1. Простая фильтрация: отобрать только подтвержденные аккаунты

    // 2. Простой маппинг: получить список всех почт

    // 3. Преобразование в Map: преобразовать список в HashMap<mail,Account>

    // 4. Группировка элементов по значению: сгруппировать пользователей по возрасту

    // 5. Группировка по значению с маппингом:
    //      - получить список почт подтвержденных/неподтвержденных аккаунтов
    //      - посчитать количество пользователей с подтвержденными/неподтвержденными аккаунтами
    
    // peek, forEach, reduce
  }
}

class DataGenerator {

  public static List<Account> makeAccounts() {
    return List.of(
        new Account(25, "alice@gmail.com", false),
        new Account(20, "bob@gmail.com", true),
        new Account(35, "jack@gmail.com", false),
        new Account(28, "mike@gmail.com", false),
        new Account(35, "oscar@gmail.com", true),
        new Account(30, "lucy@gmail.com", false));
  }
}
