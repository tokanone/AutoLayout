package uikit.autolayout.uiobjects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Created by michaelschloss on 2/1/17.
 *
 * Custom subclass of JFrame.  Subclass ALJFrame to get automatic AutoLayout inheritance.
 */
public class ALJFrame extends JFrame implements ComponentListener
{
	@Override
	public Component add(Component comp)
	{
		Component component = super.add(comp);
		if (comp instanceof ALJPanel)
		{
			((ALJPanel) comp).layoutSubviews();
		}
		return component;
	}

	public ALJFrame(String title)
	{
		super(title);
		addComponentListener(this);
	}

	@Override
	public void componentResized(ComponentEvent e)
	{
		if (!this.isVisible()) return;
		getContentPane().revalidate();
		getContentPane().repaint();
		for (Component component : getContentPane().getComponents())
		{
			component.setBounds(0, component.getBounds().y, getContentPane().getWidth(), getContentPane().getHeight() - component.getBounds().y);
			if (component instanceof ALJPanel)
			{
				((ALJPanel)component).layoutSubviews();
			}
			else if (component instanceof JPanel)
			{
				((ComponentListener)component).componentResized(null);
			}
		}
	}

	@Override
	public void componentMoved(ComponentEvent e) { }

	@Override
	public void componentShown(ComponentEvent e)
	{
		for (Component component : getContentPane().getComponents())
		{
			component.setBounds(0, component.getBounds().y, getContentPane().getWidth(), getContentPane().getHeight() - component.getBounds().y);
			if (component instanceof ALJPanel)
			{
				((ALJPanel)component).layoutSubviews();
			}
			else if (component instanceof JPanel)
			{
				((ComponentListener)component).componentResized(null);
			}
		}
	}

	@Override
	public void componentHidden(ComponentEvent e) { }
}
