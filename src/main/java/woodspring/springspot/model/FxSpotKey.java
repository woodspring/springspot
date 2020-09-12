package woodspring.springspot.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;


@Data
public class FxSpotKey implements Comparable<FxSpotKey>{
	private final static Logger logger = LoggerFactory.getLogger(FxSpotKey.class);
	
	private String symbol;
	private String tenor;
	private String price;
	
	private static DecimalFormat decFormat = new DecimalFormat("#0.######");

		public FxSpotKey(String symbol, String tenor, BigDecimal price) {
			this.symbol = symbol;
			this.tenor = tenor;
			this.price = decFormat.format( price);
			
		}

		@Override
		public int hashCode() {
			final int prime = 13;
			int result = 1;
			//result = prime * result + symbol.hashCode() ;
			//result = prime * result + tenor.hashCode();
			result = prime * result + symbol.hashCode()  + tenor.hashCode()  + price.hashCode();
			return result;			
		}
		
		@Override
		public boolean equals(Object obj) {
			boolean bRet = true;
			if ( this== obj) return true;
			if ( obj == null) return false;
			if ( getClass() != obj.getClass()) return false;
			FxSpotKey key2 = (FxSpotKey)obj;
			if (!( this.symbol.equals( key2.getSymbol()) && 
				 this.tenor.equalsIgnoreCase(key2.getTenor()) &&
				 this.price.equalsIgnoreCase( key2.getPrice()) )) {
				bRet = false;
			}
			return bRet;
		}
		
		@Override
		public int compareTo(FxSpotKey o) {
			if ( o == null || o.getPrice() == null) return 1;
			int retI = 1;
			retI = this.symbol.compareTo( o.getSymbol());
			if ( retI == 0) {
				// the same symbol
				int comp2 = this.tenor.compareToIgnoreCase( o.getTenor());
				if ( comp2 == 0) {
					comp2 = this.price.compareTo( o.getPrice());
					if (!(comp2 == 0))  retI = comp2;
				} else {
					retI = comp2;
				}
			}
			logger.info( "compareTo {}: key:{} - hc:{}, key2:{}, hs2:{}",  retI, this.toString(), this.hashCode(), o.toString(), o.hashCode());
			return retI;
		}
}
