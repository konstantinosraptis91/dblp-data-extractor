//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.04.05 at 12:03:26 AM EEST 
//


package com.kraptis91;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;group ref="{http://kraptis91.com}titlecontents" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;attribute name="bibtex" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *       &lt;attribute name="aux" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *       &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "content"
})
public class Title {

    @XmlElementRefs({
        @XmlElementRef(name = "ref", namespace = "http://kraptis91.com", type = Ref.class, required = false),
        @XmlElementRef(name = "tt", namespace = "http://kraptis91.com", type = Tt.class, required = false),
        @XmlElementRef(name = "sup", namespace = "http://kraptis91.com", type = Sup.class, required = false),
        @XmlElementRef(name = "i", namespace = "http://kraptis91.com", type = I.class, required = false),
        @XmlElementRef(name = "sub", namespace = "http://kraptis91.com", type = Sub.class, required = false)
    })
    @XmlMixed
    protected List<Object> content;
    @XmlAttribute(name = "bibtex")
    @XmlSchemaType(name = "anySimpleType")
    protected String bibtex;
    @XmlAttribute(name = "aux")
    @XmlSchemaType(name = "anySimpleType")
    protected String aux;
    @XmlAttribute(name = "label")
    @XmlSchemaType(name = "anySimpleType")
    protected String label;
    @XmlAttribute(name = "type")
    @XmlSchemaType(name = "anySimpleType")
    protected String type;

    /**
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Tt }
     * {@link String }
     * {@link Ref }
     * {@link Sup }
     * {@link I }
     * {@link Sub }
     * 
     * 
     */
    public List<Object> getContent() {
        if (content == null) {
            content = new ArrayList<Object>();
        }
        return this.content;
    }

    /**
     * Gets the value of the bibtex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBibtex() {
        return bibtex;
    }

    /**
     * Sets the value of the bibtex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBibtex(String value) {
        this.bibtex = value;
    }

    /**
     * Gets the value of the aux property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAux() {
        return aux;
    }

    /**
     * Sets the value of the aux property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAux(String value) {
        this.aux = value;
    }

    /**
     * Gets the value of the label property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets the value of the label property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabel(String value) {
        this.label = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

}
