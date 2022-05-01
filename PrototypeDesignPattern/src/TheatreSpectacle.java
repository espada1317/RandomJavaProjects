public class TheatreSpectacle implements PrototypeConcept
{
    private String spectacleName = null;
    private float rating = 0;

    public String getSpectacleName() {
        return spectacleName;
    }

    public void setSpectacleName(String spectacleName) {
        this.spectacleName = spectacleName;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public TheatreSpectacle clone() throws CloneNotSupportedException {
        System.out.println("Cloning a Theatre spectacle type object...");
        return (TheatreSpectacle) super.clone();
    }

    @Override
    public String toString() {
        return "TheatreSpectacle{" +
                "spectacleName='" + spectacleName + '\'' +
                ", rating=" + rating +
                '}';
    }
}