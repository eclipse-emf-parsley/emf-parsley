/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Vincenzo Caselli - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.web.tools;

import org.eclipse.wst.common.project.facet.core.IActionConfigFactory;

/**
 * Wizard class for holding user choices
 * 
 * @author Vincenzo Caselli
 * 
 */
public class ParsleyWebFacetInstallConfig {

    public enum PERSISTENCE_OPTION {
        NONE, TENEO, CDO
    };

    private PERSISTENCE_OPTION persistenceOption;

    public static final class Factory implements IActionConfigFactory {
        public Object create() {
            return new ParsleyWebFacetInstallConfig();
        }
    }

    public PERSISTENCE_OPTION getPersistenceOption() {
        return persistenceOption;
    }

    public void setPersistenceOption(PERSISTENCE_OPTION persistenceOption) {
        this.persistenceOption = persistenceOption;
    }


}
