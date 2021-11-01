#!/bin/bash

user_home=$(bash -c "cd ~$(printf %q $USER) && pwd")
$ANDROID_HOME/tools/bin/avdmanager create avd --force --name testAvd --package 'system-images;android-30;google_apis_playstore;x86'
set timeout 20
read $REPLY