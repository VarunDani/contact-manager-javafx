package edu.utdallas.db.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import edu.utdallas.db.database.AddressUtility;
import edu.utdallas.db.database.PersonUtility;
import edu.utdallas.db.model.Address;
import edu.utdallas.db.model.Person;
import edu.utdallas.db.utility.DateUtility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;

/**
 * This Class is work as Controller of Java FX Utility 
 * All Actions and Events will Be performed from here 
 * 
 * 
 * @version 1.0
 * @since v1.0
 * @author Varun Dani
 *
 */
public class ContactManagerController  implements Initializable{

	
	//Main Observable List for two Table View
	private ObservableList<Person> personEntries =null; 
			
	private ObservableList<Address> contactEntries =null; 
	
	@FXML
	private TextField firstName;
	@FXML
	private TextField middleInitial;
	@FXML
	private TextField lastName;
	@FXML
	private DatePicker birthDate;
	@FXML
	private ComboBox<String> sex;
	
	
	@FXML
	private TextField address1;
	@FXML
	private TextField address2;
	@FXML
	private TextField city;
	@FXML
	private TextField state;
	@FXML
	private TextField country;
	@FXML
	private TextField zipCode;
	@FXML
	private TextField phoneNumber;
	@FXML
	private TextField emailId;

	
	//Person Table 
	@FXML
	private TableView<Person> personTable;
	
	@FXML
	private TableColumn<Person, String>  colFirstName;
	@FXML
	private TableColumn<Person, String>  colMiddleInitial;
	@FXML
	private TableColumn<Person, String>  colLastName;
	@FXML
	private TableColumn<Person, String>  colBirthDate;
	@FXML
	private TableColumn<Person, String>  colSex;
	
	//Address Table 
	@FXML
	private TableView<Address> addressTable;

	@FXML
	private TableColumn<Address, String> colAddress1;
	@FXML
	private TableColumn<Address, String> colAddress2;
	@FXML
	private TableColumn<Address, String> colCity;
	@FXML
	private TableColumn<Address, String> colState;
	@FXML
	private TableColumn<Address, String> colCountry;
	@FXML
	private TableColumn<Address, String> colZip;
	@FXML
	private TableColumn<Address, String> colPhoneNum;
	@FXML
	private TableColumn<Address, String> colEmailAdd;
	
	
//Commenting Previous Cell Factory and Using In Built Cell Factory 
/*	Callback<TableColumn<Person, String>, TableCell<Person, String>> cellFactory 
					= (TableColumn<Person, String> param) -> new EditingCellPerson();
    
	Callback<TableColumn<Address, String>, TableCell<Address, String>> cellFactoryAdd 
					= (TableColumn<Address, String> param) -> new EditingCellAddress();*/
    

	@FXML
	/**
	 * Exit Current Application
	 */
	public void exitApplication()
	{
		System.exit(0);
	}
	
	
	@FXML
	/**
	 * Add New Person and Contact Details in Table View 
	 */
	public void addPerson()
	{
		if(validateFields())
		{
			//Addition of Person
			Person tmpPerson = new Person(""
					 , firstName.getText(), middleInitial.getText(), lastName.getText(), 
					 birthDate.getValue().toString(), getComboString());
			
			new PersonUtility().addPerson(tmpPerson);
			personEntries.add(tmpPerson);
			
			
			//Addition of Address Details 
			Address tmpAddress = new Address("",
					tmpPerson.getId(), address1.getText(), address2.getText(), city.getText(),
					state.getText(), country.getText(), zipCode.getText(), "", phoneNumber.getText(),
					emailId.getText(), "", "");
			
			new AddressUtility().addAddress(tmpAddress);
			contactEntries.add(tmpAddress);
			
			clearAllFields();
		}
	}
	
