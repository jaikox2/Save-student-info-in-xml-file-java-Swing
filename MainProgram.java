
import java.awt.BorderLayout;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
//import ReadXml;


public class MainProgram {
	public MainProgram() throws ParserConfigurationException{
		JFrame frame = new JFrame("Student data");
		frame.setSize(500,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
		
		JLabel titleLabel = new JLabel("MY INFORMATION");
		frame.add(titleLabel);
		
		
		
		JPanel inputPane = new JPanel();
		inputPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		 
		c.gridx = 0;
		    c.gridy = GridBagConstraints.RELATIVE;
		    c.gridwidth = 1;
		    c.gridheight = 1;
		    c.insets = new Insets(2, 2, 2, 2);
		    c.anchor = GridBagConstraints.EAST;
		
		
		
		JLabel idLabel = new JLabel("Student ID:");
		JTextField idField = new JTextField(20);
		
		JLabel nameLabel = new JLabel("Name:");
		JTextField nameField = new JTextField(20);
		
		JLabel surnameLabel = new JLabel("Surname :");
		JTextField surenameField = new JTextField(20);
		
		JLabel addressLabel = new JLabel("Address :");
		JTextField addressField = new JTextField(20);
		
		
		
		inputPane.add(idLabel, c);
		inputPane.add(nameLabel, c);
		inputPane.add(surnameLabel, c);
		inputPane.add(addressLabel, c);
	    c.gridx = 1;
	    c.gridy = 0;
	    c.weightx = 1.0;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.anchor = GridBagConstraints.CENTER;
	    
	    inputPane.add(idField, c);
	    c.gridx = 1;
	    c.gridy = GridBagConstraints.RELATIVE;
		inputPane.add(nameField, c);
		inputPane.add(surenameField, c);
		inputPane.add(addressField, c);
		
		//function SaveFlie
		JButton saveButton = new JButton("Save");
		saveButton.setBounds(171, 95, 100, 23);
		saveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				 try {
   
						DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
						DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

						// root elements
						Document doc = docBuilder.newDocument();
						Element rootElement = doc.createElement("root");
						doc.appendChild(rootElement);

						// student elements
						Element Student = doc.createElement("STUDENT");
						rootElement.appendChild(Student);

						
						Element id = doc.createElement("id");
						id.appendChild(doc.createTextNode(idField.getText()));
						Student.appendChild(id);

						// first name elements
						Element firstname = doc.createElement("firstname");
						firstname.appendChild(doc.createTextNode(nameField.getText()));
						Student.appendChild(firstname);

						// last name elements
						Element lastname = doc.createElement("lastname");
						lastname.appendChild(doc.createTextNode(surenameField.getText()));
						Student.appendChild(lastname);

						
						

						// Address elements
						Element address = doc.createElement("Address");
						address.appendChild(doc.createTextNode(addressField.getText()));
						Student.appendChild(address);

						// write the content into xml file
						TransformerFactory transformerFactory = TransformerFactory.newInstance();
						Transformer transformer = transformerFactory.newTransformer();
						DOMSource source = new DOMSource(doc);
						StreamResult result = new StreamResult(new File("file.xml"));
						transformer.transform(source, result);

						System.out.println("File saved!");

					  } catch (ParserConfigurationException pce) {
						pce.printStackTrace();
					  } catch (TransformerException tfe) {
						tfe.printStackTrace();
					  }
					}
		});
		
		
		//Function Show data
		JButton showButton = new JButton("Show");
		showButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){				
				new ReadXml();
			}	
		});
		
		//add page
		frame.getContentPane().add(inputPane);
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(saveButton);
		buttonPanel.add(showButton);
		frame.add(buttonPanel,BorderLayout.NORTH);
		frame.setVisible(true);
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		MainProgram mp = new MainProgram();
	}
	
}
