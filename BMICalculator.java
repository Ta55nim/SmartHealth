public class BMICalculator extends HealthCalculator {
    private double height;
    private double weight;

    public BMICalculator(double height, double weight) {
        this.height = height;
        this.weight = weight;
    }

    @Override
    public double calculate() {
        return weight / (height * height);
    }
}
