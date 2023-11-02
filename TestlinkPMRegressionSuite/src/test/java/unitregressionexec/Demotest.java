package unitregressionexec;

import com.epam.reportportal.example.cucumber6.attributes.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import testBase.TestBase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import static testBase.DriverFactory.driver;

public class Demotest extends TestBase {

        String jsonArray = "[{\"name\" : \"Bob\", \"age\" : 20, \"gender\": \"male\"}, {\"name\": \"George\", \"age\" : 42, \"gender\": \"male\"}, {\"name\":\n" +
            "\"Sara\", \"age\" : 42, \"gender\": \"female\"}, {\"name\": \"Conor\", \"age\" : 40, \"gender\": \"male\"}, {\"name\":\n" +
            "\"Jennifer\", \"age\" : 42, \"gender\": \"female\"}]";
    ObjectMapper objectMapper = new ObjectMapper();
    // Deserialize the JSON array to an ArrayList
    ArrayList<HashMap<String, Object>> arrayList = objectMapper.readValue(jsonArray, new TypeReference<ArrayList<HashMap<String, Object>>>() {});
    public static TreeMap<String, String> getASNInfo1 = new TreeMap<String, String>();

    // Access and print the elements in the ArrayList


    public Demotest() throws JsonProcessingException {
    }

    @Test
   public void CawStudio() throws IOException, InterruptedException {
        //from there we launch application
       launchApplication(Constants.createASNLocator);
       //its wait and click on Tabledata
       fluentWait("Tabledata");
       clickLinkOrButton("Tabledata");
       //its wait till element intactable and enter jsonArray
       waitForElementInteractability("Jsondatatextfield");
       enterText("Jsondatatextfield","[{\"name\" : \"Bob\", \"age\" : 20, \"gender\": \"male\"}, {\"name\": \"George\", \"age\" : 42, \"gender\": \"male\"}, {\"name\":\n" +
               "\"Sara\", \"age\" : 42, \"gender\": \"female\"}, {\"name\": \"Conor\", \"age\" : 40, \"gender\": \"male\"}, {\"name\":\n" +
               "\"Jennifer\", \"age\" : 42, \"gender\": \"female\"}]");
       //its wait and click on Refresh button
       fluentWait("Refreshbutton");
       clickLinkOrButton("Refreshbutton");
       //from there verifytextkeyword verify and Assert Text with the arraylist map
        Assert.assertTrue(verifyText("1stcoloumnvalue1", String.valueOf(arrayList.get(0).get("name"))));
        Assert.assertTrue(verifyText("1stcoloumnvalue2",String.valueOf(arrayList.get(0).get("age"))));
        Assert.assertTrue(verifyText("1stcoloumnvalue3",String.valueOf(arrayList.get(0).get("gender"))));
        Assert.assertTrue(verifyText("2ndcoloumnvalue1", String.valueOf(arrayList.get(1).get("name"))));
        Assert.assertTrue(verifyText("2ndcoloumnvalue2",String.valueOf(arrayList.get(1).get("age"))));
        Assert.assertTrue(verifyText("2ndcoloumnvalue3",String.valueOf(arrayList.get(1).get("gender"))));
        Assert.assertTrue(verifyText("3rdcoloumnvalue1",String.valueOf(arrayList.get(2).get("name"))));
        Assert.assertTrue(verifyText("3rdcoloumnvalue2",String.valueOf(arrayList.get(2).get("age"))));
        Assert.assertTrue(verifyText("3rdcoloumnvalue3",String.valueOf(arrayList.get(2).get("gender"))));
        Assert.assertTrue(verifyText("4thcoloumnvalue1",String.valueOf(arrayList.get(3).get("name"))));
        Assert.assertTrue(verifyText("4thtcoloumnvalue2", String.valueOf(arrayList.get(3).get("age"))));
        Assert.assertTrue(verifyText("4thcoloumnvalue3",String.valueOf(arrayList.get(3).get("gender"))));
        Assert.assertTrue(verifyText("5thcoloumnvalue1",String.valueOf(arrayList.get(4).get("name"))));
        Assert.assertTrue(verifyText("5thcoloumnvalue2",String.valueOf(arrayList.get(4).get("age"))));
        Assert.assertTrue(verifyText("5thcoloumnvalue3",String.valueOf(arrayList.get(4).get("gender"))));



       }





 }








