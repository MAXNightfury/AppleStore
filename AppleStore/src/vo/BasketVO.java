package src.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BasketVO {
	private int basketId;
	private int basketProductCount;
	private String customerId;
	private int productId;

}
