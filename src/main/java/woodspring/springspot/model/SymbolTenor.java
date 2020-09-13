package woodspring.springspot.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;

@Data
public class SymbolTenor implements Comparable< SymbolTenor> {
	private static final Logger logger = LoggerFactory.getLogger( SymbolTenor.class);
	
	private String symbol;
	private String tenor;
	
	public SymbolTenor(String symbol, String tenor) {
		this.symbol = symbol;
		this.tenor = tenor;
	}

	@Override
	public int compareTo(SymbolTenor o) {
		if ( o == null) return 1;
		int retI = 1;
		retI = this.symbol.compareTo( o.getSymbol());
		if ( retI == 0) {
			// the same symbol
			retI = this.tenor.compareToIgnoreCase( o.getTenor());
		}
		//logger.info( "compareTo {}: key:{} - hc:{}, key2:{}, hs2:{}",  retI, this.toString(), this.hashCode(), o.toString(), o.hashCode());
		return retI;
	}
	
	@Override
	public int hashCode() {
		final int prime = 13;
		int result = 1;
		//result = prime * result + symbol.hashCode() ;
		//result = prime * result + tenor.hashCode();
		result = prime * result + symbol.hashCode()  + tenor.hashCode();
		return result;			
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean bRet = true;
		if ( this== obj) return true;
		if ( obj == null) return false;
		if ( getClass() != obj.getClass()) return false;
		SymbolTenor key2 = (SymbolTenor)obj;
		if (!( this.symbol.equals( key2.getSymbol()) && 
			 this.tenor.equalsIgnoreCase(key2.getTenor()) ))
			  {
			bRet = false;
		}
		return bRet;
	}

}
