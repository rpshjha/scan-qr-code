#!/bin/bash

user_home=$(bash -c "cd ~$(printf %q $USER) && pwd")
$user_home/Library/Android/sdk/tools/bin/sdkmanager --list | grep 'system-images\|google_apis'