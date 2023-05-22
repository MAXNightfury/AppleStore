package src.vo;

import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CustomerVO {
private String customerId;
private String customerPassword;
private String customerName;
private int customerPhoneNumber;
private String customerAddress;
private Date customerBornDate;
private String customerSex;
private Date customerJoinDate; //TODO 이새끼만 join인게 맞냐 input 이라고 해야되냐 아니면 그냥 싹 다 input으로 바꿀까
private Date customerUpdateDate;
private Date customerDeleteDate;
}
