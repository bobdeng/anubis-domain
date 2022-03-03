package cn.bobdeng.anubis;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PartnerDO {
    private int id;
    private String code;
    private String name;
}
