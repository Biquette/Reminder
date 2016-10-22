package com.itametis.sandbox.drools;

import org.drools.ObjectFilter;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class FilterByClass implements ObjectFilter {

    private Class<?> theClass;


    public FilterByClass(Class<?> clazz) {
        theClass = clazz;
    }


    public boolean accept(Object object) {
        return theClass.isInstance(object);
    }
}
