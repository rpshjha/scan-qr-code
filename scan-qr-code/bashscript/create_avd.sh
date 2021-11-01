#!/usr/bin/expect -f
 
set timeout -1

pwd
cd $(dirname "$0")

spawn ./create_avd_command.sh
set timeout 2
expect {Do you wish to create a custom hardware profile? [no] }
send -- "no\r"
expect eof