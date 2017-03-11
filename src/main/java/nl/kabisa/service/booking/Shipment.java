package nl.kabisa.service.booking;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Shipment {
    @NotBlank
    public final String customerReference;

    @NotNull
    public final Integer height;

    @NotNull
    public final Integer weight;

    @NotNull
    public final Integer length;

    public final String description;

    @JsonCreator
    public Shipment(@JsonProperty("customerReference") String customerReference,
            @JsonProperty("height") Integer height,
            @JsonProperty("weight") Integer weight,
            @JsonProperty("length") Integer length,
            @JsonProperty("description") String description) {
        this.customerReference = customerReference;
        this.height = height;
        this.weight = weight;
        this.length = length;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Shipment shipment = (Shipment) o;
        return Objects.equals(customerReference, shipment.customerReference) &&
               Objects.equals(height, shipment.height) &&
               Objects.equals(weight, shipment.weight) &&
               Objects.equals(length, shipment.length) &&
               Objects.equals(description, shipment.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerReference, height, weight, length, description);
    }
}
