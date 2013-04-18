spring-javaconfig-demos
=======================

Spring JavaConfig for Annotation-based Spring Configuration


General Features
----------------
* Demonstration of JavaConfig Annotation based Spring Configuration
* Beans created in _src/main/java/com/gordondickens/javaconfig/AppContext.java_
* Tests are in _src/test/java/com/gordondickens/javaconfig/AppContextText.java_
* Logback with SLF4J for logging, see _src/test/resources/logback-test.xml_ for logging configuration

Configuration Notes
-------------------
* @Configuration classes must be non-final
* @Configuration classes must be non-local (may not be declared within a method)
* @Configuration classes must have a default/no-arg constructor
* Cannot use @Autowired constructor parameters
* Nested configuration classes must be static


01-Component-Scan
-----------------
* Demonstrates using _@ComponentScan_ in place of XML based _&lt;context:component-scan/&gt;_


02-Enable-TX
------------
* Demonstrates using _@EnableTransactionManagement in place of XML based _&lt;tx:annotation-config/&gt;_


03-Import-Resource
------------------
* Demonstrates using _@ImportResource_ to import XML configuration similar to XML based _&lt;import/&gt;_


04-Import
---------
* Demonstrates using _@Import_ in place to import another JavaConfig class similar to XML based _&lt;import/&gt;_


05-Property-Source
------------------
* Demonstrates using _@PropertySource_ to import properties similar to XML based _&lt;context:property-placeholder/&gt;_
* Demonstrates using SpEL (Spring Expression Language) and _@Value_ to inject a dependency into an _@Bean_ via an argument


06-Nested-Configuration
-----------------------
* Demonstrates Nested _@Configuration_ within an existing _@Configuration_ class
* NOTE: Nested _@Configuration_ classes MUST be _static_


07-Application-Initializer
--------------------------
* TODO - To be implemented


Lifecycle
---------
* TODO - To be implemented




