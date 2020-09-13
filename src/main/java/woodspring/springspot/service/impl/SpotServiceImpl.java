package woodspring.springspot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import woodspring.springspot.model.FXSpot;
import woodspring.springspot.service.SpotService;
import woodspring.springspot.utils.FxSpotRate;
import woodspring.springspot.model.SymbolTenor;


@Service
public class SpotServiceImpl implements SpotService {
	
	@Autowired
	FxSpotRate fxRate;

	@Override
	public List<FXSpot> listQoute() {
		int num = (int)(Math.random()*550);
		List<FXSpot> retList = fxRate.getFxQuote( num);
		return retList;
	}

	@Override
	public FXSpot createQuote(FXSpot fxQuote) {
		FXSpot fxSpot = fxRate.createFXSpot(fxQuote);
		return fxSpot;
	}

	@Override
	public List<FXSpot> getQuote(String symbol) {
		int num = (int)(Math.random()*50);
		List<FXSpot> retList = null;
		if ( symbol == null) {
			retList = fxRate.getFxQuote(num);
		}	else {
			retList = fxRate.getFxQuote( symbol, num);
		}
		return retList;
	}

	@Override
	public List<FXSpot> getQuote(String symbol, String tenor) {
		int num = (int)(Math.random()*50);
		List<FXSpot> retList = fxRate.queryQuote(symbol, tenor);
		return retList;
	}
	
	@Override
	public FXSpot removeQoute(String symbol, String tenor) {
		// TODO Auto-generated method stub
		return null;
	}

}
