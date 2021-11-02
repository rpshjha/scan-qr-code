#!/bin/bash
echo "installing android sdk in $WORKSPACE"

mkdir android
wget https://dl.google.com/android/repository/commandlinetools-mac-7583922_latest.zip
unzip -q commandlinetools-mac-7583922_latest.zip -d ./
mv cmdline-tools ./android/
cd android
mkdir platform-tools
mkdir platforms
cd cmdline-tools
mkdir tools
mv ./bin/ ./tools/
mv ./lib/ ./tools/
