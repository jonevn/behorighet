@XmlSchema(namespace = BaseRepresentation.NAMESPACE, elementFormDefault = XmlNsForm.QUALIFIED, xmlns = {
		@XmlNs(namespaceURI = BaseRepresentation.NAMESPACE, prefix = "b"),
		@XmlNs(namespaceURI = BaseRepresentation.DAP_NAMESPACE, prefix = "dap") })

package se.evelonn.behorighet.api;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;