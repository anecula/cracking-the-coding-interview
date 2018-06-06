package hackernoon.thirtydaysofcode.dayfour;

final class Person {

  private int age = 0;

  Person(int initialAge) {
    if (initialAge > 0) {
      age = initialAge;
    } else {
      System.out.println("Age is not valid, setting age to 0.");
    }
  }

  void amIOld() {
    System.out.println(
        new StringBuffer("You are ")
            .append(age < 13 ? "young" : age < 18 ? "a teenager" : "old")
            .append(".")
            .toString()
    );
  }

  void yearPasses() {
    age++;
  }
}