#!/usr/bin/env bash

echo "This is an App Center Post-Build script. For more information on how to use App Center build scripts vist: https://docs.microsoft.com/en-us/appcenter/build/custom/scripts"

AppiumFolder=`find "$APPCENTER_SOURCE_DIRECTORY" -name "appiumuitest" | head -1`
echo "Appium folder: $AppiumFolder"

echo "Pack Appium test classes and all dependencies..."
mvn -f "$AppiumFolder/pom.xml" -DskipTests -P prepare-for-upload package

AppiumUploadFolder="$AppiumFolder/target/upload"
ls $AppiumUploadFolder
