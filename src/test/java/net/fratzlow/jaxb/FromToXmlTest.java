package net.fratzlow.jaxb;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Test JaxB mapping with random element names to map a Map of CQM attributes.
 * - cqm attribute name -> XML element name
 * - cqm value          -> XML value
 *
 * Null is denoted as 'nil' attribute.
 *
 * @author ratzlow@gmail.com
 * @since 2014-11-22
 */
public class FromToXmlTest {

    private final static Logger LOGGER = Logger.getLogger(FromToXmlTest.class);
    private final static int MSG_COUNT = 5000;
    private final static int THREAD_COUNT = 4;

    @Test
    public void test() throws JAXBException, InterruptedException {
        JAXBContext ctx = JAXBContext.newInstance(WebCommand.class);

        CountDownLatch latch = new CountDownLatch(MSG_COUNT);
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        long start = System.currentTimeMillis();
        for (int i=0; i < MSG_COUNT; i++) {
            executorService.submit( createTestRunner(createMarshaller(ctx), createUnmarshaller(ctx), latch) );
        }

        Assert.assertTrue("Test could not complete in time!", latch.await(7, TimeUnit.SECONDS));
        LOGGER.info(String.format("Processing %d msgs with %d threads took %d ms",
                MSG_COUNT, THREAD_COUNT, (System.currentTimeMillis() - start)));
    }


    private Marshaller createMarshaller( JAXBContext ctx ) throws JAXBException {
        Marshaller marshaller = ctx.createMarshaller();
        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);
        return marshaller;
    }

    private Unmarshaller createUnmarshaller( JAXBContext ctx ) throws JAXBException {
        return ctx.createUnmarshaller();
    }

    private Runnable createTestRunner( final Marshaller marshaller,
                                       final Unmarshaller unmarshaller,
                                       final CountDownLatch latch ) {
        return new Runnable() {
            private final Logger LOGGER = Logger.getLogger(this.getClass());

            @Override
            public void run() {
                try {
                    String invalidXml = "<invalid xml &>";
                    Map<String, String> map = new HashMap<>();
                    map.put("key1", "value1");
                    map.put("key2", "value2");
                    map.put("key3", null);
                    map.put("key4", invalidXml);
                    map.put("key5", "");
                    map.put("key6", UUID.randomUUID().toString() );

                    WebCommand cmd = new WebCommand( map );

                    StringWriter writer = new StringWriter();

                    marshaller.marshal(cmd, writer);
                    String xml = writer.toString();

                    //LOGGER.info("toXml:\n" + xml);

                    Assert.assertTrue(xml.contains("key1"));

                    WebCommand readCmd = (WebCommand) unmarshaller.unmarshal( new StringReader(xml) );
                    Map<String, String> attrs = readCmd.getCqmAttrs();
                    Assert.assertEquals( map, attrs );
                    latch.countDown();

                } catch (JAXBException e) { throw new RuntimeException(e); }
            }
        };
    }
}