package cn.bobdeng.anubis;

import java.util.Optional;

public interface PartnerRepository {
    void save(Partners partners, Partner partner);

    Optional<Partner> findByCode(Partners partners, PartnerCode code);
}
