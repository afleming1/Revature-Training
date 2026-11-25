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
    - [2.3 Reading History](#23-reading-history)
    - [2.4 Ignoring Files](#24-ignoring-files)
  - [3. Cloning](#3-cloning)
    - [3.1 What Cloning Sets Up](#31-what-cloning-sets-up)
  - [3.2 Clone vs Init](#32-clone-vs-init)
  - [4. Pushing \& Pulling](#4-pushing--pulling)
    - [4.1 Pushing](#41-pushing)
    - [4.2 Pulling](#42-pulling)
    - [4.3 A Rejected Push](#43-a-rejected-push)
    - [4.4 The Habit](#44-the-habit)
  - [5. Branching](#5-branching)
    - [5.1 Working with Branches](#51-working-with-branches)
    - [5.2 Pushing a New Branch](#52-pushing-a-new-branch)
  - [6. Merging](#6-merging)
    - [6.1 Fast-Forward Merges](#61-fast-forward-merges)
    - [6.2 Merge Commits](#62-merge-commits)
    - [6.3 Seeing the History](#63-seeing-the-history)
    - [6.4 Keeping a Branch Up to Date](#64-keeping-a-branch-up-to-date)
  - [7. Merge Conflicts](#7-merge-conflicts)
    - [7.1 Recognizing a Conflict](#71-recognizing-a-conflict)
    - [7.2 Resolving a Conflict](#72-resolving-a-conflict)
    - [7.3 Backing out](#73-backing-out)
    - [7.4 Fewer Conflicts](#74-fewer-conflicts)
  - [8. Pull Requests](#8-pull-requests)
    - [8.1 The Flow](#81-the-flow)
    - [8.2 Conflicts in a Pull Request](#82-conflicts-in-a-pull-request)
    - [8.3 Why Teams Use Pull Requests](#83-why-teams-use-pull-requests)

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

### 2.3 Reading History
```bash
git log             # Full Details: ID, Author, Date, Message
git log --oneline   # One line per commit, short ID and message
```

### 2.4 Ignoring Files
Some files shouldn't be in a repository: build history, editor settings, passwords. A file named as **`.gitignore`** in the repository lists patterns for files Git should ignore.

```text
target /

*.log

.idea/
vscode/
```

An example `.gitignore`. Each line is a pattern: a folder name with a training slash, or a wildcard like `*.log`.

Ignored files never appear in `git status` and can't be added by accident. The `.gitignore` itself *is* commited, so the whole team shares it. It only affects files Git isn't already tracking - so it's best created before the first commit.

[&uarr; Return to Table of Contents](#table-of-contents)

## 3. Cloning
**Cloning** makes a complete copy of a remote repository - every file and the entire history. It's how we join a project that already exists.

```bash
git clone https://github.com/afleming1/Revature-Training.git
cd Revature-Training
```

Cloning a repository from GitHub. Git creates a `Revature-Training` folder named after the repository.

A different folder name can be given as a second argument.

```bash
git clone https://github.com/afleming1/Revature-Training.git Revature-Training-2
cd Revature-Training
```

### 3.1 What Cloning Sets Up
After a clone, the repository is ready to work in immediately.

- The **fully history** is present - `git log` shows every commit ever made
- The default branch (usually `main`) is **checked out**, so the files are ready to edit
- Remote named **`origin`** is configured, pointing back at the URL we cloned from

```bash
git remote -v
```

Listing the remotes. After a clone, `origin` appears with the repository's URL.

`origin` is just a conventional name - it's what `git push` and `git pull` use by default.

## 3.2 Clone vs Init
| Situation | Command |
| :--- | :--- |
| Creating a brand new repo | `git init` |
| Joining a repo that already exists | `git clone` |

A clone is never folled by `git init` - it's already a repository.

[&uarr; Return to Table of Contents](#table-of-contents)

## 4. Pushing & Pulling
Commits are made locally, on our own machine. Nobody else sees them until we **push** them to the remote, and we don't see anyone else's until we **pull** them down.

### 4.1 Pushing
`git push` sends our new commits to the remote.

```bash
git push
```

Sending local commits on the current branch to `origin`. In a cloned repository, this works with no extra arguments.

A repository created with `git init` has no remote yet. To put it on GitHub, we create on **empty** repository on GitHub (no README or other files), then connect and push once.

```bash
git remote add origin https://github.com/afleming1/Revature-Training.git
git push -u origin main
```

Connecting a local repository to GitHub and pushing it for the first time. `-u` links our `main` to the remote's `main`, so later a plain `git push` or `git pull` knows where to go.

### 4.2 Pulling
`git pull` brings down commits that others have pushed, and merges them into our current branch.


```bash
git pull
```

Downloading and merging the remote's new commits into the current branch.

When we haven't made any local commits of our own, the pull simple moves our branch forward to match the remote - Git reports this as a **fast-forward**.

### 4.3 A Rejected Push
If someone else pushed first, our push is refused.

```text
 ! [rejected]        main -> main (fetch first)
hint: Updates were rejected because the remote contains work that you do not
hint: have locally. This is usually caused by another repository pushing to
hint: the same ref.
```

Git refusing a push: the remote has commits we don't have yet.

Git won't let a push overwrite other people's work. The fix is to **pull first** - combining their commits with ours - and then push again.

### 4.4 The Habit
- **Pull before starting work**, so we build on the latest version
- **Commit often** - small commits are easier to understand and to undo
- **Push when a piece of work is done**, so teammates can see it

[&uarr; Return to Table of Contents](#table-of-contents)

## 5. Branching
A **branch** is an indepentent line of work. Commits made on one branch don't affect any other branch until the branches are merged. Branches are cheap to create - Git doesn't copy an files - so creating one per task is normal.

Every repository starts with one branch, **`main`**. On a team, `main` is the shared line of work that always work.

### 5.1 Working with Branches
```bash
git branch              # List branches
git switch -c Week-3    # Creates a new branch and switch to it
git switch main         # Switch to an existing branch
```

Listing, creating, and switching branches. Switching changes the files in the working directory to match the branch.

**Commit before switching.** Uncommitted changes either travel along to the other branch or, if they'd be overwritten, Git refuses to switch.

### 5.2 Pushing a New Branch
A new branch exists only locally until it's pushed. The first push sets its **upstream** - the remote branch it pairs with.

```bash
git push -u origin Week-3
```

Publishing a new branch to GitHub. After this, plain `git push` and `git pull` work on the branch.

[&uarr; Return to Table of Contents](#table-of-contents)

## 6. Merging
**Merging** brings the commits from one branch into another. `git merge` always merges *into the current branch*, so we switch to the branch that should receive the work first.

```bash
git switch main
git merge Week-3
```

Merging the `Week-3` branch into `main`.

On a team, merges into `main` usually happen through a pull request on GitHub rather than on the command line - bit it's the same operation either way.

### 6.1 Fast-Forward Merges
If `main` hasn't changed since the feature branch was created, there's nothing to combine. Git just moves `main` forward to the branch's latest commit. This is a **fast-forward**, and no new commit is created.

### 6.2 Merge Commits
If `main` *has* moved on - someone's else merged work in the meantime - both branches have new commits. Git combines them by creating a **merge commit**, a commit with two parents that joints the two lines of history.

Git writes the merge commit automatically when the branches changed have different lines. When both changed the *same* lines, the merge stops with a [merge conflict](#7-merge-conflicts).

### 6.3 Seeing the History
```bash
git log --oneline --graph
```

The history drawn as a graph, showing where branches split off and where they were merged.

### 6.4 Keeping a Branch Up to Date
A long-running feature branch can fall behind `main`. Merging `main` *into* the feature branch brings it up to date and surfaces any conflicts early, on the branch, instead of during the final merge.

```bash
git switch Week-3
git fetch origin
git merge origin/main
```

Updating a feature branch with the latest `main` from GitHub. `git fetch` downloads the remote's commits without merging; `origin/main` is GitHub's copy of `main`.

[&uarr; Return to Table of Contents](#table-of-contents)

## 7. Merge Conflicts
A **merge conflict** happens when two branches changed the *same lines* of the same file, and Git can't know which version is right. Instead of guessing, Git stops the merge and asks us to decide. Conflicts are a normal part of teamwork, not an error.

### 7.1 Recognizing a Conflict
```text
Auto-merging README.md
CONFLICT (content): Merge conflict in README.md
Automatic merge failed; fix conflicts and then commit the result.
```

Git's output when a merge hits a conflict.

`git status` lists the conflicted files as **both modified**. Files without conflicts were merged automatically and already staged.

### 7.2 Resolving a Conflict
1. Edit the file to what it *should* say - keep ours, keep theirs, combine them, or write something new
2. **Delete all three marker lines**
3. Stage the resolved file and commit to finish the merge

```bash
git add README.md
git commmit --no-edit
```

Marking the conflict as resolved and completing the merge. `--no-edit` accepts Git's default merge message.

VS Code highlights conflicts and offers **Accept Current Change**, **Accept Incoming Change**, and **Accept Both Changes** buttons above each one, which both edit the file and remove the marker's for us. Either way, the result still needs `git add` and `git commmit`.

### 7.3 Backing out
If a merge goes wrong partway through, it can be cancelled, returning everything to how it was before the merge started.

```bash
git merge --abort
```

Cancelling an in-progress merge.

### 7.4 Fewer Conflicts
- **Pull often** and keep branches short-lived, so they don't drift far from `main`
- **Keep changes focused** - a branch that touches only what its task needs overlaps with fewer others
- **Talk to the team** when two people need to change the same file

[&uarr; Return to Table of Contents](#table-of-contents)

## 8. Pull Requests
A **pull request** is a request, made on GitHub, to merge one branch into another - almost always a feature branch into `main`. It turns a merge into a conversation: teammates can see exactly what changed, comment on it, and approve it before it lands.

### 8.1 The Flow
1. **Push a branch** with the work on it
2. **Open a pull request** on GitHub. The **base** is the branch to merge into (`main`); the **compare** branch is ours. Give it a clear title and a short description of what changed and why
3. **Review**. Teammates read the changes and either approve them or request changes, leaving comments on specific lines
4. **Update** if needed - pushing more commits on the same branch adds them to the open pull request automatically
5. **Merge** on GitHub once it's approved
6. **Clean up**: delete the branch (GitHub offers a button), then everyone pulls the updated `main`

```bash
git switch amain
git pull
git branch -d Week-3
```

Affter a pull request is merged: update the local `main` and delete the finished local branch.

### 8.2 Conflicts in a Pull Request
If `main` changed the same lines while the pull request was open, GitHub reports **"This branch has conflicts that must be resolved"** and disables the merging. The usual fix is to merge `main` into the feature branch locally, [resolve the conflict](#72-resolving-a-conflict) and push.

```bash
git fetch origin
git merge origin/main
# Resolve the conflict
git add README.md
git commit --no-edit
git push
```

Resolving a pull request's conflict on the feature branch. The pull request updates iteself and becomes mereable.

### 8.3 Why Teams Use Pull Requests
- **Review** - A second person reads every change before it reaches `main`
- **Stability** - `main` only receives work that's been seen and agreed on
- **History** - The pull request keeps the discussion of *why* a change was made, linked to the change itself

[&uarr; Return to Table of Contents](#table-of-contents)