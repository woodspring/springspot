package woodspring.springspot;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import woodspring.springspot.model.FXSpot;
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
		logger.info("FXSpot:{}", fxSpot);
		}
		
		assertNotNull( fxSpot);
	}
	

}
