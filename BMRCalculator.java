public class BMRCalculator extends HealthCalculator {
    private User user;

    public BMRCalculator(User user) {
        this.user = user;
    }

    @Override
    public double calculate() {
        if (user.getGender().equalsIgnoreCase("male")) {
            return 10 * user.getWeight() + 6.25 * user.getHeight() * 100 - 5 * user.getAge() + 5;
        } else {
            return 10 * user.getWeight() + 6.25 * user.getHeight() * 100 - 5 * user.getAge() - 161;
        }
    }
}
