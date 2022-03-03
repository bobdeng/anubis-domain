package cn.bobdeng.anubis;

import cn.bobdeng.dummydao.DummyDao;

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
}
