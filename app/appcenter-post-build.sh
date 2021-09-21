#!/usr/bin/env bash

echo "This is an App Center Post-Build script. For more information on how to use App Center build scripts vist: https://docs.microsoft.com/en-us/appcenter/build/custom/scripts"

echo "Post Build Script Started"

cat << EOF
Check if maven is installed...
check maven version
EOF

mvn --version
