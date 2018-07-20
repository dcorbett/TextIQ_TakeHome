import com.textiq.resource.DocumentProcessResource;
import com.textiq.resource.DocumentResource;
import com.textiq.service.*;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

public class Challenge extends Application<ChallengeConfiguration> {
    @Override
    public void run(ChallengeConfiguration conf, Environment env) {
        DBIFactory dbiFactory = new DBIFactory();
        DBI dbi = dbiFactory.build(env, conf.database, "mysql");

        //Services
        DocumentService documentService = new DocumentImpl(dbi);

        //Resources
        env.jersey().register(new TestResource(dbi));
        env.jersey().register(new DocumentResource(documentService));
        env.jersey().register(new DocumentProcessResource(documentService));
    }

    public static void main(String[] args) throws Exception {
        new Challenge().run(args);
    }
}
