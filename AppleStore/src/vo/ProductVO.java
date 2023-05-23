package src.vo;

import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductVO {
    private int productId;
    private int categoryId;
    private String productName;
    private int productPrice;
    private Date productInputDate;
    private Date productUpdateDate;
    private Date productDeleteDate;
    private String productImage;
    private int productCount;
    private int totalOrderCount;

}
