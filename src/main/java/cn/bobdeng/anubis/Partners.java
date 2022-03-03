package cn.bobdeng.anubis;

public class Partners {
    public static PartnerRepository partnerRepository;

    public void newPartner(PartnerCode code, PartnerName name) {
        partnerRepository.save(this,new Partner(code,name));
    }
}
