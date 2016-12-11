
package introsde.assignment.soap.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per savePersonMeasureResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="savePersonMeasureResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="measurement" type="{http://ws.soap.assignment.introsde/}measurement" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "savePersonMeasureResponse", propOrder = {
    "measurement"
})
public class SavePersonMeasureResponse {

    protected Measurement measurement;

    /**
     * Recupera il valore della proprietà measurement.
     * 
     * @return
     *     possible object is
     *     {@link Measurement }
     *     
     */
    public Measurement getMeasurement() {
        return measurement;
    }

    /**
     * Imposta il valore della proprietà measurement.
     * 
     * @param value
     *     allowed object is
     *     {@link Measurement }
     *     
     */
    public void setMeasurement(Measurement value) {
        this.measurement = value;
    }

}
