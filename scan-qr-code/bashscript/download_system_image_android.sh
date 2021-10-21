#!/bin/bash

PACKAGE_NAME=system-images;android-29;default;x86
user_home=$(bash -c "cd ~$(printf %q $USER) && pwd")
$user_home/Library/Android/sdk/tools/bin/sdkmanager --install $PACKAGE_NAME