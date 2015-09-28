package org.eclipse.emf.parsley.web.tools.templates;

import org.eclipse.emf.parsley.edit.ui.provider.TableViewerContentProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class ParsleyWebGuiceModule extends EmfParsleyGuiceModuleGen {

    public ParsleyWebGuiceModule() {
        super(null);
    }

    public ParsleyWebGuiceModule(AbstractUIPlugin plugin) {
        super(plugin);
    }

    public Class<? extends IContentProvider> bindIContentProvider() {
        return TableViewerContentProvider.class;
    }
}
