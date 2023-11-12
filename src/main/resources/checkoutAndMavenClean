#!/bin/bash

# Define the development branch name
development_branch="development"

# Get the current branch
current_branch=$(git rev-parse --abbrev-ref HEAD)

# Update all branches
git fetch --all

# Iterate through all branches (excluding HEAD)
for branch in $(git branch -r | grep -vE "HEAD|${current_branch}"); do
  # Trim the remote part and branch name
  branch_name=$(echo $branch | sed 's/origin\///')

  # Checkout the branch
  git checkout -B $branch_name origin/$branch_name

  # Execute Maven clean install
  mvn clean install -U

  # Optionally, you can switch back to the original branch
  # git checkout $current_branch
done

# Switch back to the original branch
git checkout $current_branch

# Display a message
echo "All branches updated and Maven clean install executed for each branch."