	@FXML
	/**
	 * This method will modify Selected Row and 
	 * If Not Selected Alert User to Select for modification
	 * 
	 * 
	 */
	public void modifyPerson()
	{
		if(personTable.getSelectionModel().getSelectedItem()!=null)
		{
			if(validateFields())
			{
				Person tmpPerson = new Person(personTable.getSelectionModel().getSelectedItem().getId()
						 , firstName.getText(), middleInitial.getText(), lastName.getText(), 
						 birthDate.getValue().toString(), getComboString());
				
				if(addressTable.getSelectionModel().getSelectedItem()!=null)
				{
					//Update Person and Address Both 
					
					 new PersonUtility().updatePerson(tmpPerson);
					 personEntries.set(personTable.getSelectionModel().getSelectedIndex(), tmpPerson);
					 
					 
					Address tmpAddress = new Address(addressTable.getSelectionModel().getSelectedItem().getId(),
							tmpPerson.getId(), address1.getText(), address2.getText(), city.getText(),
							state.getText(), country.getText(), zipCode.getText(), "", phoneNumber.getText(),
							emailId.getText(), addressTable.getSelectionModel().getSelectedItem().getPhoneNumberId(),
							addressTable.getSelectionModel().getSelectedItem().getEmailAddressId());
					
					new AddressUtility().updateAddress(tmpAddress);
					contactEntries.set(addressTable.getSelectionModel().getSelectedIndex(), tmpAddress);
				}
				else
				{
					
					/* if(contactEntries.size()<1)
					 {
						 //Add Contact Details
						 Alert alert = new Alert(AlertType.CONFIRMATION);
						 alert.setTitle("Confirmation For Modification");
						 alert.setHeaderText("There is no current Entries in Contact Details. Please Select Following option");

						 ButtonType buttonTypeOne = new ButtonType("Add New Contact Entry For Person");
						 ButtonType buttonTypeThree = new ButtonType("Update Only Person Details");
						 ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

						 alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeThree, buttonTypeCancel);

						 Optional<ButtonType> result = alert.showAndWait();
						 if (result.get() == buttonTypeOne){

							 if(validateFields())
							 {
								 Address tmpAddress = new Address(addressTable.getSelectionModel().getSelectedItem().getId(),
											tmpPerson.getId(), address1.getText(), address2.getText(), city.getText(),
											state.getText(), country.getText(), zipCode.getText(), "", phoneNumber.getText(),
											emailId.getText(), addressTable.getSelectionModel().getSelectedItem().getPhoneNumberId(),
											addressTable.getSelectionModel().getSelectedItem().getEmailAddressId());
									
									new AddressUtility().updateAddress(tmpAddress);
									contactEntries.set(addressTable.getSelectionModel().getSelectedIndex(), tmpAddress);
							 }
						 } else if (result.get() == buttonTypeThree) {
						     // ... user chose "Three"
						 } else {
						     // ... user chose CANCEL or closed the dialog
						 }
					 }*/
					 new PersonUtility().updatePerson(tmpPerson);
					 personEntries.set(personTable.getSelectionModel().getSelectedIndex(), tmpPerson);
					 
				}
				 
			}
		}
		else
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Please Select Any Rows for Modify Operation");
			alert.setContentText("Select Any Row for modification of Row. "
					+ "\n To Update only Person Details Select Person Row \n To Update All Details select Person and Contact Row");

			alert.showAndWait();
		}
		
	}
	
	@FXML
	/**
	 * This method will delete details 
	 * It also Checks for Referential Integrity and allow to delete it 
	 * 
	 */
	public void deletePerson()
	{
		if(personTable.getSelectionModel().getSelectedItem()!=null)
		{
			if(contactEntries.size()<1)
			{
				new PersonUtility().deletePerson(personTable.getSelectionModel().getSelectedItem());
				personEntries.remove(personTable.getSelectionModel().getSelectedIndex());
			}
			else if(addressTable.getSelectionModel().getSelectedItem()!=null)
			{
				//Delete Child Table First And then Parent table
				new AddressUtility().deleteAddress(addressTable.getSelectionModel().getSelectedItem());
				new PersonUtility().deletePerson(personTable.getSelectionModel().getSelectedItem());
				
				
				contactEntries.remove(addressTable.getSelectionModel().getSelectedIndex());
				personEntries.remove(personTable.getSelectionModel().getSelectedIndex());
			}
			else
			{
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Information");
				alert.setHeaderText("Referential Integrity Violation");
				alert.setContentText("You are not allow to delete Parent Table Records. \nChild Table Records for Contact Details Exists");

				alert.showAndWait();
			}
		}
		else
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Please Select Any Rows for Delete Operation");
			alert.setContentText("Delete Operation will Delete with Consideration of Referential Integrity"
					+ "\n You are not allowed to delete parent record without deleting child record ");

			alert.showAndWait();
		}
	}
	

	
	@Override
	/**
	 * Initialize All JavaFX Components while class initializing
	 * 
	 */
	public void initialize(URL location, ResourceBundle resources) {
		
		personEntries = FXCollections.observableArrayList(new PersonUtility().getPersons());
		contactEntries = FXCollections.observableArrayList();
		
		//Set Table Properties
		personTable.setItems(personEntries);
		personTable.setEditable(true);
		
		addressTable.setItems(contactEntries);
		//addressTable.setEditable(true);
		
		sex.getItems().addAll("Male","Female");
		
		//Person Table onClick Event For Populating Fields
		personTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	//Fetching Row From All Tables
		    	contactEntries.clear();
		    	contactEntries.addAll(new AddressUtility().getAddress(Integer.valueOf(newSelection.getId())));
		    	
		    	clearAllFields();
		    	
		    	firstName.setText(newSelection.getFirstName());
		    	middleInitial.setText(newSelection.getMiddleInitial());
		    	lastName.setText(newSelection.getLastName());
		    	try
		    	{
		    		birthDate.setValue(LocalDate.parse(newSelection.getBirthDate(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")));
		    	}
		    	catch(Exception e)
		    	{
		    		birthDate.setValue(LocalDate.parse(newSelection.getBirthDate(),DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		    	}
		    	
		    	setComboString(newSelection.getSex());
		    }
		});
		
		//Address Table onClick Event For Populating Fields
		addressTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {

		    	Address tmpAddress = addressTable.getSelectionModel().getSelectedItem();
		    	
		    	address1.setText(tmpAddress.getAddress1());
		    	address2.setText(tmpAddress.getAddress2());
		    	city.setText(tmpAddress.getCity());
		    	state.setText(tmpAddress.getState());
		    	country.setText(tmpAddress.getCountry());
		    	zipCode.setText(tmpAddress.getZip());
		    	phoneNumber.setText(tmpAddress.getPhoneNumber());
		    	emailId.setText(tmpAddress.getEmailAddress());
		    }
		});
		
		
		/*
		 * 
		 *   Set All Columns Property in Person TableView - START 
		 *   
		 *   
		 *   
		 */
		
		colFirstName.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
		colFirstName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		colFirstName.setOnEditCommit(
	                (TableColumn.CellEditEvent<Person, String> t) -> {
	                    if(!"".equals(t.getNewValue()) && t.getNewValue().length()<200)
	            		{
	                    	((Person) t.getTableView().getItems()
	        	                    .get(t.getTablePosition().getRow()))
	        	                    .setFirstName(t.getNewValue());
	                    	 new PersonUtility().updatePerson((Person) t.getTableView().getItems()
	         	                    .get(t.getTablePosition().getRow()));
	            		}
	                    else
	                    {
	                    	((Person) t.getTableView().getItems()
	        	                    .get(t.getTablePosition().getRow()))
	        	                    .setFirstName(t.getOldValue());
	                    	t.getTableView().refresh();
	                    }
	                    
	                });
		

		colMiddleInitial.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
		colMiddleInitial.setCellValueFactory(cellData -> cellData.getValue().middleInitialProperty());
		colMiddleInitial.setOnEditCommit(
	                (TableColumn.CellEditEvent<Person, String> t) -> {
	                    	if(!"".equals(t.getNewValue()) && t.getNewValue().length()==1) 
	                		{
	                    		((Person) t.getTableView().getItems()
	            	                    .get(t.getTablePosition().getRow()))
	            	                    .setMiddleInitial(t.getNewValue());
	            	                    
	            	                    new PersonUtility().updatePerson((Person) t.getTableView().getItems()
	            	    	                    .get(t.getTablePosition().getRow()));
	                		}
	                    	else
	                    	{

	                    		((Person) t.getTableView().getItems()
	            	                    .get(t.getTablePosition().getRow()))
	            	                    .setMiddleInitial(t.getOldValue());
	                    		t.getTableView().refresh();
	                    	}
	                	

	                });
		
		
		

		colLastName.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
		colLastName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		colLastName.setOnEditCommit(
	                (TableColumn.CellEditEvent<Person, String> t) -> {
	                    
	                	
	                	if(!"".equals(t.getNewValue()) && t.getNewValue().length()<200)
	            		{
	                		((Person) t.getTableView().getItems()
	        	                    .get(t.getTablePosition().getRow()))
	        	                    .setLastName(t.getNewValue());
	        	                    
	        	                    new PersonUtility().updatePerson((Person) t.getTableView().getItems()
	        	    	                    .get(t.getTablePosition().getRow()));
	            		}
	                	else
	                	{
	                		((Person) t.getTableView().getItems()
	        	                    .get(t.getTablePosition().getRow()))
	        	                    .setLastName(t.getOldValue());
	                		t.getTableView().refresh();
	                	}

	                });
		
		
		colBirthDate.setEditable(false);
		colBirthDate.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
		colBirthDate.setCellValueFactory(cellData -> cellData.getValue().birthDateProperty());
		colBirthDate.setOnEditCommit(
	                (TableColumn.CellEditEvent<Person, String> t) -> {
	                    
	                	if(t.getNewValue()!=null && !"".equals(t.getNewValue()))
	            		{
	                		/*if(checkDateRange(t.getNewValue()))
	                		{*/
	                			((Person) t.getTableView().getItems()
		        	                    .get(t.getTablePosition().getRow()))
		        	                    .setBirthDate(t.getNewValue());

		        	                    
		        	                    new PersonUtility().updatePerson((Person) t.getTableView().getItems()
		        	    	                    .get(t.getTablePosition().getRow()));
	                		/*}*/
	                		
	            		}
	                	else
	                	{
	                		((Person) t.getTableView().getItems()
	        	                    .get(t.getTablePosition().getRow()))
	        	                    .setBirthDate(t.getNewValue());
	                		t.getTableView().refresh();
	                	}
	                	
	                    
	                    
	                });
		
		
		colSex.setEditable(false);
		colSex.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
		colSex.setCellValueFactory(cellData -> cellData.getValue().sexProperty());
		colSex.setOnEditCommit(
	                (TableColumn.CellEditEvent<Person, String> t) -> {
	                    
	                	
	                	if(t.getNewValue()!=null || 
	            				(t.getNewValue().equals("Male") 
	            						|| t.getNewValue().equals("Female")))
	            		{
	                		((Person) t.getTableView().getItems()
	        	                    .get(t.getTablePosition().getRow()))
	        	                    .setSex(t.getNewValue());

	        	                    
	        	                    new PersonUtility().updatePerson((Person) t.getTableView().getItems()
	        	    	                    .get(t.getTablePosition().getRow()));
	            		}
	                	else
	                	{
	                		((Person) t.getTableView().getItems()
	        	                    .get(t.getTablePosition().getRow()))
	        	                    .setSex(t.getOldValue());
	                		t.getTableView().refresh();
	                	}
	                	

	                });
		
		/*
		 * 
		 *   Set All Columns Property in Person TableView - END 
		 *   
		 *   
		 *   
		 */
		
		
		/*
		 * 	Set another Table For Details 1...N relationship
		 *  Set All Columns Property in Address TableView - START 
		 *   
		 *   
		 */
		colAddress1.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
		colAddress1.setCellValueFactory(cellData -> cellData.getValue().address1Property());
		colAddress1.setOnEditCommit(
	                (TableColumn.CellEditEvent<Address, String> t) -> {
	                    ((Address) t.getTableView().getItems()
	                    .get(t.getTablePosition().getRow()))
	                    .setAddress1(t.getNewValue());
	                    new AddressUtility().updateAddress((Address) t.getTableView().getItems()
	                    .get(t.getTablePosition().getRow()));
	                });
		
		colAddress2.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
		colAddress2.setCellValueFactory(cellData -> cellData.getValue().address2Property());
		colAddress2.setOnEditCommit(
	                (TableColumn.CellEditEvent<Address, String> t) -> {
	                    ((Address) t.getTableView().getItems()
	                    .get(t.getTablePosition().getRow()))
	                    .setAddress2(t.getNewValue());
	                    new AddressUtility().updateAddress((Address) t.getTableView().getItems()
	    	                    .get(t.getTablePosition().getRow()));
	                });
		
		colCity.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
		colCity.setCellValueFactory(cellData -> cellData.getValue().cityProperty());
		colCity.setOnEditCommit(
	                (TableColumn.CellEditEvent<Address, String> t) -> {
	                    ((Address) t.getTableView().getItems()
	                    .get(t.getTablePosition().getRow()))
	                    .setCity(t.getNewValue());
	                    new AddressUtility().updateAddress((Address) t.getTableView().getItems()
	    	                    .get(t.getTablePosition().getRow()));
	                });
		
		colState.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
		colState.setCellValueFactory(cellData -> cellData.getValue().stateProperty());
		colState.setOnEditCommit(
	                (TableColumn.CellEditEvent<Address, String> t) -> {
	                    ((Address) t.getTableView().getItems()
	                    .get(t.getTablePosition().getRow()))
	                    .setState(t.getNewValue());
	                    new AddressUtility().updateAddress((Address) t.getTableView().getItems()
	    	                    .get(t.getTablePosition().getRow()));
	                });
		
		colCountry.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
		colCountry.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
		colCountry.setOnEditCommit(
	                (TableColumn.CellEditEvent<Address, String> t) -> {
	                    ((Address) t.getTableView().getItems()
	                    .get(t.getTablePosition().getRow()))
	                    .setCountry(t.getNewValue());
	                    new AddressUtility().updateAddress((Address) t.getTableView().getItems()
	    	                    .get(t.getTablePosition().getRow()));
	                });
		
		colZip.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
		colZip.setCellValueFactory(cellData -> cellData.getValue().zipProperty());
		colZip.setOnEditCommit(
	                (TableColumn.CellEditEvent<Address, String> t) -> {
	                    ((Address) t.getTableView().getItems()
	                    .get(t.getTablePosition().getRow()))
	                    .setZip(t.getNewValue());
	                    new AddressUtility().updateAddress((Address) t.getTableView().getItems()
	    	                    .get(t.getTablePosition().getRow()));
	                });
		
		//Added two another Columns 
		colPhoneNum.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
		colPhoneNum.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
		colPhoneNum.setOnEditCommit(
	                (TableColumn.CellEditEvent<Address, String> t) -> {
	                    ((Address) t.getTableView().getItems()
	                    .get(t.getTablePosition().getRow()))
	                    .setPhoneNumber(t.getNewValue());
	                    new AddressUtility().updateAddress((Address) t.getTableView().getItems()
	    	                    .get(t.getTablePosition().getRow()));
	                });
		
		colEmailAdd.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
		colEmailAdd.setCellValueFactory(cellData -> cellData.getValue().emailAddressProperty());
		colEmailAdd.setOnEditCommit(
	                (TableColumn.CellEditEvent<Address, String> t) -> {
	                    ((Address) t.getTableView().getItems()
	                    .get(t.getTablePosition().getRow()))
	                    .setEmailAddress(t.getNewValue());
	                    new AddressUtility().updateAddress((Address) t.getTableView().getItems()
	    	                    .get(t.getTablePosition().getRow()));
	                });
		/*
		 * 	Set another Table For Details 1...N relationship
		 *  Set All Columns Property in Address TableView - END 
		 *   
		 *   
		 */
		
		
		//Restricting Date picker
		 final Callback<DatePicker, DateCell> dayCellFactory = 
		            new Callback<DatePicker, DateCell>() {
		                @Override
		                public DateCell call(final DatePicker datePicker) {
		                    return new DateCell() {
		                        @Override
		                        public void updateItem(LocalDate item, boolean empty) {
		                            super.updateItem(item, empty);
		                            if (item.isBefore(LocalDate.parse("1901-01-01",DateTimeFormatter.ofPattern("yyyy-MM-dd")))
		                            		|| item.isAfter(LocalDate.parse(DateUtility.getTodatDateString(),DateTimeFormatter.ofPattern("yyyy-MM-dd")))) 
		                            {
		                                    setDisable(true);
		                                    setStyle("-fx-background-color: #ffc0cb;");
		                            }   
		                    }
		                };
		            }
		        };
		        birthDate.setDayCellFactory(dayCellFactory);
		        birthDate.setEditable(false);
		
	}
	
	
	/**
	 * Setting and Retrieving Value of Combo Box
	 * 
	 * @param inputString
	 */
	private void setComboString(String inputString)
	{
		if("M".equals(inputString))
			{
				sex.getSelectionModel().select(0);
			}
		else if("F".equals(inputString))
			{
				sex.getSelectionModel().select(1);
			}
		else 
			{
				sex.getSelectionModel().clearSelection();
			}
	}
	
	/**
	 * Set Combobox String for Combo Component of JavaFX
	 * @return
	 */
	private String getComboString()
	{
		if(sex.getSelectionModel().getSelectedItem().equals("Male"))
		{
			return "M";
		}
		else
		{
			return "F";
		}


	}
	
	/**
	 * Validation Provider
	 * @return
	 */
	private boolean validateFields()
	{
		
		String msg = "";
		
		if("".equals(firstName.getText()))
		{
			msg+="Please Enter First Name as mandatory Field \n\n";
		}
		if(firstName.getText().length()>200)
		{
			msg+="First Name Should have 200 character length \n\n";
		}
		
		if("".equals(middleInitial.getText()) )
		{
			msg+="Please Enter Middle Initial as mandatory Field \n\n";
		}
		if(middleInitial.getText().length()>1)
		{
			msg+="Middle Initial Should have 1 character length \n\n";
		}


		if("".equals(lastName.getText()))
		{
			msg+="Please Enter Last Name as mandatory Field \n\n";
		}
		if(lastName.getText().length()>200)
		{
			msg+="Last Name Should have 200 character length \n\n";
		}
		
		if(birthDate.getValue()==null || "".equals(birthDate.getValue()))
		{
			msg+="Please Enter Birth Date as mandatory Field \n\n";
		}
		/*if(checkDateRange(birthDate.getValue().toString()))
		{
			msg+="Please Enter Birth Date in Proper Format. \n\n";
		}*/
		if(sex.getSelectionModel().getSelectedItem()==null || 
				(!sex.getSelectionModel().getSelectedItem().equals("Male") 
						&& !sex.getSelectionModel().getSelectedItem().equals("Female")))
		{
			msg+="Please Select Sex as mandatory Field \n\n";
		}
		
		
		if("".equals(address1.getText()))
		{
			msg+="Please Enter Address Line 1 as mandatory Field \n\n";
		}
		if(address1.getText().length()>100)
		{
			msg+="Address Line 1 Should have 100 character length \n\n";
		}
		

		if("".equals(address2.getText()))
		{
			msg+="Please Enter Address Line 2 as mandatory Field \n\n";
		}
		if(address2.getText().length()>100)
		{
			msg+="Address Line 2 Should have 100 character length \n\n";
		}
		

		if("".equals(city.getText()))
		{
			msg+="Please Enter City as mandatory Field \n\n";
		}
		if(address1.getText().length()>45)
		{
			msg+="City Should have 45 character length \n\n";
		}
		

		if("".equals(state.getText()))
		{
			msg+="Please Enter State as mandatory Field \n\n";
		}
		if(state.getText().length()>45)
		{
			msg+="State Should have 45 character length \n\n";
		}
		
		
		if("".equals(country.getText()))
		{
			msg+="Please Enter Country as mandatory Field \n\n";
		}
		if(country.getText().length()>200)
		{
			msg+="Country Should have 200 character length \n\n";
		}
		
		
		if("".equals(zipCode.getText()))
		{
			msg+="Please Enter Zip as mandatory Field \n\n";
		}
		if(zipCode.getText().length()>10)
		{
			msg+="Zip Code Should have 10 Digit length \n\n";
		}
		try
		{
			Integer.parseInt(zipCode.getText());
		}
		catch(Exception e)
		{
			msg+="Zip Should have Numeric Characters \n\n";
		}
		
		
		
		if("".equals(phoneNumber.getText()))
		{
			msg+="Please Enter Phone Number as mandatory Field \n\n";
		}
		if(phoneNumber.getText().length()>10)
		{
			msg+="Phone Number Should have 10 Digit length \n\n";
		}
		try
		{
			Integer.parseInt(phoneNumber.getText());
		}
		catch(Exception e)
		{
			msg+="Phone Number have Numeric Characters \n\n";
		}
		
		if("".equals(emailId.getText()))
		{
			msg+="Please Enter Email Address as mandatory Field \n\n";
		}
		if(emailId.getText().length()>100)
		{
			msg+=" Email Address Should have 100 Character length \n\n";
		}


		if(!"".equals(msg))
		{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Validation");
			alert.setHeaderText("Please Correct Following Validations For Proceed Further");
			alert.setContentText(msg);
			alert.showAndWait();
			return false;
		}
		
		return true;
	
	}
	
	@FXML
	/**
	 * Clearing All Filled Fields
	 * 
	 */
	public void clearAllFields()
	{
		firstName.setText("");
    	middleInitial.setText("");
    	lastName.setText("");
    	birthDate.setValue(null);
    	sex.getSelectionModel().clearSelection();
    	
    	
    	address1.setText("");
    	address2.setText("");
    	city.setText("");
    	state.setText("");
    	country.setText("");
    	zipCode.setText("");
    	phoneNumber.setText("");
    	emailId.setText("");
    	
    	
	}
	
	
	/*private boolean checkDateRange(String inputDate)
	{
		LocalDate dd = null;
		try
    	{
			dd = LocalDate.parse(inputDate,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
    	}
    	catch(Exception e)
    	{
    		dd = LocalDate.parse(inputDate,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    	}
		
		return !(dd.isAfter(LocalDate.parse(DateUtility.getTodatDateString(),DateTimeFormatter.ofPattern("yyyy-MM-dd"))) 
				&& dd.isBefore(LocalDate.parse("1901-01-01",DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
	}*/
	
}
