package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import utilities.ConfigReader;

import java.sql.*;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class LogisticAPISteps {
    Response response;
    String companyId;
    ResultSet resultSet;

    @When("user sends get company api call")
    public void user_sends_get_company_api_call() {
        // http://3.137.169.132/en-us/api/v2/companies/153/
        response = given().baseUri(ConfigReader.getProperty("LogisticBaseURI"))
                .and().header("Authorization", "Token 9d3994dd2afd7d1d8ae9ecf4d77e45932bb210d6")
                .when().get("/companies/"+companyId+"/");
        System.out.println(response.statusCode());
        //System.out.println(response.body().asString());
        response.then().log().all();
    }


    @Then("user validates {int} status code")
    public void user_validates_status_code(int expectedStatusCode) {
        Assert.assertEquals(expectedStatusCode, response.statusCode());

    }

    @Given("user creates company with post call")
    public void userCreatesCompanyWithPostCall() {
        Random random = new Random();
        long mc_number=999999999+random.nextInt(10000000);
        long dot_number=999999999+random.nextInt(10000000);
        response = given().baseUri(ConfigReader.getProperty("LogisticBaseURI"))
                .and().header("Authorization","Token 9d3994dd2afd7d1d8ae9ecf4d77e45932bb210d6")
                .and().header("Content-Type","application/json")
                .and().body("{\n" +
                        "    \"company_name\": \"GreenWood\",\n" +
                        "    \"company_type\": \"broker company\",\n" +
                        "    \"status\": \"active\",\n" +
                        "    \"mc_number\": \""+mc_number+"\",\n" +
                        "    \"dot_number\": \""+dot_number+"\",\n" +
                        "    \"ifta\": false,\n" +
                        "    \"address\": \"111\",\n" +
                        "    \"apt_suite_company_co\": null,\n" +
                        "    \"city\": \"Raleigh\",\n" +
                        "    \"state\": \"NC\",\n" +
                        "    \"zip_code\": \"27511\",\n" +
                        "    \"insurance\": \"MetLife\",\n" +
                        "    \"producer_address\": \"123 Washington ave.\",\n" +
                        "    \"producer_apt_suite_company_co\": null,\n" +
                        "    \"producer_city\": \"Austin\",\n" +
                        "    \"producer_state\": \"IN\",\n" +
                        "    \"producer_zip_code\": \"85001\",\n" +
                        "    \"policy_effective_day\": null,\n" +
                        "    \"policy_expiration\": null,\n" +
                        "    \"type_of_insurance\": null,\n" +
                        "    \"automobile_liability\": \"\",\n" +
                        "    \"num_of_truck_insured\": null,\n" +
                        "    \"policy_number\": null,\n" +
                        "    \"employer_id_num\": \"22-2228998\",\n" +
                        "    \"billing_address\": null,\n" +
                        "    \"bank_name\": null,\n" +
                        "    \"routing_number\": null,\n" +
                        "    \"account_number\": null,\n" +
                        "    \"president_full_name\": null,\n" +
                        "    \"trucks_in_fleet\": null,\n" +
                        "    \"scac_code\": \"\",\n" +
                        "    \"other_licenses\": false,\n" +
                        "    \"license_name\": null,\n" +
                        "    \"incorporated_in\": null,\n" +
                        "    \"warning\": \"\",\n" +
                        "    \"notes\": null,\n" +
                        "    \"company_picture\": [],\n" +
                        "    \"company_documents\": [],\n" +
                        "    \"contacts\": [\n" +
                        "        {\n" +
                        "            \"phone\": \"111-111-1111\",\n" +
                        "            \"ext\": \"\",\n" +
                        "            \"contact_name\": \"\",\n" +
                        "            \"email\": \"raleigh@gmail.com\",\n" +
                        "            \"fax\": \"\",\n" +
                        "            \"producer_phone\": \"111-111-1111\",\n" +
                        "            \"producer_phone_ext\": \"\",\n" +
                        "            \"producer_contact_name\": \"\",\n" +
                        "            \"producer_email\": \"22@yahoo.com\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"phone_number\": [\n" +
                        "        {\n" +
                        "            \"phone\": \"111-111-1111\",\n" +
                        "            \"ext\": \"\",\n" +
                        "            \"contact_name\": \"\",\n" +
                        "            \"email\": \"raleigh@gmail.com\",\n" +
                        "            \"fax\": \"\",\n" +
                        "            \"producer_phone\": \"111-111-1111\",\n" +
                        "            \"producer_phone_ext\": \"\",\n" +
                        "            \"producer_contact_name\": \"\",\n" +
                        "            \"producer_email\": \"22@yahoo.com\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"fax_number\": [\n" +
                        "        {\n" +
                        "            \"fax\": \"\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"email_number\": [\n" +
                        "        {\n" +
                        "            \"email\": \"raleigh@gmail.com\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"producer_email_number\": [\n" +
                        "        {\n" +
                        "            \"producer_email\": \"22@yahoo.com\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"producer_phone_number\": [\n" +
                        "        {\n" +
                        "            \"producer_phone\": \"111-111-1111\",\n" +
                        "            \"producer_phone_ext\": \"\",\n" +
                        "            \"producer_contact_name\": \"\"\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}")
                .when().post("/companies/");
        response.then().log().all();
        companyId = response.body().jsonPath().getString("id");
        System.out.println("New Company ID "+ companyId);
    }

    @When("user connects to database")
    public void userConnectsToDatabase() throws SQLException {
        Connection connection = DriverManager.getConnection(
             "jdbc:postgressgl://3.137.169.132:5432/elardb",
                "impleyer",
                "ciforest");
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        resultSet = statement.executeQuery("select*from core_company where id = "+ companyId);
        resultSet.next();
    }

    @Then("user validates created company is persisted in DataBase")
    public void userValidatesCreatedCompanyIsPersistedInDataBase() throws SQLException {
        Assert.assertEquals(companyId,resultSet.getString("id"));
    }
}
