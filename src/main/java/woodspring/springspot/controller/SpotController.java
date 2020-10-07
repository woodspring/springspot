package woodspring.springspot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import woodspring.springspot.model.FXSpot;
import woodspring.springspot.service.SpotService;
import woodspring.springspot.utils.FxSpotRate;

@RestController
@RequestMapping("/FXQ")
public class SpotController {
	private final static Logger logger = LoggerFactory.getLogger(SpotController.class);
	
	@Autowired
	SpotService spotService;
	
	@PostMapping(value="/new")
	public ResponseEntity<FXSpot> newFXSpot(@RequestBody FXSpot fxSpot) {
		
		var retQ = fxSpot;
		retQ.setQuoteTime( System.currentTimeMillis());
		var headers = new HttpHeaders();
		headers.add("Responsed", "SpotController");
		
		return ResponseEntity.ok( retQ);
		
	}
	
	@GetMapping("/Quotes")
	@ResponseBody
	public List<FXSpot> getFxQuote() {
		String symbol = null;
		List<FXSpot> retList = spotService.getQuote( symbol);
		return retList;
	}
	
	@GetMapping("/Call")
	@ResponseBody
	public List<FXSpot> callFxQuote() {
		String symbol = null;
		List<FXSpot> retList = spotService.getQuote();
		return retList;
	}
	
	@GetMapping("/Quote/{symbol}")
	public List<FXSpot> requestQoute(@PathVariable String symbol) {
		List<FXSpot> retList = spotService.getQuote( symbol);
		return retList;		
	}
	
	@GetMapping("/Quote")
	@ResponseBody
	public List<FXSpot> requestForQuote(@RequestParam Map<String, String> requestParams) { 
		logger.info("request:{}", requestParams);
		String symbol = requestParams.get("symbol");
		String tenor = requestParams.get("tenor");
		logger.info("request:symbol:{}, tenor:{}", symbol, tenor);
		List<FXSpot> retList = spotService.getQuote(symbol,  tenor);
		return retList;
		
	}
	
	
//	@GetMapping("/Qoute/{symbol}")
//	public List<FXSpot> getQuote(@PathVariable String symbol) {
//		List<FXSpot> retList = spotService.getQuote( symbol );
//		return retList;	
//	}
//	
//	
//	@GetMapping("/Qoute")
//	@ResponseBody
//	public List<FXSpot> getQuoteST(@RequestParam String symbol, @RequestParam tenor) {
//		List<FXSpot> retList = spotService.getQuote( "USDCAD");
//		return retList;
//		
//	}

}
