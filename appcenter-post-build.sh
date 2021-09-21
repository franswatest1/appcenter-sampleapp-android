echo "Post Build Script Started"

cat << EOF
Check if maven is installed...
check maven version
EOF

mvn --version
