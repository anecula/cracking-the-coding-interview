package hackernoon.thirtydaysofcode;

enum DayTwo_Operators {

  SOLUTION;

  // Complete the solve function below.
  String buildMessage(double mealCost, int tipPercent, int taxPercent) {
    return
        "The total meal cost is " +
        Math.round(
            mealCost +
            calculatePercent(mealCost, tipPercent) +
            calculatePercent(mealCost, taxPercent)
        ) + " dollars.";
  }

  private static double calculatePercent(double mealCost, int value) {
    return mealCost * value / 100;
  }
}
