package cn.bobdeng.anubis;

import lombok.Getter;

public class Content {
    @Getter
    private String content;

    public Content(String content) {

        this.content = content;
    }
}
