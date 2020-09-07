package woodspring.springspot.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class FxSpotKey {
	
	private String symbol;
	private String tenor;
	private String price;

		public FxSpotKey(String symbo, String tenor, BigDecimal price) {
			this.symbol = symbol;
			this.tenor = tenor;
			
		}
}
