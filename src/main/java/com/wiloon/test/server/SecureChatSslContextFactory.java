package com.wiloon.test.server;

import io.netty.util.internal.SystemPropertyUtil;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.security.KeyStore;

/**
 * Created by Yupeg.LV on 17/7/4.
 */
public class SecureChatSslContextFactory {
    private static final String PROTOCOL = "SSL";
    private static final SSLContext SERVER_CONTEXT;


    static {
        String algorithm = SystemPropertyUtil.get("ssl.KeyManagerFactory.algorithm");
        if (algorithm == null) {
            algorithm = "SunX509";
        }

        SSLContext serverContext = null;
        try {
            String password = "password0";
            KeyStore ks = KeyStore.getInstance("JKS");
            ks.load(new FileInputStream("/home/wiloon/projects/nettyx/src/main/resources/certs/server.jks"), password.toCharArray());
            KeyStore tks = KeyStore.getInstance("JKS");
            tks.load(new FileInputStream("/home/wiloon/projects/nettyx/src/main/resources/certs/serverTrust.jks"), password.toCharArray());
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(algorithm);
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            kmf.init(ks, password.toCharArray());
            tmf.init(tks);
            serverContext = SSLContext.getInstance(PROTOCOL);
            serverContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SERVER_CONTEXT = serverContext;
    }

    public static SSLContext getServerContext() {
        return SERVER_CONTEXT;
    }
}
