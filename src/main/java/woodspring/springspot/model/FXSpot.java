package woodspring.springspot.model;

import java.math.BigDecimal;

import lombok.Data;

//@Data
public class FXSpot {
	
	private String symbol;
	private BigDecimal price;
	private String tenor;
	private long quoteTime;
	//private int notation;
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "FXSpot [symbol=" + symbol + ", price=" + price + ", tenor=" + tenor + ", quoteTime=" + quoteTime + "]";
	}
	public String getTenor() {
		return tenor;
	}
	public void setTenor(String tenor) {
		this.tenor = tenor;
	}
	public long getQuoteTime() {
		return quoteTime;
	}
	public void setQuoteTime(long quoteTime) {
		this.quoteTime = quoteTime;
	}

}
