package cn.bobdeng.anubis;

import lombok.Getter;

public class Signature {
    @Getter
    private byte[] signature;

    public Signature(byte[] signature) {
        this.signature = signature;
    }
}
