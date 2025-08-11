@echo off
echo Creating Gradle Wrapper...

if not exist gradle mkdir gradle
if not exist gradle\wrapper mkdir gradle\wrapper

echo distributionBase=GRADLE_USER_HOME > gradle\wrapper\gradle-wrapper.properties
echo distributionPath=wrapper/dists >> gradle\wrapper\gradle-wrapper.properties
echo distributionUrl=https\://services.gradle.org/distributions/gradle-8.5-bin.zip >> gradle\wrapper\gradle-wrapper.properties
echo zipStoreBase=GRADLE_USER_HOME >> gradle\wrapper\gradle-wrapper.properties
echo zipStorePath=wrapper/dists >> gradle\wrapper\gradle-wrapper.properties

echo.
echo Gradle Wrapper properties created successfully.
echo Please download gradle-wrapper.jar manually from:
echo https://repo1.maven.org/maven2/org/gradle/gradle-wrapper/8.5/gradle-wrapper-8.5.jar
echo and place it in gradle/wrapper/ directory.