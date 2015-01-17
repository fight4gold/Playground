package net.fratzlow.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Wrapper for key-value pairs usually supplied from a Map.
 *
 * @author ratzlow@gmail.com
 * @since 2014-11-22
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DomObj {

    @XmlAnyElement
    private List<KeyValue> attrs;

    public List<KeyValue> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<KeyValue> attrs) {
        this.attrs = attrs;
    }

    public Map<String, String> getCqmAttrs() {
        if ( attrs == null || attrs.isEmpty() ) return Collections.emptyMap();

        Map<String, String> map = new HashMap<>(attrs.size());
        for (KeyValue attr : attrs) {
            map.put( attr.getKey(), attr.getValue() );
        }

        return map;
    }
}
