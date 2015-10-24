package fr.grafcet.bd;

import fr.grafcet.persistence.xml.ObjectFactory;

public class XMLObjectFactory extends ObjectFactory {

    private static ObjectFactory instance;

    private XMLObjectFactory() {
    }

    public static final ObjectFactory getInstance() {
	if (null == instance) {
	    instance = new XMLObjectFactory();
	}
	return instance;
    }
}
