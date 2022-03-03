package cn.bobdeng.anubis;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PartnerKeyDO {
    private int id;
    private int partnerId;
    private String key;
    private Long expireAt;
}
