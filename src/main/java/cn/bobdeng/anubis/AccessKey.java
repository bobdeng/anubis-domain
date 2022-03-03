package cn.bobdeng.anubis;

import lombok.Getter;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.signers.RSADigestSigner;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;

import java.io.IOException;
import java.io.StringReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class AccessKey {
    @Getter
    private String key;

    public AccessKey(String key) {

        this.key = key;
    }

    public boolean verifySign(String raw, byte[] sign) {
        try {
            RSAPublicKey rsaPublicKey = this.getRsaPublicKey(key);
            RSADigestSigner signer = this.getRsaDigestSigner(raw, rsaPublicKey);
            return signer.verifySignature(sign);
        } catch (Exception var6) {
            var6.printStackTrace();
            return false;
        }
    }

    private RSADigestSigner getRsaDigestSigner(String rawStr, RSAPublicKey rsaPublicKey) {
        RSADigestSigner signer = new RSADigestSigner(new SHA512Digest());
        signer.init(false, new RSAKeyParameters(false, rsaPublicKey.getModulus(), rsaPublicKey.getPublicExponent()));
        byte[] raw = rawStr.getBytes();
        signer.update(raw, 0, raw.length);
        return signer;
    }

    private RSAPublicKey getRsaPublicKey(String pubkey) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        PemReader pemReader = new PemReader(new StringReader(pubkey));
        PemObject pemObject = pemReader.readPemObject();
        X509EncodedKeySpec spec = new X509EncodedKeySpec(pemObject.getContent());
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) kf.generatePublic(spec);
    }

    static {
        Security.addProvider(new BouncyCastleProvider());
    }
}
