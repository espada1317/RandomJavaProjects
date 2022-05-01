import java.util.HashMap;
import java.util.Map;

public class PrototypeRegistry
{
    public static class ModelType
    {
        public static final String MOVIE = "Movie";
        public static final String BOOK = "BOOK";
        public static final String SPECTACLE = "Spectacle";
    }

    private static Map<String, PrototypeConcept> prototypes = new HashMap<String, PrototypeConcept>();

    static
    {
        prototypes.put(ModelType.MOVIE, new Movie("The good, the Bad and the Ugly", 9.1f));
        prototypes.put(ModelType.SPECTACLE, new TheatreSpectacle());
        prototypes.put(ModelType.BOOK, new Book());
    }

    public static PrototypeConcept getInstance(final String typePrototype) throws CloneNotSupportedException
    {
        return prototypes.get(typePrototype).clone();
    }
}
