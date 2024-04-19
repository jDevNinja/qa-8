package streams;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class VerifeidUsersPredicate implements Predicate<Account> {
  @Override
  public boolean test(Account account) {
    return account.isVerified();
  }
}

public class Main {

  public static void main(String[] args) {
    // Предикат в виде класса
    VerifeidUsersPredicate verifeidUsersPredicate = new VerifeidUsersPredicate();

    // Предикат в виде анонимного класса
    Predicate<Account> mailStartsWithS =
      new Predicate<Account>() {
        @Override
        public boolean test(Account account) {
          return account.getEmail().startsWith("m");
        }
      };

    // Предикат в виде лямбды
    Predicate<Account> ageGte25Predicate = account -> account.age > 25;

    List<Account> allAccounts = DataGenerator.makeAccountsList();

    // 1. Простая фильтрация: отобрать только подтвержденные аккаунты
    List<Account> verifiedAccounts =
        allAccounts.stream()
            .filter(
                new Predicate<Account>() {
                  @Override
                  public boolean test(Account account) {
                    return account.isVerified();
                  }
                })
            .collect(Collectors.toList());

    verifiedAccounts =
        allAccounts.stream().filter(Account::isVerified).collect(Collectors.toList());

    // 2. Простой маппинг: получить список всех почт
    Set<String> accountEmails =
        allAccounts.stream()
            .map(
                new Function<Account, String>() {
                  @Override
                  public String apply(Account account) {
                    return account.getEmail();
                  }
                })
            .collect(Collectors.toSet());

    accountEmails = allAccounts.stream().map(Account::getEmail).collect(Collectors.toSet());

    // 3. Преобразование в Map: преобразовать список в HashMap<mail,Account>
    Map<String, Account> emailToAccount =
        allAccounts.stream()
            .collect(
                Collectors.toMap(
                    new Function<Account, String>() {
                      @Override
                      public String apply(Account account) {
                        return account.getEmail();
                      }
                    },
                    account -> account));

    emailToAccount =
        allAccounts.stream()
            .collect(
                Collectors.toMap(Account::getEmail, account -> account, (first, second) -> first));

    // 4. Группировка элементов по значению: сгруппировать пользователей по возрасту
    Map<Integer, List<Account>> ageToUsers =
        allAccounts.stream().collect(Collectors.groupingBy(Account::getAge));

    // 5. Группировка по значению с маппингом:
    //    - получить список почт подтвержденных/неподтвержденных аккаунтов
    Map<Boolean, Set<String>> verificationStatusToEmailList =
        allAccounts.stream()
            .collect(
                Collectors.groupingBy(
                    new Function<Account, Boolean>() {
                      @Override
                      public Boolean apply(Account account) {
                        return account.isVerified();
                      }
                    },
                    Collectors.mapping(
                        new Function<Account, String>() {
                          @Override
                          public String apply(Account account) {
                            return account.getEmail();
                          }
                        },
                        Collectors.toSet())));

    verificationStatusToEmailList =
        allAccounts.stream()
            .collect(
                Collectors.groupingBy(
                    Account::isVerified,
                    Collectors.mapping(Account::getEmail, Collectors.toSet())));

    //    - посчитать количество пользователей с подтвержденными/неподтвержденными аккаунтами
    Map<Boolean, Long> verificationStatusToCount =
        allAccounts.stream()
            .collect(Collectors.groupingBy(Account::isVerified, Collectors.counting()));

    // 6. Преобразование списка списков в список:
    //    - получить один список из списка пользователей сгруппированных по возрасту
    List<List<Account>> accountsGroupedByAge = DataGenerator.makeAccountLists();

    List<Account> allUsers =
        accountsGroupedByAge.stream()
            .flatMap(
                new Function<List<Account>, Stream<Account>>() {
                  @Override
                  public Stream<Account> apply(List<Account> accounts) {
                    return accounts.stream();
                  }
                })
            .collect(Collectors.toList());

    allUsers = accountsGroupedByAge.stream().flatMap(Collection::stream).toList();

    // 7. Ни peek, ни map не работают, если для результата не нужно обходить все элементы:
    long count = allUsers.stream().peek(System.out::println).count();

    count =
        allUsers.stream()
            .map(
                account -> {
                  System.out.println(account);
                  return account;
                })
            .count();
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

  public static List<List<Account>> makeAccountLists() {
    return List.of(
        List.of(
            new Account(22, "alice@gmail.com", false), new Account(22, "oscar@gmail.com", true)),
        List.of(new Account(33, "jack@gmail.com", false), new Account(33, "lucy@gmail.com", false)),
        List.of(new Account(44, "mike@gmail.com", false)));
  }
}
