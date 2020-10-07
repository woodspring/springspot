package woodspring.springspot.config;

import java.util.Collections;

import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.failure.NoOpFailureHandler;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SpringIgniteConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(SpringIgniteConfig.class );
	
	public SpringIgniteConfig() {
		logger.info("================================== SpringIgniteConfig" );
	}
	
	//@Bean
	public IgniteConfiguration configIgnite() {
		IgniteConfiguration igniteCfg = new IgniteConfiguration();
		igniteCfg.setClientMode(true);
		igniteCfg.setPeerClassLoadingEnabled( true);
		//igniteCfg.setFailureHandler(new NoOpFailureHandler());
		TcpDiscoveryMulticastIpFinder  ipFinder = new TcpDiscoveryMulticastIpFinder ();
		//ipFinder.setAddresses(Collections.singletonList("192.168.7.143:47500..47509"));
		ipFinder.setAddresses(Collections.singletonList("192.168.0.101:47500..47509"));
		//ipFinder.setAddresses(Collections.singletonList("127.0.0.1:47500..47509"));
		
	
		igniteCfg.setDiscoverySpi(new TcpDiscoverySpi().setIpFinder(ipFinder));		
		igniteCfg.setFailureHandler(new NoOpFailureHandler());
		
		
		return igniteCfg;
		
	}

}
