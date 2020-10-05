# Contributing

[Lire en Français ? 🇫🇷](https://github.com/PNS-Conception/cookiefactory-20-21-team-l/blob/master/CONTRIBUTING_FR.md)

## Plan
* [Introduction](#Introduction)
* [Make a commit](#Make-a-commit)
* [Branch Management](#Branch-Management)
  * [Branch organization](#Branch-organization)
  * [Create a new branch to develop a new feature](#Add-a-branch)
  * [Create a sweater Request](#Create-a-pull-request)

## Introduction
This document indicates how to participate in this project, how to submit contributions and how to make changes.

## Make a commit
Each commit must be associated with a github issue. The submitted code must be in English, commented and tested.


## Branch Management
  ### Branch organization
  Our branches are organized as follows: 
  * a `master` branch for renderings
  * a `pre-prod` branch, stable and tested for the next rendering
  * a `newfeaturename` branch to develop new features
  
  ### Add a branch
  Use the following command to create a new branch : 
  
  ```
  git checkout -b mynewbranchname
  ```
  
  To switch to another branch : 
  
  ```
  git checkout otherbranch
  ```
 
  
  ### Create a pull request
  Go to Git and click on `Compare & pull request`.
  
  Add a title and description to the pull request.
  
  The submitted code must be clear, commented and tested for the pull request to be accepted.
  
  Then use the `merge` command to merge the branches.
