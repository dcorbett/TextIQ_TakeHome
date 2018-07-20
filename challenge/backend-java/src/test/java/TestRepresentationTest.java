import org.junit.Assert;
import org.junit.Test;

public class TestRepresentationTest {

    @Test
    public void testDataRetrieval() {
        TestRepresentation tr = new TestRepresentation();
        tr.backend = "java";
        tr.result = "ok";
        Assert.assertEquals(tr.backend, "java");
        Assert.assertEquals(tr.result, "ok");
    }
}
