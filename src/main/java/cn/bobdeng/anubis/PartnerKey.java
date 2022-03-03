package cn.bobdeng.anubis;

public class PartnerKey {
    private AccessKey key;
    private ExpireDate expireAt;

    public PartnerKey(AccessKey key, ExpireDate expireAt) {
        this.key = key;
        this.expireAt = expireAt;
    }

    public String key() {
        return this.key.getKey();
    }

    public boolean verify(Content content, Signature signature) {
        return this.key.verifySign(content.getContent(), signature.getSignature());
    }
}
