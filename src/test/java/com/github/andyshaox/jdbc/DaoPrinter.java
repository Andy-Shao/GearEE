package com.github.andyshaox.jdbc;

import org.objectweb.asm.util.ASMifier;

public class DaoPrinter {

    public static void main(String[] args) throws Exception {
        ASMifier.main(new String[] { "com.github.andyshaox.jdbc.UserDaoEntity" });
    }
}
