package cn.bobdeng.anubis;

import static cn.bobdeng.anubis.Partners.partnerKeyRepository;

public class PartnerKey {
    private PartnerKeyId id;
    private AccessKey key;
    private ExpireDate expireAt;

    public PartnerKey(AccessKey key, ExpireDate expireAt) {
        this.key = key;
        this.expireAt = expireAt;
    }

    public PartnerKey(PartnerKeyId id, AccessKey key, ExpireDate expireAt) {
        this.id = id;
        this.key = key;
        this.expireAt = expireAt;
    }

    public String key() {
        return this.key.getKey();
    }

    public boolean verify(Content content, Signature signature) {
        return this.key.verifySign(content.getContent(), signature.getSignature());
    }

    public boolean isActive() {
        return expireAt.isBiggerThan(System.currentTimeMillis());
    }

    public void setExpireAt(long currentTimeMillis) {
        this.expireAt = new ExpireDate(currentTimeMillis);
        partnerKeyRepository.save(this);
    }

    public int id() {
        return this.id.getId();
    }

    public Long expireAt() {
        return expireAt.get();
    }
}
