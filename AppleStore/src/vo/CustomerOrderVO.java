package vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Data
@Getter
@Setter
public class CustomerOrderVO {
	private int orderId;
	private Date orderDate;
	private String customerId;
	private Date orderUpdateDate;
	private Date orderEndDate;
	private String statusId;

}
