package cn.bobdeng.anubis;

import lombok.Getter;

public class PartnerKeyId {
    @Getter
    private int id;

    public PartnerKeyId(int id) {
        this.id = id;
    }

    public static PartnerKeyId of(int id) {
        return new PartnerKeyId(id);
    }
}
