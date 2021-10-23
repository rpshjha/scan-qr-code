#!/bin/bash

user_home=$(bash -c "cd ~$(printf %q $USER) && pwd")
$user_home/Library/Android/sdk/tools/bin/avdmanager create avd --force --name testAvd --package 'system-images;android-30;google_apis_playstore;x86'
set timeout 20
read $REPLY