package UML.CanvasObject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import UML.Canvas;

public class ClassBasic extends BasicObject {

	private JPanel attribute_field = new JPanel();
	private JPanel method_field = new JPanel();

	private JPanel attribute_content = new JPanel();
	private JPanel method_content = new JPanel();

	private int lineHeight = 20; // Approx height per line

	public ClassBasic(Canvas canvas, Point startPoint) {
		super(canvas, startPoint, new Dimension(160, 140));

		this.setBorder(new TitledBorder("Class"));

		this.add(this.classTitle, BorderLayout.NORTH);

		// --Attribute Field--
		this.attribute_field.setLayout(new BorderLayout());
		this.attribute_field.setBackground(new Color(255, 253, 208));
		JLabel attribute_label = new JLabel("--Attribute Field--");
		attribute_label.setHorizontalAlignment(JLabel.CENTER);
		attribute_label.setFont(this.displayFont);
		this.attribute_field.add(attribute_label, BorderLayout.NORTH);

		attribute_content.setLayout(new BoxLayout(attribute_content, BoxLayout.Y_AXIS));
		attribute_content.setBackground(new Color(255, 253, 208));
		this.attribute_field.add(attribute_content, BorderLayout.CENTER);

		this.attribute_field.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editAttributes();
			}
		});

		this.add(this.attribute_field, BorderLayout.CENTER);

		// --Method Field--
		this.method_field.setLayout(new BorderLayout());
		this.method_field.setBackground(new Color(245, 245, 220));
		JLabel method_label = new JLabel("--Method Field--");
		method_label.setHorizontalAlignment(JLabel.CENTER);
		method_label.setFont(this.displayFont);
		this.method_field.add(method_label, BorderLayout.NORTH);

		method_content.setLayout(new BoxLayout(method_content, BoxLayout.Y_AXIS));
		method_content.setBackground(new Color(245, 245, 220));
		this.method_field.add(method_content, BorderLayout.CENTER);

		this.method_field.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editMethods();
			}
		});

		this.add(this.method_field, BorderLayout.SOUTH);
	}

	private void editAttributes() {
		String input = JOptionPane.showInputDialog(this, "Enter new attributes (each on a new line):", "Edit Attributes", JOptionPane.PLAIN_MESSAGE);
		if (input != null && !input.isEmpty()) {
			String[] attributes = input.split("\n");
			for (String attr : attributes) {
				JLabel label = new JLabel(attr.trim());
				label.setFont(this.displayFont);
				attribute_content.add(label);
			}
			attribute_content.revalidate();
			attribute_content.repaint();
			resizeClassBox();
		}
	}

	private void editMethods() {
		String input = JOptionPane.showInputDialog(this, "Enter new methods (each on a new line):", "Edit Methods", JOptionPane.PLAIN_MESSAGE);
		if (input != null && !input.isEmpty()) {
			String[] methods = input.split("\n");
			for (String method : methods) {
				JLabel label = new JLabel(method.trim());
				label.setFont(this.displayFont);
				method_content.add(label);
			}
			method_content.revalidate();
			method_content.repaint();
			resizeClassBox();
		}
	}

	private void resizeClassBox() {
		// Estimate size based on number of components
		int titleHeight = 30;
		int attrHeight = attribute_content.getComponentCount() * lineHeight;
		int methodHeight = method_content.getComponentCount() * lineHeight;
		int totalHeight = titleHeight + attrHeight + methodHeight + 60; // Padding

		this.setPreferredSize(new Dimension(160, totalHeight));
		this.revalidate();
		this.repaint();
	}
}
