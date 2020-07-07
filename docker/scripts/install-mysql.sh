#!/usr/bin/env bash

echo "Installing MySQL."

# Password is 'password'.
# This also prevents any prompts from coming up.
echo "mysql-server-5.7 mysql-server/root_password password password"        | sudo debconf-set-selections
echo "mysql-server-5.7 mysql-server/root_password_again password password"  | sudo debconf-set-selections

echo "Hey Henry -- You must secure this before you use it. 'password' sucks as a password."
echo "Security by default, right? ;)"
exit 1

apt-get install -y mysql-server-5.7 mysql-client