package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ToDoPage {

    public WebDriver driver;

    public ToDoPage(WebDriver d){
        driver = d;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@class='new-todo']")
    WebElement addToDo;

    public WebElement addToDo(){
        return addToDo;
    }

    @FindBy(xpath = "//label[@data-testid='todo-item-label']")
    List<WebElement> items;

    public List<WebElement> items(){
        return items;
    }

    @FindBy(xpath = "//span[@class='todo-count']")
    WebElement count;

    public WebElement count(){
        return count;
    }

    @FindBy(xpath = "//label[@data-testid='todo-item-label']/preceding-sibling::input")
    List<WebElement> checkbox;

    public List<WebElement> checkbox(){
        return checkbox;
    }

    @FindBy(xpath = "//label[@data-testid='todo-item-label']/../..")
    List<WebElement> completed;

    public List<WebElement> completed(){
        return completed;
    }

    @FindBy(xpath = "//label[text()='Clean the house']")
    WebElement deleteItem;

    public WebElement deleteItem(){
        return deleteItem;
    }

    @FindBy(xpath = "//label[text()='Clean the house']/following-sibling::button")
    WebElement delete;

    public WebElement delete(){
        return delete;
    }

    @FindBy(xpath = "//a[text()='Active']")
    WebElement active;

    public WebElement active(){
        return active;
    }

    @FindBy(xpath = "//a[text()='Completed']")
    WebElement completeditem;

    public WebElement completeditem(){
        return completeditem;
    }
}
