package net.fratzlow.jms;

import org.apache.log4j.Logger;


public class SimpleMessageListener {
    private static final Logger LOGGER = Logger.getLogger(SimpleMessageListener.class);

    public void onMessage(String msg) {
        LOGGER.info( "=====> " + msg );
    }
}
