package controller.mainUI;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import view.mainUI.ApplicationFrame;

public class FrameResizeListener extends ComponentAdapter {

	private ApplicationFrame frame;

	public FrameResizeListener(ApplicationFrame frame) {
		this.frame = frame;
	}

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		this.frame.revalidate();
	}

}
