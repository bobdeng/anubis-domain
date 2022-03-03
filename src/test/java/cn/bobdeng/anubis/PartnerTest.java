package cn.bobdeng.anubis;

import cn.bobdeng.dummydao.AutoIntegerIdGenerator;
import cn.bobdeng.dummydao.DummyDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class PartnerTest {
    private DummyDao<PartnerDO, Integer> partnerDao;

    @BeforeEach
    public void setup() {
        partnerDao = new DummyDao<>(PartnerDO.class, "id", new AutoIntegerIdGenerator());
        Partners.partnerRepository = new PartnerRepositoryImpl(partnerDao);
    }

    @Test
    public void should_has_1_partner_when_new_partner() {
        Partners partners = new Partners();
        PartnerCode partnerCode = new PartnerCode("1000");
        PartnerName partnerName = new PartnerName("合作伙伴名字");
        partners.newPartner(partnerCode, partnerName);

        assertThat(partnerDao.all().size(), is(1));
        PartnerDO partnerDO = partnerDao.all().get(0);
        assertThat(partnerDO.getCode(), is("1000"));
        assertThat(partnerDO.getName(), is("合作伙伴名字"));
    }
}
