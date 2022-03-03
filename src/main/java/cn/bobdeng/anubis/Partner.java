package cn.bobdeng.anubis;

public class Partner {
    private PartnerCode code;
    private PartnerName name;

    public Partner(PartnerCode code, PartnerName name) {
        this.code = code;
        this.name = name;
    }

    public String code() {
        return this.code.getCode();
    }

    public String name() {
        return name.getName();
    }
}
