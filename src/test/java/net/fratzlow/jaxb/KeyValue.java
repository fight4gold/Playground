package net.fratzlow.jaxb;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * An entry that can be used in lists to preserve a Map's key/value semantic.
 *
 * @author ratzlow@gmail.com
 * @since 2014-11-22
 */
@XmlJavaTypeAdapter(KeyValueAdapter.class)
public class KeyValue {
    private String key;
    private String value;

    public KeyValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
