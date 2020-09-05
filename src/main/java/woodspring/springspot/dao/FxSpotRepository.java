package woodspring.springspot.dao;

import org.apache.ignite.springdata.repository.IgniteRepository;
import org.apache.ignite.springdata.repository.config.RepositoryConfig;

import woodspring.springspot.model.FXSpotDTO;

@RepositoryConfig(cacheName = "springSpotCache")
public interface FxSpotRepository  extends IgniteRepository<FXSpotDTO, Long >{
	FXSpotDTO getFXSpotDTOById( Long rId);

}
