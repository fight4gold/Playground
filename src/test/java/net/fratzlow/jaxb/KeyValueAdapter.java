package net.fratzlow.jaxb;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Write a list of key/value pairs analog to a Map. Is not provided as such straight off JAXB and it's annotations.
 *
 * @author ratzlow@gmail.com
 * @since 2014-11-22
 */
public class KeyValueAdapter extends XmlAdapter<Element, KeyValue> {
    private final static Logger LOGGER = Logger.getLogger(KeyValueAdapter.class);

    private final static JAXBContext jaxbContext = createJAXBContext();

    //---------------------------------------------------------
    // public API
    //---------------------------------------------------------

    @Override
    public Element marshal(KeyValue keyValue) throws Exception {
        if (keyValue == null) return null;

        // 1. Build the JAXBElement to wrap the instance of Parameter.
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Document document = documentBuilderFactory.newDocumentBuilder().newDocument();
        QName rootElement = new QName(keyValue.getKey());
        String value = keyValue.getValue();
        JAXBElement<String> jaxbElement = new JAXBElement<>(rootElement, String.class, value);

        // 2.  Marshal the JAXBElement to a DOM element.
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.marshal(jaxbElement, document);

        return document.getDocumentElement();
    }

    @Override
    public KeyValue unmarshal(Element element) throws Exception {
        if (element == null) return null;

        String nil = element.getAttribute("xsi:nil");
        boolean isNull = nil != null && nil.equals("true");
        String value = isNull ? null : element.getTextContent();

        return new KeyValue( element.getLocalName(), value);
    }

    //------------------------------------------
    // helper methods
    //------------------------------------------


    private static JAXBContext createJAXBContext() {
        try {
            return JAXBContext.newInstance(String.class);
        } catch (JAXBException e) { throw new IllegalStateException(e); }
    }
}
