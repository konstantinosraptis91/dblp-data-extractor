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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
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
 *       &lt;choice&gt;
 *         &lt;sequence&gt;
 *           &lt;element ref="{http://kraptis91.com}author" maxOccurs="unbounded" minOccurs="0"/&gt;
 *           &lt;choice maxOccurs="unbounded" minOccurs="0"&gt;
 *             &lt;element ref="{http://kraptis91.com}note"/&gt;
 *             &lt;element ref="{http://kraptis91.com}url"/&gt;
 *             &lt;element ref="{http://kraptis91.com}cite"/&gt;
 *           &lt;/choice&gt;
 *         &lt;/sequence&gt;
 *         &lt;element ref="{http://kraptis91.com}crossref"/&gt;
 *       &lt;/choice&gt;
 *       &lt;attribute name="key" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *       &lt;attribute name="mdate" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *       &lt;attribute name="cdate" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "author",
    "noteOrUrlOrCite",
    "crossref"
})
@XmlRootElement(name = "person")
public class Person {

    protected List<Author> author;
    @XmlElements({
        @XmlElement(name = "note", type = Note.class),
        @XmlElement(name = "url", type = Url.class),
        @XmlElement(name = "cite", type = Cite.class)
    })
    protected List<Object> noteOrUrlOrCite;
    protected String crossref;
    @XmlAttribute(name = "key", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String key;
    @XmlAttribute(name = "mdate")
    @XmlSchemaType(name = "anySimpleType")
    protected String mdate;
    @XmlAttribute(name = "cdate")
    @XmlSchemaType(name = "anySimpleType")
    protected String cdate;

    /**
     * Gets the value of the author property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the author property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAuthor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Author }
     * 
     * 
     */
    public List<Author> getAuthor() {
        if (author == null) {
            author = new ArrayList<Author>();
        }
        return this.author;
    }

    /**
     * Gets the value of the noteOrUrlOrCite property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the noteOrUrlOrCite property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNoteOrUrlOrCite().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Note }
     * {@link Url }
     * {@link Cite }
     * 
     * 
     */
    public List<Object> getNoteOrUrlOrCite() {
        if (noteOrUrlOrCite == null) {
            noteOrUrlOrCite = new ArrayList<Object>();
        }
        return this.noteOrUrlOrCite;
    }

    /**
     * Gets the value of the crossref property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrossref() {
        return crossref;
    }

    /**
     * Sets the value of the crossref property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrossref(String value) {
        this.crossref = value;
    }

    /**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey(String value) {
        this.key = value;
    }

    /**
     * Gets the value of the mdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMdate() {
        return mdate;
    }

    /**
     * Sets the value of the mdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMdate(String value) {
        this.mdate = value;
    }

    /**
     * Gets the value of the cdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCdate() {
        return cdate;
    }

    /**
     * Sets the value of the cdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCdate(String value) {
        this.cdate = value;
    }

}