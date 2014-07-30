package net.fratzlow.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.jms.TextMessage;

/**
 * Setup Spring wired in-mem message broker
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/META-INF/spring-jms.xml")
public class ActiveMqInMemoryTest {

    @Resource
    private JmsTemplate jmsTemplate;

    @Test
    public void testSimpleMsgSubmit() {

        for (int i=0; i < 1000; i++ ) {
            if ( i % 5 == 0 ) {
                final int count = i;
                jmsTemplate.send(
                        "TEST.FOO",
                        session -> {
                            TextMessage textMessage = session.createTextMessage("Message_" + count);
                            textMessage.setJMSCorrelationID("" + 1);
                            return textMessage;
                        });

            }
        }
    }
}
