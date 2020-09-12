package woodspring.springspot.service;

import java.util.List;

import woodspring.springspot.model.FXSpot;

public interface SpotService {
	
	public List<FXSpot> listQoute();
	public FXSpot createQuote( FXSpot fxQuote);
	public List<FXSpot> getQuote(String symbol);
	public FXSpot removeQoute(String symbol, String tenor);
	
	

}
