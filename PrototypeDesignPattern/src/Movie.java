public class Movie implements PrototypeConcept
{
    private String movieName = null;
    private float rating = 0;

    public Movie(String movieName, float rating) {
        this.movieName = movieName;
        this.rating = rating;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public Movie clone() throws CloneNotSupportedException {
        System.out.println("Cloning a movie type object...");
        return (Movie) super.clone();
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieName='" + movieName + '\'' +
                ", rating=" + rating +
                '}';
    }
}
