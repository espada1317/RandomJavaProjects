public class Book implements PrototypeConcept
{
    private String bookName = null;
    private float rating = 0;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public Book clone() throws CloneNotSupportedException {
        System.out.println("Cloning a movie type object...");
        return (Book) super.clone();
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", rating=" + rating +
                '}';
    }
}
