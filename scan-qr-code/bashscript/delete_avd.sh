#!/bin/bash

user_home=$(bash -c "cd ~$(printf %q $USER) && pwd")
$user_home/Library/Android/sdk/tools/bin/avdmanager delete avd -n testAvd