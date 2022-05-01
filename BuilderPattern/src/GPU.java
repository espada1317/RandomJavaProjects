public class GPU
{
    private String name;
    private int powerConsume;

    public GPU(String name, int powerConsume) {
        this.name = name;
        this.powerConsume = powerConsume;
    }

    @Override
    public String toString() {
        return "GPU{" +
                "name=" + name +
                ", powerConsume=" + powerConsume +
                '}';
    }
}
