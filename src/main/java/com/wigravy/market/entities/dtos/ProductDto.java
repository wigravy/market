package com.wigravy.market.entities.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Size;
import java.math.BigDecimal;

@ApiModel(description = "Product dto in the application.")
public interface ProductDto {
    @ApiModelProperty(notes = "A unique product identifier. No two products with the same identifier can exist.", example = "1", required = true)
    Long getId();

    @ApiModelProperty(notes = "Product title. The minimum length is 4 characters, the maximum is 255.", example = "Milk", required = true, position = 1)
    @Size(min = 4, max = 255)
    String getTitle();

    @ApiModelProperty(notes = "The price of the product with a fractional part. A maximum of 8 digits before the decimal point.", example = "199.99", required = true, position = 2)
    BigDecimal getPrice();
}
