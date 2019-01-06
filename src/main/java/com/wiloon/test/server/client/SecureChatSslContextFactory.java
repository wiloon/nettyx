package com.wiloon.test.server.client;

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
    private static final SSLContext CLIENT_CONTEXT;

    static {
        String algorithm = SystemPropertyUtil.get("ssl.KeyManagerFactory.algorithm");
        if (algorithm == null) {
            algorithm = "SunX509";
        }

        SSLContext clientContext;
        try {
            String password = "password0";


            KeyStore tks2 = KeyStore.getInstance("JKS");
            tks2.load(new FileInputStream("/home/wiloon/projects/nettyx/src/main/resources/certs/serverTrust.jks"), password.toCharArray());
            KeyManagerFactory kmf2 = KeyManagerFactory.getInstance(algorithm);
            TrustManagerFactory tmf2 = TrustManagerFactory.getInstance("SunX509");

            tmf2.init(tks2);
            clientContext = SSLContext.getInstance(PROTOCOL);
            clientContext.init(null, tmf2.getTrustManagers(), null);
        } catch (Exception e) {
            throw new Error(e);
        }

        CLIENT_CONTEXT = clientContext;
    }

    public static SSLContext getClientContext() {
        return CLIENT_CONTEXT;
    }
}
