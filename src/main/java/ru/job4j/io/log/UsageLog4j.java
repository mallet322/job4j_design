package ru.job4j.io.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte var0 = 0;
        short var1 = 1;
        int var2 = 2;
        long var3 = 3L;
        float var4 = 4f;
        double var5 = 5d;
        boolean var6 = true;
        char var7 = '7';
        LOG.debug("User variables: "
                          + "var0: {},"
                          + " var1: {},"
                          + " var2: {},"
                          + " var3: {},"
                          + " var4: {},"
                          + " var5: {},"
                          + " var6: {},"
                          + " var7: {}",
                  var0, var1, var2, var3, var4, var5, var6, var7);
    }

}
