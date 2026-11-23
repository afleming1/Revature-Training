# All About Git

## Table of Contents
- [All About Git](#all-about-git)
  - [Table of Contents](#table-of-contents)
  - [1. What is Git?](#1-what-is-git)
    - [1.1 Repositories and Commits](#11-repositories-and-commits)
    - [1.2 Three Areas of Git](#12-three-areas-of-git)
    - [1.3 Remotes](#13-remotes)
    - [1.4 What GitHub Adds](#14-what-github-adds)
  - [2. Creating a Repository](#2-creating-a-repository)
    - [2.1 Starting a Repository](#21-starting-a-repository)
    - [2.2 The Everyday Loop](#22-the-everyday-loop)

## 1. What is Git?
Git is a **Version Control System**. It records the history of a project's files - every change, who made it, when, and why.

### 1.1 Repositories and Commits
A **Repository** (or **repo**) is a project folder that Git is tracking. Multiple commits make a "history". The history lives in a hidden `.git` folder insite it.

The history is made of **commits**. A commit is a **snapshot** of every tracked file at one moment.

### 1.2 Three Areas of Git
Git contains three main places in while working.
- **Working Directory** - Files we can see and edit
- **Staging Area** - Changes we've chosen to include in the next commit
- **Repository** - Committed history

### 1.3 Remotes
A **Remote** is a copy of a repository hosted somewhere else - on GitHub, for our purposes. A team doesn't send commits directly to each other's computers; everyone sends commits to the shared remote and receives other people's commits from it.

### 1.4 What GitHub Adds
**GitHub** is a website that hosts Git repositories. It runs in the cloud.

- Web view of the code, its history, and every change
- Access Control - Who can see the repository, and who can push to it
- Backup - The project survives on a host laptop
- Pull Requests - Way to propose, review, and discuss changes before they're covered in the Branching unit

[&uarr; Return to Table of Contents](#table-of-contents)

## 2. Creating a Repository
Every commit records its author, so Git needs a name and email before the first commit.

### 2.1 Starting a Repository
`git init` turns the current folder into a repository by creating the hidden `.git` folder.

```bash
mkdir folder_name
cd folder_name
git init -b main
```

The above creates a new repository. `-b main` names the first branch `main`.

Deleting the `.git` folder deletes the entire history, so it's left alone.

### 2.2 The Everyday Loop
Almost all day-to-day Git is the same three commands, with `git status` in between to see where things stand.

```bash
git status                  # What's changed, what's staged, which branch we're on
git add README.md           # Stage one file
git add .                   # Or: Stage every change in this folder
git commit -m "Add README"  # Commits with details inside ""
```

Check, stage, commit. `git status` is safe to run at any time and is the best habit to build.

A new file is **untracked** until it's added. After `git add`, changes show as **staged** (ready to commit). After the commit, `git status` reports a clean working tree.