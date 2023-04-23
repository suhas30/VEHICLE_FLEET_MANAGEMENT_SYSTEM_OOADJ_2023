package VehicleGUI;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import AbstractFactoryPack.VehicleFactory;
import VehicleAgency.Bicycle;
import VehicleAgency.Frigate;
import VehicleAgency.Jeep;
import VehicleAgency.interfaceMarineVehicles;
import VehicleAgency.interfaceVehicle;

public class BicyclePanel extends JPanel implements VehiclePanelsInterface
{
	
	private static JLabel pic;
	private static ImageIcon[] img = {new ImageIcon("img\\bicycle1.JPG"),new ImageIcon("img\\bicycle2.png"),new ImageIcon("img\\bicycle3.jpg"),new ImageIcon()};
	private  ArrayList<JLabel> label = new ArrayList<JLabel>();
	private  ArrayList<JTextField> text = new ArrayList<JTextField>();
	private String[] colorName = {"Choose Color","Black","Blue", "Green","Yellow","Orange","Pink","Red","White"};
	private  JComboBox<String> colorCH = new JComboBox<>(colorName);
	private JComboBox comboBox;
	
	public BicyclePanel()
	{
		//set layout panel
		setLayout(null);
		setBounds(15, 280 , 600 , 700);
		
		//init label and text arrays
		JLabel MaxP = new JLabel("Max Passengers :");
		label.add(MaxP);
		JTextField MaxPText = new JTextField();
		text.add(MaxPText);
		JLabel MaxS = new JLabel("Max Speed : ");
		label.add(MaxS);
		JTextField	MaxSText = new JTextField();
		text.add(MaxSText);
		JLabel Model = new JLabel("Model : ");
		label.add(Model);
		JTextField ModelText = new JTextField();
		text.add(ModelText);
		JLabel typeRoad = new JLabel("Type Road : ");
		label.add(typeRoad);
		JTextField typeRoadText = new JTextField();
		text.add(typeRoadText);
		
		//init picture label
		pic = new JLabel();
		pic.setBounds(255, 0, 320, 380);
		
		//resize array image
		for(int i=0;i<img.length-1;i++)
			img[i] = resizeImage(img[i]);
		
		String[] names = {"Choose Image","Image 1","Image 2","Image 3"};
	    comboBox = new JComboBox(names);

	}
	/**
	 * set size to the image label
	 */
	public static ImageIcon resizeImage(ImageIcon img)
	{
		Image im = img.getImage();
		Image tmp =  im.getScaledInstance(320, 380, Image.SCALE_REPLICATE);
		img = new ImageIcon(tmp);
		return img;
	}
	/**
	 * arrange the labels and textField to the main panel
	 */
	public void arrangeLabel_Text()
	{
		int tmp = 10;
		for(int i =0;i<label.size();i++)
		{
			label.get(i).setBounds(0 , tmp , 130, 25);
			text.get(i).setBounds(130 , tmp , 100, 25);
			this.add(label.get(i));
			this.add(text.get(i));
			tmp+=30;
		}
	}
	/**
	 * return a panel to the main frame
	 */
	public Component PanelComp()
	{

		arrangeLabel_Text();
		comboBox.setBounds(0, 170 , 150, 20);

		comboBox.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				int i = comboBox.getSelectedIndex();
				if(i > 0)
				{
					pic.setIcon(img[i-1]);
					img[img.length-1] = img[i-1];
				}
				else
					pic.setIcon(null);
			}
		});
		colorCH.setBounds(0, 140, 150, 20);
		add(colorCH);
		this.add(pic);
	    this.add(comboBox);
		return this;
	}
	/**
	 * add a new vehicle to the lists (array vehicle , marine , and image)  -----------------------------------------------------------
	 */
	public boolean addVehicleToList() {
		if(pic.getIcon() != null)
		{	
			VehicleFactory factory = new VehicleFactory();
			interfaceVehicle Bic = factory.getNewVehicle("Bicycle", text ,null,colorCH);
			if(!Bic.equals(null)) {
				MainVehicleAgency.arrV.add(Bic);
				MainVehicleAgency.arrayImage.add(img[img.length-1]);
				MainVehicleAgency.arraySemaphoreLock.add((new Semaphore(1)));
				return true;
			}
			else return false;
		}	
		else 
			JOptionPane.showMessageDialog(null, "Choose Image!!");
		return false;	
	}
	/**
	 * reset the field on the panel after we add a vehicle
	 */
	public void resetField() {
		for (int i = 0; i < text.size(); i++) {
			text.get(i).setText("");
			colorCH.setSelectedIndex(0);
			comboBox.setSelectedIndex(0);
		}
	}
	/**
	 * set browse image on the label
	 */
	public void setBrowseImg(String nameImg) {
		img[img.length-1] = new ImageIcon(nameImg);
		img[img.length-1] = resizeImage(img[img.length-1]);
		pic.setIcon(img[img.length-1]);
		
	}
	/**
	 * help me to understand with which panel i work
	 */
	public String getRealName() {
		return "Bicycle";
	}
}
