import javax.inject.*;
import play.*;
import play.filters.cors.CORSFilter;
import play.mvc.EssentialFilter;
import play.http.HttpFilters;

@Singleton
public class Filters implements HttpFilters {

    private final Environment env;

    @Inject
    CORSFilter corsFilter;

    @Inject
    public Filters(Environment env) {
        this.env = env;
    }

    @Override
    public EssentialFilter[] filters() {
        if (env.mode().equals(Mode.DEV)) {
            return new EssentialFilter[] { corsFilter.asJava() };
        } else {
            return new EssentialFilter[]{};
        }
    }

}
