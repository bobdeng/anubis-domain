package cn.bobdeng.anubis;

import java.util.Optional;

import static cn.bobdeng.anubis.Partners.partnerKeyRepository;

public class Partner {
    private PartnerId id;
    private PartnerCode code;
    private PartnerName name;

    public Partner(PartnerCode code, PartnerName name) {
        this.code = code;
        this.name = name;
    }

    public Partner(PartnerId id, PartnerCode code, PartnerName name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public String code() {
        return this.code.getCode();
    }

    public String name() {
        return name.getName();
    }

    public void newKey(AccessKey key, ExpireDate expireAt) {
        partnerKeyRepository.save(this, new PartnerKey(key, expireAt));
    }

    public int id() {
        return id.getId();
    }

    public boolean verifySign(Content content, Signature signature) {
        return partnerKeyRepository.findKeys(this)
                .filter(PartnerKey::isActive)
                .anyMatch(partnerKey -> partnerKey.verify(content, signature));
    }

    public Optional<PartnerKey> findKey(int id) {
        return partnerKeyRepository.findKeys(this)
                .filter(partnerKey -> partnerKey.id() == id)
                .findFirst();
    }
}
