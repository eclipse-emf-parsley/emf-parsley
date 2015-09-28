/*******************************************************************************
 * Copyright (c) 2014 RCP Vision (http://www.rcp-vision.com) and others. All rights reserved. This
 * program and the accompanying materials are made available under the terms of the Eclipse Public
 * License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.web.servlets;

import org.eclipse.core.databinding.observable.Realm;

/**
 * Simple realm implementation that will set itself as default when constructed, this will be used
 * for unit testing. Invoke {@link #dispose()} to remove the realm from being the default. Does not
 * support asyncExec(...).
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 */
public class DefaultRealm extends Realm {
    private Realm previousRealm;

    public DefaultRealm() {
        previousRealm = super.setDefault(this);
    }

    /**
     * @return always returns true
     */
    @Override
    public boolean isCurrent() {
        return true;
    }

    @Override
    protected void syncExec(Runnable runnable) {
        runnable.run();
    }

    /**
     * @throws UnsupportedOperationException
     */
    @Override
    public void asyncExec(Runnable runnable) {
        throw new UnsupportedOperationException("asyncExec is unsupported");
    }

    /**
     * Removes the realm from being the current and sets the previous realm to the default.
     */
    public void dispose() {
        if (getDefault() == this) {
            setDefault(previousRealm);
        }
    }
}
