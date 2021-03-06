package views;

import java.util.Date;

import javax.security.auth.Refreshable;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.SharedImages;
import org.eclipse.ui.part.ViewPart;
import entity.DataBase;
import entity.Model;
import entity.Requirement;
import entity.RowRequirement;
import jdbc.ConnectHelper;

/**
 * 
* @ClassName: NavigationView  
* @Description: 左侧的导航栏视图
* @author Binver131  
* @date 2020年4月14日
 */
public class NavigationView extends ViewPart {
	public static final String ID = "Auto-TestCase-generate-tool.navigationView";
	private TreeViewer viewer;
	private MenuManager fMenuMgr; 
	private Menu fMenu; 

	
	class ViewContentProvider implements IStructuredContentProvider, ITreeContentProvider{

		@Override
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		@Override
		public void dispose() {
		}

		@Override
		public Object[] getElements(Object parent) {
			return getChildren(parent);
		}

		@Override
		public Object getParent(Object child) {
			if (child instanceof Model) {
				return ((Model)child).getParent();
			}
			if (child instanceof Requirement) {
				return ((Requirement)child).getParent();
			}
			if (child instanceof RowRequirement) {
				return ((RowRequirement)child).getParent();
			}
			return null;
		}

		@Override
		public Object[] getChildren(Object parent) {
			if (parent instanceof DataBase) {
				return ((DataBase)parent).getChildren();
			}
			if (parent instanceof Model) {
				return ((Model)parent).getChildren();
			}
			if (parent instanceof RowRequirement) {
				return ((RowRequirement)parent).getChildren();
			}
			return new Object[0];
		}

		@Override
		public boolean hasChildren(Object parent) {
			if (parent instanceof DataBase)
				return ((DataBase)parent).hasChildren();
			if (parent instanceof Model)
				return ((Model)parent).hasChildren();
			if (parent instanceof RowRequirement)
				return ((RowRequirement)parent).hasChildren();
			return false;
		}

	}

	class ViewLabelProvider extends LabelProvider {

		@Override
		public String getText(Object obj) {
			return obj.toString();
		}

		@Override
		public Image getImage(Object obj) {
			String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
			if (obj instanceof Model)
				imageKey = ISharedImages.IMG_OBJS_DND_STACK_MASK;
			else if (obj instanceof RowRequirement)
				imageKey = ISharedImages.IMG_OBJ_FOLDER;
			
			return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
		}
	}

	/**
	 * 
	* @Title: initDataBase  
	* @Description: 初始化导航栏数据，从数据库中提取信息
	* @return DataBase    返回从数据库里提取好的库结构
	* @throws
	 */
	private DataBase initDataBase() {
		DataBase root = ConnectHelper.getDataBaseInstance();		
		return root;
	}

	@Override
	public void createPartControl(Composite parent) {
		MenuManager menuManager = new MenuManager();

	    Menu menu = menuManager.createContextMenu(parent);

	    parent.setMenu(menu);
	    getSite().registerContextMenu(menuManager, null);
	    
	   
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setInput(initDataBase());		
		
		hookContextMenu();
		
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection is = (IStructuredSelection)event.getSelection();
				if( is.getFirstElement() instanceof Requirement) {
					try {
						PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(TestCasesView.ID,null,IWorkbenchPage.VIEW_VISIBLE);
					} catch (PartInitException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//viewer.getControl().setFocus();
				}
			}
		});
		
		
		
		
		getSite().setSelectionProvider(viewer);
			
	}

	private void hookContextMenu() { 
	       fMenuMgr = new MenuManager("#PopupMenu"); 
	       fMenuMgr.setRemoveAllWhenShown(true); 
	       fMenuMgr.addMenuListener(new IMenuListener() { 
	           public void menuAboutToShow(IMenuManager manager) {                
	           } 
	       }); 
	       fMenu = fMenuMgr.createContextMenu(viewer.getControl()); 
	 
	       viewer.getControl().setMenu(fMenu); 
	       getSite().registerContextMenu(fMenuMgr, viewer);              
	   }    
	
	public void refresh() {
		viewer.refresh();
	}
	
	
	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}