#!/usr/bin/env bash

apt-get -y install git #TODO this shouldn't be needed, git should be installed in the packer build step. Fix this.

# Delete cloned repo if it exists
if [[ -d ${REPO_PATH} ]]; then
    echo "Updating repo as it already exists..."
	
	# Move to the REPO_PATH
	pushd ${REPO_PATH}
		
		# Clean working tree
		git reset --hard
		
		# Update
		git pull
	
	popd
	
else
	echo "Cloning repo as it does not exist..."
	# Clone team repo into REPO_PATH
	sudo git clone ${REPO_URL} ${REPO_PATH}
	
	if [[ ! -d ${REPO_PATH} ]]; then
		echo "For some reason, cloning the git repo has failed!"
		false
	fi

	# Give vagrant user ownership of git repo
	sudo chown vagrant:vagrant ${REPO_PATH}

	echo "Done cloning repo."
fi

