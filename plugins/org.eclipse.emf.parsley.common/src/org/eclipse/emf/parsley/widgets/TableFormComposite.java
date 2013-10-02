package org.eclipse.emf.parsley.widgets;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.builders.TableViewerBuilder;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;

import com.google.inject.Inject;

/**
 * A generic composite with a Table and a Form with details of the selected
 * object in the tree.
 * 
 * @author Francesco Guidieri
 * 
 */
public class TableFormComposite extends AbstractMasterDetailComposite {
	
	private TableViewerBuilder tableViewerBuilder;
	private TableViewer tableViewer;
	private boolean autoBuild;
	
	public TableFormComposite(Composite parent, int style, boolean autoBuild) {
		super(parent, style, SWT.VERTICAL, new int[0]);
		this.autoBuild=autoBuild;
	}

	public TableFormComposite(Composite parent, int style) {
		this(parent, style, SWT.VERTICAL, new int[0], false);
	}
	
	public TableFormComposite(Composite parent, int style, int sashStyle,int[] weights) {
		this(parent, style, sashStyle, weights, false);
		
	}
	
	public TableFormComposite(Composite parent, int style, int sashStyle,int[] weights, boolean autoBuild) {
		super(parent, style);
		this.autoBuild=autoBuild;
	}
	
	protected StructuredViewer createViewer(Composite parent) {
		tableViewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
		return tableViewer;
	}
	
	public void buildTable(EClass type, IStructuredContentProvider contentProvider){
		removeExistingColumns();
		tableViewerBuilder.build(tableViewer, type,  contentProvider);
	}

	public TableViewerBuilder getTableViewerBuilder() {
		return tableViewerBuilder;
	}

	@Inject
	public void setTableViewerBuilder(TableViewerBuilder tableViewerBuilder) {
		this.tableViewerBuilder = tableViewerBuilder;
	}
	
	
	@Override
	public void update(Object input) {
		if(autoBuild){
			removeExistingColumns();
		}
		if (input instanceof List){
			final List<?> elements=(List<?>) input;
			IStructuredContentProvider contentProvider=new ArrayContentProvider();
			if(elements.size()>0){
				EObject eObject = (EObject) elements.get(0);
				tableViewerBuilder.build(tableViewer, eObject.eClass(), contentProvider);
				tableViewer.setContentProvider(contentProvider);
				//TODO
				PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
					public void run() {
						tableViewer.setInput(elements);
					}
				});
			}else{				
				viewerInitializer.initialize(tableViewer,input,contentProvider,null);
			}
			pagebook.showPage(tableViewer.getControl());
		}else super.update(input);
	}

	private void removeExistingColumns() {
		for (int i=tableViewer.getTable().getColumns().length;i>0;i--) {
			tableViewer.getTable().getColumns()[i-1].dispose();
		}
		tableViewer.getTable().pack();
	}

	public void buildTable(EClass eType) {
		buildTable(eType, new ArrayContentProvider());
	}
	
}
