package woodspring.springspot.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import lombok.Data;

@Data
public class FXSpotDTO  implements Serializable {
	
	@QuerySqlField(index = true)
	private Long rd;
	
	@QuerySqlField(index = true)
	private String symbol;
	
	@QuerySqlField(index = true)
	private BigDecimal price;
	
	@QuerySqlField(index = true)
	private String notation;
	
	private String type;
	private long quoteTime;

}
