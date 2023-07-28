package com.actitime.testscript;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.actitime.generic.BaseClass;
import com.actitime.pom.HomePage;
import com.actitime.pom.TaskListPage;

@Listeners(com.actitime.generic.ListenerImplementation.class)
public class CustomerModule extends BaseClass{

@Test
public void testCreateCustomer() throws InterruptedException, EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./data/testscript.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
String expectedCustomer = wb.getSheet("createcustomer").getRow(1).getCell(2).getStringCellValue();
String customerDescription = wb.getSheet("createcustomer").getRow(1).getCell(3).getStringCellValue();
		HomePage h=new HomePage(driver);
		h.setTasksTab();
		TaskListPage t=new TaskListPage(driver);
		t.getAddNewBtn().click();
		t.getNewCustomerBtn().click();
		t.getCustomerNameTbx().sendKeys(expectedCustomer);
		t.getCustomerDescriptionTbx().sendKeys(customerDescription);
		t.getSelectCustomerDD().click();
		t.getBigBangCompany().click();
		t.getCreateCustomerBtn().click();
		Thread.sleep(5000);
		String actualCustomer = t.getActualCustomerCreated().getText();
		Assert.assertEquals(actualCustomer, expectedCustomer);
	}
	
}
