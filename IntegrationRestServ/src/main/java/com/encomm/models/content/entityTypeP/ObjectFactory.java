//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.07.11 at 01:28:16 AM IST 
//


package com.encomm.models.content.entityTypeP;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.encomm.models.content.entityTypeP package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FileTable_QNAME = new QName("", "fileTable");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.encomm.models.content.entityTypeP
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FileTableType }
     * 
     */
    public FileTableType createFileTableType() {
        return new FileTableType();
    }

    /**
     * Create an instance of {@link ParaType }
     * 
     */
    public ParaType createParaType() {
        return new ParaType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FileTableType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "fileTable")
    public JAXBElement<FileTableType> createFileTable(FileTableType value) {
        return new JAXBElement<FileTableType>(_FileTable_QNAME, FileTableType.class, null, value);
    }

}
