package crm_connector.connectors;

import commons.Input;

import java.lang.reflect.InvocationTargetException;

public enum CRMConnectorType {
    STRAWBERRY(StrawberryCRMConnector.class),
    BANANA(BananaCRMConnector.class),
    ;

    private final Class<?> objectClass;

    CRMConnectorType(Class<?> objectClass) {
        this.objectClass = objectClass;
    }

    public CRMConnector create(Input config)
            throws
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException
    {
        return (CRMConnector) this.objectClass.getConstructor(Input.class).newInstance(config);
    }
}
