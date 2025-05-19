package com.example.sw_project.AcceptanceTest.ProductionCode;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/cases",        // استخدم مسار نسبي فقط
        glue = "com.example.sw_project.AcceptanceTest.ProductionCode" // ضع الباكيج الصحيح لخطوات Given/When/Then
)
public class acceptanceTest {
}