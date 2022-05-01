public class PowerBlock
{
    private String name;
    private int powerUsage;

    public PowerBlock(String name, int powerUsage) {
        this.name = name;
        this.powerUsage = powerUsage;
    }

    @Override
    public String toString() {
        return "PowerBlock{" +
                "name=" + name +
                ", powerUsage=" + powerUsage +
                '}';
    }
}
