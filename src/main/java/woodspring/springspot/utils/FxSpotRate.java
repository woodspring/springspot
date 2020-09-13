package woodspring.springspot.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import woodspring.springspot.model.FXSpot;
import woodspring.springspot.model.FxSpotKey;
import woodspring.springspot.model.SymbolTenor;

@Component
public class FxSpotRate {
	private final static  Logger logger = LoggerFactory.getLogger(FxSpotRate.class);
	private  ArrayList<String> symbolList = new ArrayList<>();
	private ArrayList<BigDecimal> priceList = new  ArrayList<>();
	private  ArrayList<String> priceStrList = new ArrayList<>();
	private  ArrayList<String> tenorStrList = new ArrayList<>();
	private int size =0;
	private int tenorSize =0;
	
	private ConcurrentSkipListMap<FxSpotKey, FXSpot> quoteMap = new ConcurrentSkipListMap<>();
	private ConcurrentSkipListMap<SymbolTenor, ConcurrentSkipListMap<String, ArrayList<FXSpot>>> quoteSTMap = new ConcurrentSkipListMap<>();
			
			
	public FxSpotRate() {
		symbolList.add("EURUSD");priceList.add(new BigDecimal("1.1805"));
		symbolList.add("USDCAD");priceList.add(new BigDecimal("1.3225"));
		symbolList.add("USDJPY");priceList.add(new BigDecimal("105.8860"));
		symbolList.add("CADJPY");priceList.add(new BigDecimal("80.0670"));
		symbolList.add("EURUSD");priceList.add(new BigDecimal("1.1805"));
		symbolList.add("USDCAD");priceList.add(new BigDecimal("1.3225"));
		symbolList.add("EURUSD");priceList.add(new BigDecimal("1.1805"));
		tenorStrList.add("ON");tenorStrList.add("TN"); tenorStrList.add("SN");tenorStrList.add("TOM");
		tenorStrList.add("1W");tenorStrList.add("1M");	tenorStrList.add("2W"); tenorStrList.add("2M");
		tenorStrList.add("3M");tenorStrList.add("6M");tenorStrList.add("9M");tenorStrList.add("1Y");
		tenorStrList.add("ON");tenorStrList.add("ON");tenorStrList.add("ON");tenorStrList.add("TN");
		tenorStrList.add("ON");tenorStrList.add("SN");tenorStrList.add("TOM");tenorStrList.add("1W");
		tenorStrList.add("ON");tenorStrList.add("ON");tenorStrList.add("ON");tenorStrList.add("ON");
		tenorStrList.add("ON");tenorStrList.add("ON");tenorStrList.add("ON");tenorStrList.add("ON");
		size = symbolList.size();
		tenorSize = tenorStrList.size();
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
		retFx.setTenor(tenorStrList.get( ((int) (Math.random() * tenorSize))));
		
		quoteMap.put(new FxSpotKey( retFx.getSymbol(), retFx.getTenor(), retFx.getPrice()), retFx);
		putToSTMap( retFx);
		priceList.set( ind,  retFx.getPrice());
		//retFx.setPriceStr( retFx.getPrice().setScale(6, BigDecimal.ROUND_DOWN).toString());
		//logger.info("ind:{} FXSpot:{}", ind, retFx.toString());
		return retFx;
	}
	
	public FXSpot getSymbol(String symStr) {
		//DecimalFormat df = new DecimalFormat("##,###.000000");
		FXSpot retFx = new FXSpot();
		int ind = symbolList.indexOf( symStr);
		retFx.setSymbol( symbolList.get(ind));
		int pluse = (int)(Math.random() *2);
		int rang = (int)(Math.random()*350);
		BigDecimal price = priceList.get(ind);
		BigDecimal value = price.multiply( new BigDecimal(rang* 0.000001));
		retFx.setPrice(((pluse == 0) ? price.subtract(value) : price.add( value)).setScale(6, BigDecimal.ROUND_DOWN));
		retFx.setQuoteTime( System.nanoTime());
		priceList.remove(ind);
		priceList.add(ind, retFx.getPrice());
		retFx.setTenor(tenorStrList.get( ((int) (Math.random() * tenorSize))));
		priceList.set( ind,  retFx.getPrice());
		quoteMap.put(new FxSpotKey( retFx.getSymbol(), retFx.getTenor(), retFx.getPrice()), retFx);
		putToSTMap( retFx);
		return retFx;
	}
	
