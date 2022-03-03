package cn.bobdeng.anubis;

public class PartnerId {
    private int id;

    public PartnerId(int id) {
        this.id = id;
    }

    public static PartnerId of(int id) {
        return new PartnerId(id);
    }

    public int getId() {
        return id;
    }
}
