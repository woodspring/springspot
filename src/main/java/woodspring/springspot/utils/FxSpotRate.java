package woodspring.springspot.utils;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import woodspring.springspot.model.FXSpot;

@Component
public class FxSpotRate {
	private final static  Logger logger = LoggerFactory.getLogger(FxSpotRate.class);
	private  ArrayList<String> symbolList = new ArrayList<>();
	private ArrayList<BigDecimal> priceList = new  ArrayList<>();
	private  ArrayList<String> priceStrList = new ArrayList<>();
	private int size =0;
			
			
	public FxSpotRate() {
		symbolList.add("EURUSD");symbolList.add("USDCAD");symbolList.add("USDJPY");symbolList.add("CADJPY");
		priceList.add(new BigDecimal("1.1805"));
		priceList.add(new BigDecimal("1.3225"));
		priceList.add(new BigDecimal("105.8860"));
		priceList.add(new BigDecimal("80.0670"));
		size = symbolList.size();
		
	}
	
	@SuppressWarnings("deprecation")
	public FXSpot getSymbol() {
		//DecimalFormat df = new DecimalFormat("##,###.000000");
		FXSpot retFx = new FXSpot();
		int ind = (int)(Math.random() *  size); 
		retFx.setSymbol( symbolList.get(ind));
		int pluse = (int)(Math.random() *2);
		int rang = (int)(Math.random()*350);
		BigDecimal price = priceList.get(ind);
		BigDecimal value = price.multiply( new BigDecimal(rang* 0.000001));
		retFx.setPrice(((pluse == 0) ? price.subtract(value) : price.add( value)).setScale(6, BigDecimal.ROUND_DOWN));
		retFx.setQuoteTime( System.nanoTime());
		priceList.remove(ind);
		priceList.add(ind, retFx.getPrice());
		//retFx.setPriceStr( retFx.getPrice().setScale(6, BigDecimal.ROUND_DOWN).toString());
		//logger.info("ind:{} FXSpot:{}", ind, retFx.toString());
		return retFx;
	}

}
