//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cn.bobdeng.anubis;

public class Base16 {
    private static char[] BASE_16_CHARS = "0123456789ABCDEF".toCharArray();

    public Base16() {
    }

    public static String encode(byte[] data) {
        String result = "";

        for(int i = 0; i < data.length; ++i) {
            result = result + BASE_16_CHARS[(data[i] & 240) >> 4];
            result = result + BASE_16_CHARS[data[i] & 15];
        }

        return result;
    }

    public static byte[] decode(String src) {
        byte[] data = new byte[src.length() / 2];

        for(int i = 0; i < data.length; ++i) {
            data[i] = (byte)Integer.parseInt(src.substring(i * 2, i * 2 + 2), 16);
        }

        return data;
    }
}
