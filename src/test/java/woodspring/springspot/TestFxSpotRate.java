package woodspring.springspot;

import static org.junit.Assert.assertNotNull;

import java.util.concurrent.ConcurrentSkipListMap;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import woodspring.springspot.model.FXSpot;
import woodspring.springspot.model.FxSpotKey;
import woodspring.springspot.utils.FxSpotRate;


public class TestFxSpotRate {
	
	private final static Logger logger = LoggerFactory.getLogger(TestFxSpotRate.class);
	private FxSpotRate fxSpotRate = new FxSpotRate();
	
	@Test 
	public void testGetSymbol() {
		int index =0;
		
		FXSpot fxSpot = fxSpotRate.getSymbol();
		logger.info("{} FXSpot:{}", index++,fxSpot);
		fxSpot = fxSpotRate.getSymbol();
		logger.info("{} FXSpot:{}", index++,fxSpot);
		fxSpot = fxSpotRate.getSymbol();
		logger.info("{} FXSpot:{}", index++,fxSpot);
		fxSpot = fxSpotRate.getSymbol();
		logger.info("{} FXSpot:{}", index++,fxSpot);
		fxSpot = fxSpotRate.getSymbol();
		logger.info("{} FXSpot:{}", index++,fxSpot);
		fxSpot = fxSpotRate.getSymbol();
		logger.info("{} FXSpot:{}", index++,fxSpot);
		fxSpot = fxSpotRate.getSymbol();
		logger.info("{} FXSpot:{}", index++,fxSpot);
		fxSpot = fxSpotRate.getSymbol();
		logger.info("{} FXSpot:{}", index++,fxSpot);
		fxSpot = fxSpotRate.getSymbol();
		logger.info("{} FXSpot:{}", index++,fxSpot);
		fxSpot = fxSpotRate.getSymbol();
		logger.info("{} FXSpot:{}", index++,fxSpot);
		fxSpot = fxSpotRate.getSymbol();
		logger.info("{} FXSpot:{}", index++,fxSpot);
		for (int ind =0; ind < 1000; ind++) {
		fxSpot = fxSpotRate.getSymbol();
		//logger.info("FXSpot:{}", fxSpot);
		}
		
		assertNotNull( fxSpot);
	}
	
	@Test
	public void testFxSpotKey() {
		ConcurrentSkipListMap<FxSpotKey, FXSpot> theMap = new ConcurrentSkipListMap<>();
		FXSpot fxSpot = fxSpotRate.getSymbol();
		FxSpotKey spotKey = null;
		for (int ind =0; ind < 10; ind++) {
			fxSpot = fxSpotRate.getSymbol();
			//logger.info("FXSpot:{}", fxSpot);
			spotKey = new FxSpotKey( fxSpot.getSymbol(), fxSpot.getTenor(), fxSpot.getPrice());
			logger.info("FxKey:{}, FXSpot:{} ", spotKey.toString(), fxSpot);
			theMap.put(spotKey,  fxSpot);
		}
		assertNotNull( fxSpot);
		
		
	}
	

}
