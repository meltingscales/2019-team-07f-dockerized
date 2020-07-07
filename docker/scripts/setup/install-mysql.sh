#!/usr/bin/env bash

echo "Installing MySQL."

if [[ -z $MYSQL_PASSWORD ]]; then
  echo "MYSQL_PASSWORD must not be empty. Did you forget to pass arguments/environment variables?"
  exit 1
fi

# This prevents any prompts from coming up.
echo "mysql-server-5.7 mysql-server/root_password $MYSQL_PASSWORD $MYSQL_PASSWORD"        | debconf-set-selections
echo "mysql-server-5.7 mysql-server/root_password_again $MYSQL_PASSWORD $MYSQL_PASSWORD"  | debconf-set-selections

apt-get install -y mysql-server-5.7 mysql-client