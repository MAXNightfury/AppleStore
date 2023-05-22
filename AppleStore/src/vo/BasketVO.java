package src.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Data
@Getter
@Setter
public class BasketVO {
	private int basketId;
	private String customerId;
	private int productId;
	private Date basketInputDate;
	private Date basketUpdateDate;
	private Date basketDeleteDate;
	private int basketProductCount;
}
