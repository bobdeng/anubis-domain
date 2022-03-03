package cn.bobdeng.anubis;

import cn.bobdeng.dummydao.DummyDao;

import java.util.stream.Stream;

public class PartnerKeyRepositoryImpl implements PartnerKeyRepository {
    private final DummyDao<PartnerKeyDO, Integer> dummyDao;

    public PartnerKeyRepositoryImpl(DummyDao<PartnerKeyDO, Integer> dummyDao) {
        this.dummyDao = dummyDao;
    }

    @Override
    public void save(Partner partner, PartnerKey partnerKey) {
        dummyDao.save(PartnerKeyDO.builder()
                .key(partnerKey.key())
                .build());
    }

    @Override
    public Stream<PartnerKey> findKeys(Partner partner) {
        return dummyDao.all()
                .stream()
                .filter(partnerKeyDO -> partnerKeyDO.getPartnerId() == partner.id())
                .map(partnerKeyDO -> {
                    ExpireDate expireAt = new ExpireDate(partnerKeyDO.getExpireAt());
                    AccessKey key = new AccessKey(partnerKeyDO.getKey());
                    PartnerKeyId id = PartnerKeyId.of(partnerKeyDO.getId());
                    PartnerKey partnerKey = new PartnerKey(id, key, expireAt);
                    return partnerKey;
                });
    }

    @Override
    public void save(PartnerKey partnerKey) {
        dummyDao.findById(partnerKey.id()).ifPresent(partnerKeyDO -> {
            partnerKeyDO.setExpireAt(partnerKey.expireAt());
            dummyDao.save(partnerKeyDO);
        });
    }
}
