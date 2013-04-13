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

import java.util.Collection;

import org.jboss.osgi.spi.Attachable;
import org.jboss.osgi.spi.AttachmentKey;
import org.jboss.osgi.vfs.VirtualFile;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * The context passed between Interceptors
 *
 * @author thomas.diesler@jboss.com
 * @since 27-May-2009
 */
public class AbstractInvocationContext implements InvocationContext {

    private Attachable attachments;
    private BundleContext systemContext;
    private VirtualFile root;
    private Bundle bundle;

    public AbstractInvocationContext(BundleContext systemContext, Bundle bundle, VirtualFile root, Attachable attachments) {
        if (systemContext == null)
            throw MESSAGES.illegalArgumentNull("context");
        if (bundle == null)
            throw MESSAGES.illegalArgumentNull("bundle");
        if (attachments == null)
            throw MESSAGES.illegalArgumentNull("attachments");

        this.systemContext = systemContext;
        this.root = root;
        this.bundle = bundle;
        this.attachments = attachments;
    }

    public BundleContext getSystemContext() {
        return systemContext;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public VirtualFile getRoot() {
        return root;
    }

    public <T> T putAttachment(AttachmentKey<T> key, T value) {
        return attachments.putAttachment(key, value);
    }

    public <T> T getAttachment(AttachmentKey<T> type) {
        return attachments.getAttachment(type);
    }

    public <T> T removeAttachment(AttachmentKey<T> clazz) {
        return attachments.removeAttachment(clazz);
    }
}