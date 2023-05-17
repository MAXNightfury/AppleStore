package vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BasketVO {
	private int basketId;
	private int count;
	private String customerId;
	private int productId;

}
