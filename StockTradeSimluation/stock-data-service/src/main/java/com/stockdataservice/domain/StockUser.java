package com.stockdataservice.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="StockUser")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockUser {

    @Id
    @Column(name = "id")
    @JsonProperty("id")
    String id;

    @Column(name = "stock_name")
    @JsonProperty("stock_name")
    String stock_name;

    @Column(name = "stock_volume")
    @JsonProperty("stock_volume")
    String stock_volume;

    @Column(name = "user_id")
    @JsonProperty("user_id")
    String user_id;
}
