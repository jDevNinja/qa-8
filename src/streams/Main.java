package streams;

import java.util.List;
import java.util.Map;

public class Main {
  public static void main(String[] args) {
    List<Account> userAccounts = DataGenerator.makeAccountsList();
    // 1. Простая фильтрация: отобрать только подтвержденные аккаунты

    // 2. Простой маппинг: получить список всех почт

    // 3. Преобразование в Map: преобразовать список в HashMap<mail,Account>

    // 4. Группировка элементов по значению: сгруппировать пользователей по возрасту

    // 5. Группировка по значению с маппингом:
    //      - получить список почт подтвержденных/неподтвержденных аккаунтов
    //      - посчитать количество пользователей с подтвержденными/неподтвержденными аккаунтами

    // 6. Преобразование списка списков в список:
    //      - получить один список из списка пользователей сгруппированных по возрасту

    // peek, forEach, reduce
  }
}

class DataGenerator {

  public static List<Account> makeAccountsList() {
    return List.of(
        new Account(21, "alice@gmail.com", false),
        new Account(28, "bob@gmail.com", true),
        new Account(35, "jack@gmail.com", false),
        new Account(28, "mike@gmail.com", false),
        new Account(35, "oscar@gmail.com", true),
        new Account(21, "lucy@gmail.com", false));
  }

  public Map<Integer, List<Account>> makeAccountsMapByAge() {
    return Map.of(
        22,
        List.of(
            new Account(22, "alice@gmail.com", false), new Account(22, "oscar@gmail.com", true)),
        33,
        List.of(new Account(33, "jack@gmail.com", false), new Account(33, "lucy@gmail.com", false)),
        44,
        List.of(new Account(44, "mike@gmail.com", false)));
  }
}
