import hudson.model.*;
import hudson.util.*;
import hudson.scm.*;
import hudson.plugins.accurev.*

//def thr = Thread.currentThread();
//def build = thr?.executable;

//def changeSet = build.getChangeSet();

//def changes = changeSet.getItems();

pipeline {
    agent any

    stages {
        stage("Build") {
            steps {
                echo "Building.."

                
                // Allow ps1 files to be run ;)
                bat "powershell Set-ExecutionPolicy unrestricted -Force
                
                // Debug print for execution policy.
                bat "powershell Get-ExecutionPolicy"
                
                dir("${WORKSPACE}/ci-scripts/windows/") {
                    // Install dependencies.
                    bat "powershell -file install-deps.ps1"
                    bat "powershell -file try-install-vagrant.ps1"
                }

                dir("${WORKSPACE}/") {
                    // Remove box files if they were changed in the most recent commit.
                    bat "ruby ci-scripts/ruby/remove-boxes-if-changed-in-most-recent-commit.rb"
                }

                dir("${WORKSPACE}/packer") {
                    // Create all missing packer box files.
                    bat "ruby build-missing.rb"
                }

            }
        }
        stage("Deploy") {
            steps {
                echo "Deploying...."
                dir("${WORKSPACE}/") {
                    bat "vagrant up"
                }
            }
        }
        stage("Test") {
            steps {
                echo "Testing.."

                dir("${WORKSPACE}/ci-scripts/ruby") {
                    bat "ruby test-webserver.rb"
                }
            }
        }
    }
    post {
        always {
            echo "Cleaning..."
            
            dir("${WORKSPACE}/") {
                bat "vagrant halt -f"
            }
            
            dir("${WORKSPACE}/") {
                bat "vagrant destroy -f"
            }
        }
    }
}
