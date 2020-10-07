package woodspring.springspot.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.Ignition;
import org.apache.ignite.lang.IgniteCallable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import woodspring.springspot.config.SpringIgniteConfig;
import woodspring.springspot.model.FXSpot;
import woodspring.springspot.model.FxSpotKey;
import woodspring.springspot.utils.FX_CONSTANT;

@Component
public class IgniteDAO {
	private final static Logger logger = LoggerFactory.getLogger( IgniteDAO.class);
	
	private final Ignite ignite;
	
	private final IgniteCache< FxSpotKey, String> fxCache;
	
	private List<FxSpotKey> keyList = new ArrayList<>();
	
	private Gson gson = new Gson();
	
	//@Autowired
	private SpringIgniteConfig igniteConfig;
	
	public IgniteDAO() {
		igniteConfig = new SpringIgniteConfig();
		ignite = Ignition.getOrStart( igniteConfig.configIgnite());
		fxCache = ignite.getOrCreateCache(FX_CONSTANT.FX_CACHE);
	}

	
	public boolean putListToCache(List<FXSpot> fxSpotList) {
		boolean bRet = true;
		fxSpotList.stream().forEach( fxSpot -> {
			FxSpotKey spotKey = new FxSpotKey( fxSpot.getSymbol(), fxSpot.getTenor(), fxSpot.getPrice());
			keyList.add( spotKey);
			logger.info("putListToCache, keyList size:{}, list:[{}], fxSpotList size:{}", keyList.size(), keyList, fxSpotList.size());
			String spotString = gson.toJson( fxSpot);
			fxCache.getAndPut(spotKey, spotString);
		});
		
		return bRet;
	}
	
	public boolean startBroadcast() {
		boolean bRet = true;
		IgniteCompute igCompute = ignite.compute( ignite.cluster().forServers().forServers());
		
		
		return bRet;
	}
	
	public Collection<FXSpot> startCall() {
		boolean bRet = true;
		//List<String> strList = new ArrayList<>();
		//strList.add("USDCADON1.1234");
		
		Collection<IgniteCallable<FXSpot>> calls = new ArrayList<>();
		logger.info("in startCall, keyList size:{}", keyList.size());
		for (FxSpotKey key : keyList) {
			calls.add( new IgniteCallable<FXSpot>() {
					@Override
					public FXSpot call() {
						FXSpot retFxSpot = null; 
logger.info("in Call;-0- key:[{}]", key);
						if (fxCache.containsKey(key)) {
							String spotStr = fxCache.get( key);
							retFxSpot =  gson.fromJson(spotStr, FXSpot.class);
						}
logger.info("in Call;-0- key:[{}], retFxSpot:[{}]", key, retFxSpot);						
						return retFxSpot;
					}
			});
		}
		Collection<FXSpot> result = ignite.compute().call( calls);		
		
		return result;
	}
	public boolean startAsyncCall() {
		boolean bRet = true;
		IgniteCompute asyncCompute = ignite.compute().withAsync();
		
		return bRet;
	}
}