	public FXSpot getSymbol(String symStr, String tenor) {
		//DecimalFormat df = new DecimalFormat("##,###.000000");
		FXSpot retFx = new FXSpot();
		int ind = symbolList.indexOf( symStr);
		//int ind = (int)(Math.random() *  size); 
		retFx.setSymbol( symbolList.get(ind));
		int pluse = (int)(Math.random() *2);
		int rang = (int)(Math.random()*350);
		BigDecimal price = priceList.get(ind);
		BigDecimal value = price.multiply( new BigDecimal(rang* 0.000001));
		retFx.setPrice(((pluse == 0) ? price.subtract(value) : price.add( value)).setScale(6, BigDecimal.ROUND_DOWN));
		retFx.setQuoteTime( System.nanoTime());
		retFx.setTenor(tenor);
		quoteMap.put(new FxSpotKey( retFx.getSymbol(), retFx.getTenor(), retFx.getPrice()), retFx);
		putToSTMap( retFx);
		priceList.set( ind,  retFx.getPrice());
		return retFx;
	}
	
	public List<FXSpot> getFxQuote(int qNum) {
		List<FXSpot> listQuote = new ArrayList<>();
		for ( int ind=0; ind< qNum; ind++) {
			listQuote.add( getSymbol());
		}
		return listQuote;
		
	}
	
	public List<FXSpot> getFxQuote(String symbol, int qNum) {
		List<FXSpot> listQuote = new ArrayList<>();
		for ( int ind=0; ind< qNum; ind++) {
			listQuote.add( getSymbol(symbol));
		}
		return listQuote;		
	}
	
	public FXSpot createFXSpot( FXSpot fxRate) {
		fxRate.setQuoteTime( System.nanoTime());
		quoteMap.put(new FxSpotKey( fxRate.getSymbol(), fxRate.getTenor(), fxRate.getPrice()), fxRate);
		
		return fxRate;		
	}
	
	
	private FXSpot putToSTMap(FXSpot fxSpot) {
		SymbolTenor symTenor = new SymbolTenor( fxSpot.getSymbol(), fxSpot.getTenor());
		if ( quoteSTMap.containsKey( symTenor) ) {
			if (quoteSTMap.get( symTenor).containsKey( fxSpot.getPriceString()) ) {
				ArrayList<FXSpot> theList = quoteSTMap.get( symTenor).get( fxSpot.getPriceString());
				theList.add( fxSpot);
				logger.info("{} EXITED the sampe price{}", symTenor.toString(),fxSpot.getPriceString() );
			} else {
				ArrayList<FXSpot> theList = new ArrayList<FXSpot>();
				theList.add( fxSpot);
				quoteSTMap.get( symTenor).put( fxSpot.getPriceString(), theList); // it will be a problem				
			}			
		} else {
			ArrayList<FXSpot> theList = new ArrayList<FXSpot>();
			theList.add( fxSpot);
			ConcurrentSkipListMap<String, ArrayList<FXSpot>> priceMap =  new ConcurrentSkipListMap<>();
			priceMap.put( fxSpot.getPriceString(), theList);
			quoteSTMap.put( symTenor, priceMap);		
		}
		return fxSpot;
		
	}
	
	public List<FXSpot> queryQuote(String symbol, String tenor) {
		List<FXSpot> retList  = new ArrayList<>();
		Collection<ArrayList<FXSpot>> aCollect = null;
		SymbolTenor keyST = new SymbolTenor(symbol,tenor);
logger.info("queryQuote:{},{}", symbol, tenor);
		ConcurrentSkipListMap<String, ArrayList<FXSpot>> priceMap = quoteSTMap.get( keyST);
		if ( priceMap != null) {
			logger.info("queryQuote:priceMap size:{},{}", priceMap.size(), priceMap.toString());
			int ind=0;
			for (ArrayList<FXSpot> item: priceMap.values()) {
				logger.info("in ArrayList {} loop [{}] size:{}", ind++, item.toString(), item.size());
				if ( !item.isEmpty() ) {
					if ( item.size() == 1) 
						retList.add( item.get(0));
					else {
						logger.info("{} price more than one:{}", ind, item.toString());
						for ( FXSpot fx : item) {
							retList.add( fx);
						}
					}
				} else {
					logger.info( "{} Array is empty", ind++);
				}
							
				
			}
			//aCollect = priceMap.values()
			//retList = (List<FXSpot>) priceMap.values().toArray()[0];
			logger.info("queryQuote:priceMap retList, size:{}", retList.size());
		}
		
		
		
		return retList;
		
	}


}
