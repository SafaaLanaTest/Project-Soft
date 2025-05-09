package com.example.sw_project.AcceptanceTest.ProductionCode;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "C:\\Users\\safaa\\IdeaProjects\\sw_project\\cases", // مسار ملفات .feature
        glue = "com.example.sw_project"     // الباكيج الذي يحتوي خطوات الاختبار

)
public class acceptanceTest {
}
