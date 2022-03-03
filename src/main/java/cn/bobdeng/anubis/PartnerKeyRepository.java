package cn.bobdeng.anubis;

import java.util.stream.Stream;

public interface PartnerKeyRepository {
    void save(Partner partner, PartnerKey partnerKey);

    Stream<PartnerKey> findKeys(Partner partner);

    void save(PartnerKey partnerKey);
}
