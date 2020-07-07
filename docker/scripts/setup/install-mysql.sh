#!/usr/bin/env bash

echo "Installing MySQL."

# Password is 'password'.
# This also prevents any prompts from coming up.
echo "mysql-server-5.7 mysql-server/root_password password password"        | debconf-set-selections
echo "mysql-server-5.7 mysql-server/root_password_again password password"  | debconf-set-selections

apt-get install -y mysql-server-5.7 mysql-client

echo "Hey Henry -- You must secure this before you use it. 'password' sucks as a password."
echo "Security by default, right? ;)"
echo "IDK, past self... bruh... Use uh...Vault? K8s? Not sure. Google!!!"
exit 1