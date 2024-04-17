package streams;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    List<Account> userAccounts = DataGenerator.makeAccounts();
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
