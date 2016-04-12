public class CalculateDiscount
        extends fit.ColumnFixture {
    public double amount;

    private Discount application = new Discount();

    public double discount() {
        return application.getDiscount(amount);
    }
}
