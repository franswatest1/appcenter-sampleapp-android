#!/usr/bin/env bash

echo "This is an App Center Post-Build script. For more information on how to use App Center build scripts vist: https://docs.microsoft.com/en-us/appcenter/build/custom/scripts"

AppiumFolder=`find "$APPCENTER_SOURCE_DIRECTORY" -name "appium-ui-test" | head -1`
echo "Appium folder: $AppiumFolder"

echo "Pack Appium test classes and all dependencies..."
mvn -f "$AppiumFolder/pom.xml" -DskipTests -P prepare-for-upload package

AppiumUploadFolder="$AppiumFolder/target/upload"

APKFile=`find "$APPCENTER_SOURCE_DIRECTORY" -name *.apk | head -1`

#npm install -g appcenter-cli

appcenter login --token $ENVIRONMENT_BUILDTOKEN

appcenter test run appium --app $ENVIRONMENT_APPCENTER_APP_NAME --devices $ENVIRONMENT_APPCENTER_DEVICE --app-path $APKFile --test-series "master" --locale "en_US" --build-dir $AppiumUploadFolder
