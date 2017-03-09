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
    public final int height;

    @NotNull
    public final int weight;

    @NotNull
    public final int length;

    public final String description;

    @JsonCreator
    public Shipment(@JsonProperty("customerReference") String customerReference,
            @JsonProperty("height") int height,
            @JsonProperty("weight") int weight,
            @JsonProperty("length") int length,
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
        return height == shipment.height &&
               weight == shipment.weight &&
               length == shipment.length &&
               Objects.equals(customerReference, shipment.customerReference) &&
               Objects.equals(description, shipment.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerReference, height, weight, length, description);
    }
}
