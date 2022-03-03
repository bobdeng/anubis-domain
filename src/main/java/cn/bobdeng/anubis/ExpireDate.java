package cn.bobdeng.anubis;

public class ExpireDate {
    private Long expireAt;

    public ExpireDate(Long expireAt) {
        this.expireAt = expireAt;
    }

    public static ExpireDate empty() {
        return new ExpireDate(null);
    }

    public boolean isBiggerThan(long currentTimeMillis) {
        if (this.expireAt == null) {
            return true;
        }
        return this.expireAt > currentTimeMillis;
    }

    public Long get() {
        return expireAt;
    }
}
