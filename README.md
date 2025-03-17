Task for using WebDriver API with any mail service, with few simple scenarios


**Task description:** 
![img.png](readmeContent/img.png)

For running this framework using IDE, use:
![img_1.png](readmeContent/img_1.png)
Paste values of the environment variables
`AT_GOOGLE_MAIL=mail`
`AT_GOOGLE_PASSWORD=password`
![img_2.png](readmeContent/img_2.png)


For running this framework using CLI, use these commands export or set (if you're on Windows):
`export AT_GOOGLE_MAIL=mail`
`export AT_GOOGLE_PASSWORD=password`
`./gradlew test`


For generating the Allure Report use:
`allure generate`
`allure serve`