package com.wiloon.test.client;

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
        SSLContext clientContext;
        try {
            clientContext = SSLContext.getInstance(PROTOCOL);
            clientContext.init(null, null, null);
        } catch (Exception e) {
            throw new Error(e);
        }

        CLIENT_CONTEXT = clientContext;
    }

    public static SSLContext getClientContext() {
        return CLIENT_CONTEXT;
    }
}
