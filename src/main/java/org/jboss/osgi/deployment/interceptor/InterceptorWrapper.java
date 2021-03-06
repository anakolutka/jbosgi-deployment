/*
 * #%L
 * JBossOSGi Deployment
 * %%
 * Copyright (C) 2010 - 2012 JBoss by Red Hat
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
/*
 * JBoss, Home of Professional Open Source
 * Copyright 2005, JBoss Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.osgi.deployment.interceptor;

import static org.jboss.osgi.deployment.DeploymentMessages.MESSAGES;

import java.util.HashSet;
import java.util.Set;

import org.jboss.osgi.spi.AttachmentKey;


/**
 * A wrapper around lifecycle interceptors.
 *
 * @author thomas.diesler@jboss.com
 * @since 15-Oct-2009
 */
public class InterceptorWrapper implements LifecycleInterceptor {
    private LifecycleInterceptor delegate;

    public InterceptorWrapper(LifecycleInterceptor delegate) {
        if (delegate == null)
            throw MESSAGES.illegalArgumentNull("delegate");

        this.delegate = delegate;
    }

    public Set<AttachmentKey<?>> getInput() {
        return delegate.getInput();
    }

    public Set<AttachmentKey<?>> getOutput() {
        return delegate.getOutput();
    }

    public int getRelativeOrder() {
        return delegate.getRelativeOrder();
    }

    public void invoke(int state, InvocationContext context) throws LifecycleInterceptorException {
        delegate.invoke(state, context);
    }

    public String toLongString() {
        String classToken = delegate.getClass().getSimpleName();

        Set<String> input = null;
        if (getInput() != null) {
            input = new HashSet<String>();
            for (AttachmentKey<?> aux : getInput())
                input.add(aux.getType().getSimpleName());
        }

        Set<String> output = null;
        if (getOutput() != null) {
            output = new HashSet<String>();
            for (AttachmentKey<?> aux : getOutput())
                output.add(aux.getType().getSimpleName());
        }

        return "[" + classToken + ",order=" + getRelativeOrder() + ",input=" + input + ",output=" + output + "]";
    }

    @Override
    public String toString() {
        String className = delegate.getClass().getName();
        return "[" + className + ",order=" + getRelativeOrder() + "]";
    }
}