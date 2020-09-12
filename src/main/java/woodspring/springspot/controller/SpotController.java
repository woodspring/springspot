package woodspring.springspot.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import woodspring.springspot.model.FXSpot;
import woodspring.springspot.utils.FxSpotRate;

@RestController
@RequestMapping("/FXQ")
public class SpotController {
	private final static Logger logger = LoggerFactory.getLogger(SpotController.class);
	
	@Autowired
	FxSpotRate fxSpotRate;
	
	@PostMapping(value="/new")
	public ResponseEntity<FXSpot> newFXSpot(@RequestBody FXSpot fxSpot) {
		
		var retQ = fxSpot;
		retQ.setQuoteTime( System.currentTimeMillis());
		var headers = new HttpHeaders();
		headers.add("Responsed", "SpotController");
		
		return ResponseEntity.ok( retQ);
		
	}
	
	@GetMapping("/Qoute")
	@ResponseBody
	public List<FXSpot> getQuote() {
		List<FXSpot> retList = new ArrayList<>();
		for (int ind=0; ind < 100; ind++) {
			retList.add(fxSpotRate.getSymbol());
		}
		
		return retList;
		
	}
	

}
