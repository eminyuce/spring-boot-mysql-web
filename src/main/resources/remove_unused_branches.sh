#!/bin/bash

# Define the development branch name
development_branch="development"

# Get the current branch
current_branch=$(git rev-parse --abbrev-ref HEAD)

# Update all branches
git fetch --all

# Iterate through all local branches (excluding HEAD)
for branch in $(git branch --format='%(refname:short)' | grep -vE "HEAD|${current_branch}"); do
  # Check if the branch has a corresponding remote branch
  remote_branch=$(git ls-remote --heads origin $branch)

  if [ -z "$remote_branch" ]; then
    # If no remote branch exists, remove the local branch
    git branch -d $branch
    echo "Local branch '$branch' removed as there is no corresponding remote branch."
  fi
done