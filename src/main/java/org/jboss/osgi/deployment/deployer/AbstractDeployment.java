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
package org.jboss.osgi.deployment.deployer;

import static org.jboss.osgi.deployment.DeploymentMessages.MESSAGES;

import java.io.Serializable;

import org.jboss.osgi.spi.AttachableSupport;
import org.osgi.framework.Version;

/**
 * An abstraction of a bundle deployment
 *
 * @author thomas.diesler@jboss.com
 * @since 27-May-2009
 */
public abstract class AbstractDeployment extends AttachableSupport implements Deployment, Serializable {
    private static final long serialVersionUID = -3918766495938169718L;

    private String location;
    private String symbolicName;
    private String version;
    private Integer startLevel;
    private boolean autoStart;
    private boolean update;

    public AbstractDeployment(String location, String symbolicName, Version version) {
        if (location == null)
            throw MESSAGES.illegalArgumentNull("location");
        if (version == null)
            version = Version.emptyVersion;

        // symbolicName can be null

        this.location = location;
        this.symbolicName = symbolicName;
        this.version = version.toString();
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public String getSymbolicName() {
        return symbolicName;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public Integer getStartLevel() {
        return startLevel;
    }

    @Override
    public void setStartLevel(Integer startLevel) {
        if (startLevel == null || startLevel < 1)
            throw MESSAGES.illegalArgumentStartLevel(startLevel);

        this.startLevel = startLevel;
    }

    @Override
    public boolean isAutoStart() {
        return autoStart;
    }

    @Override
    public void setAutoStart(boolean autoStart) {
        this.autoStart = autoStart;
    }

    @Override
    public boolean isBundleUpdate() {
        return update;
    }

    @Override
    public void setBundleUpdate(boolean update) {
        this.update = update;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractDeployment))
            return false;

        AbstractDeployment other = (AbstractDeployment) obj;
        boolean matchLocation = location.equals(other.location);
        boolean matchName = symbolicName == other.symbolicName || symbolicName.equals(other.symbolicName);
        boolean matchVersion = getVersion().equals(other.getVersion());
        return matchLocation && matchName && matchVersion;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return "[" + symbolicName + ":" + version + ",location=" + location + "]";
    }
}