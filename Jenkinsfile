import hudson.model.*;
import hudson.util.*;
import hudson.scm.*;
import hudson.plugins.accurev.*

//def thr = Thread.currentThread();
//def build = thr?.executable;

//def changeSet = build.getChangeSet();

//def changes = changeSet.getItems();

// From https://gist.github.com/ftclausen/8c46195ee56e48e4d01cbfab19c41fc0
/**
 * Gets the commit hash from a Jenkins build object, if any
 */
@NonCPS
def commitHashForBuild( build ) {
  def scmAction = build?.actions.find { action -> action instanceof jenkins.scm.api.SCMRevisionAction }
  return scmAction?.revision?.hash
}

// From https://gist.github.com/ftclausen/8c46195ee56e48e4d01cbfab19c41fc0
def getLastSuccessfulCommit() {
  def lastSuccessfulHash = null
  def lastSuccessfulBuild = currentBuild.rawBuild.getPreviousSuccessfulBuild()
  if ( lastSuccessfulBuild ) {
    lastSuccessfulHash = commitHashForBuild( lastSuccessfulBuild )
  }
  return lastSuccessfulHash
}


pipeline {
    agent any

    stages {
        stage("Build") {
            steps {
                echo "Building.."
				
				node("Slack Notification") {
					slackSend channel: 'ci', message: "Building commit ${commitHashForBuild(currentBuild.rawBuild)}", tokenCredentialId: 'slack-integration-token'
				}
                
				// Allow ps1 files to be run ;)
                bat "powershell Set-ExecutionPolicy unrestricted -Force"
                
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

                dir("${WORKSPACE}/") {
                    // Validate Vagrantfile.
                    bat "vagrant validate"
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
                    bat "vagrant up --provision"
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
            echo "Shutting down VMs..."
            
            dir("${WORKSPACE}/") {
                bat "vagrant halt"
            }
		}
		
		failure {
			echo "Destroying VMs as build has failed."
            dir("${WORKSPACE}/") {
                bat "vagrant destroy -f"
            }

			slackSend channel: 'ci', message: "Build failed! See Jenkins for the log.", tokenCredentialId: 'slack-integration-token'

        }
		
		success {
			slackSend channel: 'ci', message: "Build succeeded!", tokenCredentialId: 'slack-integration-token'
		}
    }
}
