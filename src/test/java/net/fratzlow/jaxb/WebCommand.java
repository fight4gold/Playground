package net.fratzlow.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * // TODO (FRa) : (FRa) : comment
 *
 * @author ratzlow@gmail.com
 * @since 2014-11-22
 */
@XmlRootElement(name = "webCmd")
@XmlAccessorType(XmlAccessType.FIELD)
public class WebCommand {

    @XmlElement(name = "singleObj")
    private DomObj domObj;

    public WebCommand() {}

    public WebCommand(Map<String, String> cqmAttrs) {
        List<KeyValue> keyValues = new ArrayList<>();
        for (Map.Entry<String, String> entry : cqmAttrs.entrySet()) {
            keyValues.add( new KeyValue(entry.getKey(), entry.getValue()));
        }

        domObj = new DomObj();
        domObj.setAttrs( keyValues );
    }

    public Map<String, String> getCqmAttrs() {
        return (domObj == null) ? Collections.emptyMap() : domObj.getCqmAttrs();
    }
}
