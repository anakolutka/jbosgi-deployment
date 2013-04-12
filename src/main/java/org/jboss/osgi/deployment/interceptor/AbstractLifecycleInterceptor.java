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

import java.util.HashSet;
import java.util.Set;

import org.jboss.osgi.spi.AttachmentKey;

/**
 * An abstract implementation of a LifecycleInterceptor.
 *
 * @author thomas.diesler@jboss.com
 * @since 15-Oct-2009
 */
public abstract class AbstractLifecycleInterceptor implements LifecycleInterceptor {
    private Set<AttachmentKey<?>> input;
    private Set<AttachmentKey<?>> output;

    /**
     * Get default relative order
     * @return 1000
     */
    public int getRelativeOrder() {
        return LifecycleInterceptor.RELATIVE_ORDER_DEFAULT;
    }

    /**
     * Get the required set of inputs.
     *
     * @return null if there are no inputs required
     */
    public Set<AttachmentKey<?>> getInput() {
        return input;
    }

    /**
     * Get the provided set of outputs.
     *
     * @return null if there are no outputs provided
     */
    public Set<AttachmentKey<?>> getOutput() {
        return output;
    }

    /**
     * Add an input requirement.
     */
    public void addInput(AttachmentKey<?> in) {
        if (input == null)
            input = new HashSet<AttachmentKey<?>>();

        input.add(in);
    }

    /**
     * Add an output capability.
     */
    public void addOutput(AttachmentKey<?> out) {
        if (output == null)
            output = new HashSet<AttachmentKey<?>>();

        output.add(out);
    }
}