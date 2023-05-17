package vo;

import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CustomerVO {
private String customerId;
private String password;
private String name;
private int phoneNumber;
private String address;
private Date bornDate;
private char sex;
private Date customerJoinDate;
private Date customerUpdateDate;
private Date customerDeleteDate;
}
