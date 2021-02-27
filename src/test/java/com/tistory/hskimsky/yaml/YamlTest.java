package com.tistory.hskimsky.yaml;

import com.tistory.hskimsky.yaml.model.Contact;
import com.tistory.hskimsky.yaml.model.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * https://www.baeldung.com/java-snake-yaml
 *
 * @author hskimsky
 * @version 1.0
 * @since 2021-02-27
 */
public class YamlTest {

  private static final Logger logger = LoggerFactory.getLogger(YamlTest.class);

  @Test
  public void loadingBasicUsage() {
    Yaml yaml = new Yaml();
    InputStream inputStream = this.getClass()
      .getClassLoader()
      .getResourceAsStream("customer.yaml");
    Map<String, Object> obj = yaml.load(inputStream);
    logger.info("class = {}, obj = {}", obj.getClass(), obj);
  }

  @Test
  public void customType() {
    // Yaml yaml = new Yaml();
    Yaml yaml = new Yaml(new Constructor(Customer.class));
    InputStream inputStream = this.getClass()
      .getClassLoader()
      .getResourceAsStream("customer_with_type.yaml");
    Customer customer = yaml.load(inputStream);
    logger.info("customer = {}", customer);
  }

  @Test
  public void whenLoadYAML_thenLoadCorrectImplicitTypes() {
    Yaml yaml = new Yaml();
    Map<Object, Object> document = yaml.load("3.0: 2018-07-22");

    Assert.assertNotNull(document);
    Assert.assertEquals(1, document.size());
    Assert.assertTrue(document.containsKey(3.0d));
  }

  @Test
  public void whenLoadYAMLDocumentWithTopLevelClass_thenLoadCorrectJavaObjectWithNestedObjects() {
    Yaml yaml = new Yaml(new Constructor(Customer.class));
    InputStream inputStream = this.getClass().getClassLoader()
      .getResourceAsStream("customer_with_contact_details_and_address.yaml");
    Customer customer = yaml.load(inputStream);

    Assert.assertNotNull(customer);
    Assert.assertEquals("John", customer.getFirstName());
    Assert.assertEquals("Doe", customer.getLastName());
    Assert.assertEquals(31, customer.getAge());
    Assert.assertNotNull(customer.getContactDetails());
    Assert.assertEquals(2, customer.getContactDetails().size());

    Assert.assertEquals("mobile", customer.getContactDetails().get(0).getType());
    Assert.assertEquals(123456789, customer.getContactDetails().get(0).getNumber());
    Assert.assertEquals("landline", customer.getContactDetails().get(1).getType());
    Assert.assertEquals(456786868, customer.getContactDetails().get(1).getNumber());
    Assert.assertNotNull(customer.getHomeAddress());
    Assert.assertEquals("Xyz, DEF Street", customer.getHomeAddress().getLine());
  }

  @Test
  public void typeSafeCollections() {
    Constructor constructor = new Constructor(Customer.class);
    TypeDescription customTypeDescription = new TypeDescription(Customer.class);
    customTypeDescription.addPropertyParameters("contactDetails", Contact.class);
    constructor.addTypeDescription(customTypeDescription);
    Yaml yaml = new Yaml(constructor);
  }

  @Test
  public void whenLoadMultipleYAMLDocuments_thenLoadCorrectJavaObjects() {
    Yaml yaml = new Yaml(new Constructor(Customer.class));
    InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("customers.yaml");

    int count = 0;
    for (Object object : yaml.loadAll(inputStream)) {
      count++;
      Assert.assertTrue(object instanceof Customer);
    }
    Assert.assertEquals(2, count);
  }

  @Test
  public void whenDumpMap_thenGenerateCorrectYAML() {
    Map<String, Object> data = new LinkedHashMap<>();
    data.put("name", "Silenthand Olleander");
    data.put("race", "Human");
    data.put("traits", new String[]{"ONE_HAND", "ONE_EYE"});
    Yaml yaml = new Yaml();
    StringWriter writer = new StringWriter();
    yaml.dump(data, writer);
    String expectedYaml = "name: Silenthand Olleander\nrace: Human\ntraits: [ONE_HAND, ONE_EYE]\n";

    Assert.assertEquals(expectedYaml, writer.toString());
  }

  @Test
  public void whenDumpACustomType_thenGenerateCorrectYAML() {
    Customer customer = new Customer();
    customer.setAge(45);
    customer.setFirstName("Greg");
    customer.setLastName("McDowell");
    Yaml yaml = new Yaml();
    StringWriter writer = new StringWriter();
    yaml.dump(customer, writer);
    String expectedYaml = "!!com.tistory.hskimsky.yaml.model.Customer {age: 45, contactDetails: null, firstName: Greg,\n  homeAddress: null, lastName: McDowell}\n";
    Assert.assertEquals(expectedYaml, writer.toString());

    String toString = yaml.dumpAs(customer, Tag.MAP, null);
    logger.info("toString = {}", toString);
  }
}
