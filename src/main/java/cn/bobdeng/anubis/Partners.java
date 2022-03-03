package cn.bobdeng.anubis;

public class Partners {
    public static PartnerRepository partnerRepository;

    public void newPartner(PartnerCode code, PartnerName name) throws DuplicateCodeException {
        if (partnerRepository.findByCode(this, code).isPresent()) {
            throw new DuplicateCodeException();
        }
        partnerRepository.save(this, new Partner(code, name));
    }
}
