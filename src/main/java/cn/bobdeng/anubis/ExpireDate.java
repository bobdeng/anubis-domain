package cn.bobdeng.anubis;

public class ExpireDate {
    private Long expireAt;

    public ExpireDate(Long expireAt) {
        this.expireAt = expireAt;
    }

    public static ExpireDate empty() {
        return new ExpireDate(null);
    }
}
