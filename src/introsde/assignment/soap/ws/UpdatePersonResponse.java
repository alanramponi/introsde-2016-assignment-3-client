
package introsde.assignment.soap.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per updatePersonResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="updatePersonResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="updatedPerson" type="{http://ws.soap.assignment.introsde/}person" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updatePersonResponse", propOrder = {
    "updatedPerson"
})
public class UpdatePersonResponse {

    protected Person updatedPerson;

    /**
     * Recupera il valore della proprietà updatedPerson.
     * 
     * @return
     *     possible object is
     *     {@link Person }
     *     
     */
    public Person getUpdatedPerson() {
        return updatedPerson;
    }

    /**
     * Imposta il valore della proprietà updatedPerson.
     * 
     * @param value
     *     allowed object is
     *     {@link Person }
     *     
     */
    public void setUpdatedPerson(Person value) {
        this.updatedPerson = value;
    }

}
