package vo;

import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductVO {
private int productId;
private String productName;
private int price;
private Date productInputDate;
private Date productUpdateDate;
private Date producDeleteDate;
private String productImage;
private int categoryId;

}
