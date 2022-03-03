package cn.bobdeng.anubis;

import lombok.Getter;

public class PartnerCode {
    @Getter
    private String code;

    public PartnerCode(String code) {

        this.code = code;
    }
}
