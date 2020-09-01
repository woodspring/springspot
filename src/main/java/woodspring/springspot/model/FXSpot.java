package woodspring.springspot.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class FXSpot {
	
	private String symbol;
	private BigDecimal price;
	private long quoteTime;
	//private int notation;

}
