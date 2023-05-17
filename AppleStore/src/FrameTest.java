import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrameTest extends JFrame {
	JFrame frame;
	JPanel bannerPanel, typePanel, cartPanel, mainPanel;
	public FrameTest() {
		super("MAX NIght Fury");
		setBounds(100, 0, 1440, 1024); // 창크기를 설정
		
		bannerPanel=new JPanel();
		bannerPanel.setLayout(new BoxLayout(bannerPanel, BoxLayout.X_AXIS));
		bannerPanel.add(new JLabel("LOGO").CENTER);
		
		typePanel=new JPanel();
		typePanel.set
		
		setVisible(true); // 창보이기 값 설정

	
		
		// X 버튼을 누르면 프로그램이 종료 됨
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// 창 닫기
				// 프레임 종료
				dispose();
				// 프로세스 종료
				System.exit(0);
			}

		});
	}

	public static void main(String args[]) {
		// 텍스트 필드 생성자
		JFrame f = new FrameTest(); // f라는 Frame 생성
	}
}
