package streams;

public class Account {

  int age;
  private String email;
  private boolean isVerified;

  public Account(int age, String email, boolean isVerified) {
    this.age = age;
    this.email = email;
    this.isVerified = isVerified;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isVerified() {
    return isVerified;
  }

  public void setVerified(boolean verified) {
    isVerified = verified;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "Account{"
        + "age="
        + age
        + ", email='"
        + email
        + '\''
        + ", isVerified="
        + isVerified
        + '}';
  }
}
