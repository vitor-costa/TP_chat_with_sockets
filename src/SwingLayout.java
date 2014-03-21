import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

class SwingLayout implements LayoutManager {

    	    public SwingLayout() {
    	    }

    	    public void addLayoutComponent(String name, Component comp) {
    	    }

    	    public void removeLayoutComponent(Component comp) {
    	    }

    	    public Dimension preferredLayoutSize(Container parent) {
    	        Dimension dim = new Dimension(0, 0);
    	        Insets insets = parent.getInsets();
    	        dim.width = 592 + insets.left + insets.right;
    	        dim.height = 500 + insets.top + insets.bottom;

    	        return dim;
    	    }

    	    public Dimension minimumLayoutSize(Container parent) {
    	        Dimension dim = new Dimension(0, 0);
    	        return dim;
    	    }

    	    public void layoutContainer(Container parent) {
    	        Insets insets = parent.getInsets();

    	        Component c;
    	        c = parent.getComponent(0);//title
    	        if (c.isVisible()) {c.setBounds(insets.left+46,insets.top+10,280,20);}
    	        c = parent.getComponent(1);//buttonLoad
    	        if (c.isVisible()) {c.setBounds(insets.left+46,insets.top+50,500,300);}
    	        c = parent.getComponent(2);//buttonNew
   	        	if (c.isVisible()) {c.setBounds(insets.left+46,insets.top+350,500,20);}
   	        	c = parent.getComponent(3);//buttonExit
    	        if (c.isVisible()) {c.setBounds(insets.left+46,insets.top+370,500,20);}
    	        c = parent.getComponent(4);//buttonExit
    	        if (c.isVisible()) {c.setBounds(insets.left+416,insets.top+390,128,20);}
    	    }
    	}