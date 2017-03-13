package nl.kabisa.service.booking

import org.hibernate.validator.constraints.NotBlank
import javax.validation.constraints.NotNull

data class Shipment(@field:NotBlank val customerReference: String,
                    @field:NotNull val height: Int?,
                    @field:NotNull val weight: Int?,
                    @field:NotNull val length: Int?,
                    val description: String?)
