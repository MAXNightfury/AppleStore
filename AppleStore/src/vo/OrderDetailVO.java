package vo;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OrderDetailVO {
private int orderId; 
private int productId;
private int count;

}
