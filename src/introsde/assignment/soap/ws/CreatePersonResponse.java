
package introsde.assignment.soap.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per createPersonResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="createPersonResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createdPerson" type="{http://ws.soap.assignment.introsde/}person" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createPersonResponse", propOrder = {
    "createdPerson"
})
public class CreatePersonResponse {

    protected Person createdPerson;

    /**
     * Recupera il valore della proprietà createdPerson.
     * 
     * @return
     *     possible object is
     *     {@link Person }
     *     
     */
    public Person getCreatedPerson() {
        return createdPerson;
    }

    /**
     * Imposta il valore della proprietà createdPerson.
     * 
     * @param value
     *     allowed object is
     *     {@link Person }
     *     
     */
    public void setCreatedPerson(Person value) {
        this.createdPerson = value;
    }

}
