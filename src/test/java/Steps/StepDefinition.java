package Steps;

import PageObject.ToDoPage;
import Utilities.ExcelReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.swing.*;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class StepDefinition extends BaseClass{

    public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    ToDoPage page;

    int itemCount=0;
    int complete=0;

    public StepDefinition() {
        page = new ToDoPage(driver);
    }

    @When("I add all four items to the todo list")
    public void i_add_all_four_items_to_the_todo_list() throws IOException {
       try{

           ExcelReader.excelData("ToDo", "Sheet1");
           int size = ExcelReader.data.size();
           for (int i=0; i<size; i++){
               wait.until(ExpectedConditions.visibilityOf(page.addToDo())).sendKeys(ExcelReader.data.get(i), Keys.ENTER);
               itemCount = itemCount+1;
           }
           Hooks.log("Items added successfully");
           System.out.println("Items added successfully");
       }
       catch (Throwable e) {
           e.printStackTrace();
           Hooks.log("Adding items failed" + e.getMessage());
           System.out.println("Adding items failed");
           Assert.fail("Adding items failed");
       }
    }

    @Then("I should see all four items in the list")
    public void i_should_see_all_four_items_in_the_list() {
        try {
            for(WebElement item: page.items()){
                String text = wait.until(ExpectedConditions.visibilityOf(item)).getText();
                if (ExcelReader.data.contains(text)){
                    Hooks.log("ToDo: " + text + " is present");
                }
                else{
                    Hooks.log("ToDo item is missing");
                    Assert.fail();
                }
            }
            System.out.println("All todo items are present");
        }
        catch (Throwable e) {
            e.printStackTrace();
            Hooks.log("Todo item is missing");
            System.out.println("Todo item is missing");
            Assert.fail();
        }
    }

    @And("I should see the number of items in the left bottom counter")
    public void i_should_see_the_number_of_items_in_the_left_bottom_counter() {
        try{
            String text = wait.until(ExpectedConditions.visibilityOf(page.count())).getText();
            String count = text.replaceAll("\\D+", "");
            Assert.assertEquals(Integer.parseInt(count), itemCount, "Item count mismatched");
            Hooks.log("Items counts matched: " + count);
            System.out.println("Items counts matched: " + count);
        } catch (Throwable e) {
            e.printStackTrace();
            Hooks.log("Item count mismatched");
            System.out.println("Item count mismatched");
            Assert.fail();
        }

    }

    @When("I complete the todos")
    public void i_complete_todos() {
        try{
            ExcelReader.excelData("ToDo", "Complete");
            int size = ExcelReader.data.size();
            List<WebElement> check = page.checkbox();
//            List<WebElement> items = page.items();
//            for (WebElement item: items){
//                String text = item.getText();
//                if(ExcelReader.data.contains(text)){
//                    wait.until(ExpectedConditions.elementToBeClickable()).click();
//                }
//            }
            for(int i=0; i<size; i++){
                check.get(i).click();
//                wait.until(ExpectedConditions.elementToBeClickable(check.get(i))).click();
                complete = complete+1;
            }
            Hooks.log("Marked as completed");
            System.out.println("Marked as completed");
        }
        catch (Throwable e){
            e.printStackTrace();
            Hooks.log("Not completed");
            System.out.println("Not completed");
            Assert.fail();
        }
    }
    @Then("those todos should be striked out")
    public void those_todos_should_be_striked_out() {
        try {
            ExcelReader.excelData("ToDo", "Complete");
            int size = ExcelReader.data.size();
            List<WebElement> completes = page.completed();
            for (int i=0; i<completes.size(); i++){
                String aClass = completes.get(i).getAttribute("class");
                if(aClass.equals("completed")){
                    Hooks.log(ExcelReader.data.get(i)+" is completed");
                }
            }

        } catch (Throwable e) {
            e.printStackTrace();
            Hooks.log("Not completed");
            System.out.println("Not completed");
            Assert.fail();
        }
    }

    @Then("I should see the remaining number of items in the left bottom counter")
    public void i_should_see_the_remaining_number_of_items_in_the_left_bottom_counter() {
        try{
            String text = wait.until(ExpectedConditions.visibilityOf(page.count())).getText();
            String count = text.replaceAll("\\D+", "");
            Assert.assertEquals(Integer.parseInt(count), complete, "Item count mismatched");
            Hooks.log("Remaining items: " + count);
            System.out.println("Remaining items: " + count);
        } catch (Throwable e) {
            e.printStackTrace();
            Hooks.log("Item count mismatched");
            System.out.println("Item count mismatched");
            Assert.fail();
        }
    }

    @When("I delete Clean the house")
    public void i_delete_clean_the_house() {
        try{
            Actions action = new Actions(driver);
            action.moveToElement(page.deleteItem()).perform();
            wait.until(ExpectedConditions.elementToBeClickable(page.delete())).click();
            Hooks.log("Delete button is clicked");
        } catch (Throwable e) {
            e.printStackTrace();
            Hooks.log("Delete button is not clicked");
            System.out.println("Delete button is not clicked");
            Assert.fail();
        }
    }

    @Then("the item should be removed")
    public void the_item_should_be_removed() {
        try{
            List<WebElement> items = page.items();
            int size = items.size();
            Assert.assertEquals(size, 3, "Item not deleted");
        } catch (Throwable e) {
            e.printStackTrace();
            Hooks.log("Item not deleted");
            System.out.println("Item not deleted");
            Assert.fail();
        }
    }

    @When("I click Active button, only active items should displayed")
    public void iClickActiveButtonOnlyActiveItemsShouldDisplayed() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(page.active())).click();
            List<WebElement> items = page.items();
            Assert.assertEquals(items.size(), 1, "Active items not shown");
            Hooks.log("Active item is: "+ items.getFirst());
        }
        catch (Throwable e){
            e.printStackTrace();
            Hooks.log("Active items not shown");
            System.out.println("Active items not shown");
            Assert.fail();
        }
    }
    @When("I click Completed button, only completed items should displayed")
    public void iClickCompletedButtonOnlyCompletedItemsShouldDisplayed() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(page.completeditem())).click();
            List<WebElement> items = page.items();
            Assert.assertEquals(items.size(), 2, "Completed items not shown");
            for (WebElement item: items){
                Hooks.log("Completed item is: "+ item.getText());
            }
        }
        catch (Throwable e){
            e.printStackTrace();
            Hooks.log("Completed items not shown");
            System.out.println("Completed items not shown");
            Assert.fail();
        }
    }

    }
