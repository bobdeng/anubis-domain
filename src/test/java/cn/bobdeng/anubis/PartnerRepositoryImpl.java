package cn.bobdeng.anubis;

import cn.bobdeng.dummydao.DummyDao;

import java.util.Optional;

public class PartnerRepositoryImpl implements PartnerRepository {
    private final DummyDao<PartnerDO, Integer> dummyDao;

    public PartnerRepositoryImpl(DummyDao<PartnerDO, Integer> dummyDao) {
        this.dummyDao = dummyDao;
    }

    @Override
    public void save(Partners partners, Partner partner) {
        dummyDao.save(PartnerDO.builder()
                .code(partner.code())
                .name(partner.name())
                .build());
    }

    @Override
    public Optional<Partner> findByCode(Partners partners, PartnerCode code) {
        return dummyDao.all()
                .stream().filter(partnerDO -> partnerDO.getCode().equals(code.getCode()))
                .map(partnerDO -> new Partner(PartnerId.of(partnerDO.getId()),new PartnerCode(partnerDO.getCode()), new PartnerName(partnerDO.getName())))
                .findFirst();
    }
}
