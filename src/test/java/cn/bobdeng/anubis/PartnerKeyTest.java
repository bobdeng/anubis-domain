package cn.bobdeng.anubis;

import cn.bobdeng.dummydao.AutoIntegerIdGenerator;
import cn.bobdeng.dummydao.DummyDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class PartnerKeyTest {
    public static final String CODE = "123456";
    private DummyDao<PartnerDO, Integer> partnerDao;
    private Partner partner;
    private String key = """
            -----BEGIN PUBLIC KEY-----
            MIIBojANBgkqhkiG9w0BAQEFAAOCAY8AMIIBigKCAYEA2DILCOJwTTM45rAw81bn
            wIfzYHNXCgc5C0RFBu4IoWa13Y9JzBis8gtWbbOp/rIUwFmDrmUTPRmORM+1+zjO
            ZGnBlxn28OIh+DuloExe91rLORhON4FNcCIosq7Oa2ntbpXQyRshmvwHGPND6IXP
            A0eHMP+Zs/uY7UsRudP9u2kaLR5Y/eH0hQTbM9lI5P1t2PVSdXvKKhzOyf5aeIqH
            zI8DMQLMx0TZc6r4GLv5p1TMxwnyYoVob68zC/uQ6s2moOKUwfErIcTZdobE/jYS
            0fyNrsPJv3k75qvamt8P4Q/coYEo8UmViRnsMJtAQyz7+1eK+k1W8NFPJHSBegzC
            l66AYOEPlH4RJB8ie0kqziaen6AXJNKI75mvD6InaJ1XLzQlFKYV4KziV+GuWhZL
            jBwfEog+wdQ5smbkL66g0GYq3hwoMNl6PW9h/ohijLOn+Wj2Q8RfroslcUETzpt7
            DEgqrKHfe4zwcsTh/hOMvkQBh4bmfuODiWRZ2C5b5KwRAgMBAAE=
            -----END PUBLIC KEY-----
            """;
    private DummyDao<PartnerKeyDO, Integer> partnerKeyDao;

    @BeforeEach
    public void setup() {
        partnerDao = new DummyDao<>(PartnerDO.class, "id", new AutoIntegerIdGenerator());
        Partners.partnerRepository = new PartnerRepositoryImpl(partnerDao);
        partnerDao.save(PartnerDO.builder()
                .code(CODE).build());
        partner = Partners.partnerRepository.findByCode(new Partners(), PartnerCode.of(CODE)).orElseThrow();
        partnerKeyDao = new DummyDao<>(PartnerKeyDO.class, "id", new AutoIntegerIdGenerator());
        Partners.partnerKeyRepository = new PartnerKeyRepositoryImpl(partnerKeyDao);
    }

    @Test
    public void should_has_1_key_when_new_key() {
        AccessKey accessKey = new AccessKey(this.key);
        partner.newKey(accessKey, ExpireDate.empty());

        assertThat(partnerKeyDao.all().size(), is(1));
        PartnerKeyDO accessKeyDO = partnerKeyDao.all().get(0);
        assertThat(accessKeyDO.getKey(), is(key));
    }

    @Test
    public void should_verify_pass_when_has_valid_key() {
        partnerKeyDao.save(PartnerKeyDO.builder()
                .partnerId(partner.id())
                .key(key)
                .build());
        Content content = new Content("1639744351000{\"\"}");
        Signature signature = new Signature(Base16.decode("CDAD26C4D3E3E850241C426F681984B983FF97F801D3C9F347ADB3301DAC0753216CCE60B8D921D4564621F252492426A128CACDC30B4F50090745A454B69CC9A593B6CCCD60DA6DA136804757768BB8A33569BA8EF32C588332F79D056CF23E268606DD17CC3DF7488BA96D588D5DB545D0A3B419D7E80C4BCF048EDA62E2918ABDD5951DB2162586FAFD8FE914B4C387647385B1A21DDCD3B021A9BE2231A36A652E750DBBA3E1DD5F7DFD9EB4700E2F8C208B3F601526F809CB3A49B9D86A0EA2370346C74D5F639A7FA5DEED155B79D15A6C84DB9FE4BEDA50D59217053FAFCA99EE8278815B457872039A29B4622A32CCD9DEAC83E7B9E194D9107729C46397EEA75E01C906616CFE1CF92D9990CCE9BC35F7AF4270672DBC07D4EBAE0DCDB722D4C360F46FF026498932A54F732FBD83EA768FD6346AEFB9D8B49E15E8FED230DC423040481589CC379788FD1144338C13688A3B060BC7EF028768C61122526F30544F2EC97CD860AFEFF9E653E039A7B1053802895D34EEA01B369723"));
        boolean result = partner.verifySign(content, signature);
        assertThat(result, is(true));
    }

    @Test
    public void should_verify_fail_when_has_invalid_key() {
        partnerKeyDao.save(PartnerKeyDO.builder()
                .partnerId(partner.id())
                .key(key)
                .build());
        Content content = new Content("1639744351001{\"\"}");
        Signature signature = new Signature(Base16.decode("CDAD26C4D3E3E850241C426F681984B983FF97F801D3C9F347ADB3301DAC0753216CCE60B8D921D4564621F252492426A128CACDC30B4F50090745A454B69CC9A593B6CCCD60DA6DA136804757768BB8A33569BA8EF32C588332F79D056CF23E268606DD17CC3DF7488BA96D588D5DB545D0A3B419D7E80C4BCF048EDA62E2918ABDD5951DB2162586FAFD8FE914B4C387647385B1A21DDCD3B021A9BE2231A36A652E750DBBA3E1DD5F7DFD9EB4700E2F8C208B3F601526F809CB3A49B9D86A0EA2370346C74D5F639A7FA5DEED155B79D15A6C84DB9FE4BEDA50D59217053FAFCA99EE8278815B457872039A29B4622A32CCD9DEAC83E7B9E194D9107729C46397EEA75E01C906616CFE1CF92D9990CCE9BC35F7AF4270672DBC07D4EBAE0DCDB722D4C360F46FF026498932A54F732FBD83EA768FD6346AEFB9D8B49E15E8FED230DC423040481589CC379788FD1144338C13688A3B060BC7EF028768C61122526F30544F2EC97CD860AFEFF9E653E039A7B1053802895D34EEA01B369723"));
        boolean result = partner.verifySign(content, signature);
        assertThat(result, is(false));
    }
}
