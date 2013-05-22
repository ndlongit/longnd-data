/**
 * Copyright (C) 2010 Sebastien Chassande-Barrioz
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.structis.vip.server.i18n;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.security.InvalidParameterException;
import java.util.Properties;

public abstract class GenericX implements InvocationHandler {

    protected final Properties properties = new Properties();
    protected final Class<?> itf;

    @Override
    public abstract Object invoke(Object proxy, Method method, Object[] args) throws Throwable;

    public GenericX(Class<?> _itf, String lang) throws IOException, InvalidParameterException {
        this.itf = _itf;
        this.fillProperties(this.itf, lang);
    }

    protected void fillProperties(Class<?> itf, String lang) throws IOException {
        for (Class<?> superItf : itf.getInterfaces()) {
            this.fillProperties(superItf, lang);
        }
        String suffix = lang == null ? "" : ("_" + lang);
        String baseName = itf.getName().replace('.', '/');
        InputStream in = this.load(baseName + suffix + ".properties");
        if (in == null) {
            in = this.load(baseName + ".properties");
        }
        if (in != null) {
            this.properties.load(new InputStreamReader(in, "UTF-8"));
        }
    }

    protected InputStream load(String s) {
        InputStream in = null;
        ClassLoader cl;
        cl = Thread.currentThread().getContextClassLoader();
        if (cl != null) {
            in = cl.getResourceAsStream(s);
        }
        if (in == null) {
            cl = this.getClass().getClassLoader();
            if (cl != null) {
                in = this.getClass().getClassLoader().getResourceAsStream(s);
            }
            if (in == null) {
                cl = ClassLoader.getSystemClassLoader();
                if (cl != null) {
                    in = cl.getResourceAsStream(s);
                }
            }
        }
        return in;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this;
    }

    @Override
    public int hashCode() {
        return this.properties.size();
    }

    public static boolean isA(Class<?> c1, Class<?> c2) {
        return c2.isAssignableFrom(c1);
    }
}
