# Using Git for Your Final Game Project

For the Final game project, you will be doing group development and managing a **shared GitHub repository** using Eclipse..

This repository is a **starter template**. 
It is intentionally minimal and is meant to help you start cleanly and design your own solution.

---
## Starter Code Structure (Read This First)


This project already contains a **minimal runnable structure**:

- **app/**
- `MainApp.java` – entry point only
- **model/**
- `GameModel.java` – game state + rules
- `Player.java` *(suggested)*
- `Enemy.java` *(suggested)*
- `Wall.java` *(suggested)*
- `Level.java` – map / layout
- **ui/**
- `GameComponent.java` – rendering
- `GameWindow.java` – window setup


# Step #1: Create an Account

1. Create a GitHub Account

# Step #2: Setting Up PATs for Cloning the Repo

GitHub no longer allows password authentication from Eclipse.


You **must** create a **Personal Access Token (PAT)**.

[Setup Personal Access Token (PAT) for GitHub](https://docs.google.com/document/d/1HD18gxAwSevFrFW-OOXy_mlbXAxxkphD2Tbz0eKo5Rk/edit?usp=sharing)
Important notes:
- Use a **Classic PAT**
- Select scope: `repo`
- Set an expiration date
- **Copy the token immediately** (you cannot see it again)
You will NOT be able to clone the repo and work on eclipse without doing this.


# Step 3: Cloning the Repo and Renaming the Project (Captain Only)

** IMPORTANT: One Person (Captain) Should Clone and Rename BEFORE Anyone else Clones**


1. In your browser, at the top of this page, you should find a green 
button you can use to copy the URI of this project to your clipboard.

![Cloning out repo screenshot](https://github.com/RHIT-CSSE/csse220/blob/master/Docs/misc/checkout_repo.png)

2. Open your Eclipse and go to File > Import > Git > Projects from Git
3. Select "Clone URI"
4. It should automatically get filled out for you, but if not, in the 
   URI field paste the URL you took from the website.  Host and
   repository path should get filled out for you.
5. In Authentication, enter your GITHUB username and INSTEAD of your password you will want to paste in your PAT
from Step #2 (you might find it convenient to have it save these for you, checking the box giving that option) and hit Next
6. In branch selection make sure master is checked and click next
7. In local destination, you can configure anywhere you like *except*
   the directories that your existing CSSE220 repos are being checked
   out to
8. Select "Import existing eclipse projects" and select next.
9. You should see "GameGit-00" on the list, make sure it's checked
   and select next
10. You should see a folder for GameGit-00 in your project browser

11.  In eclipse, rename your project to have your team name instead of ArcadeGameGit-00.
    Right Click on GameGit-00 then Refactor->Rename:

![Renaming repo step 1 screenshot](https://github.com/RHIT-CSSE/csse220/blob/master/Docs/misc/RenameProjectRefactoringStep1.png)

![Renaming repo step 2 screenshot](https://github.com/RHIT-CSSE/csse220/blob/master/Docs/misc/RenameProjectRefactoringStep2.png)

12.  Right click on the project folder and select Team > Commit
13.  Verify that .project appears in your list of "Staged Changes"
14.  Add some text in the commit message "renaming project"
15.  Select Commit and push


## Step #4: Cloning the Repo into Eclipse


All team members:


1. Open Eclipse
2. Go to `File → Import → Git → Projects from Git`
3. Select **Clone URI**
4. Paste the GitHub repo URL
5. Authentication:
- **Username:** your GitHub username
- **Password:** your PAT (from Step #2)
6. Select branch: **main**
7. Choose **Import existing Eclipse project**
8. Finish


If prompted, you may allow Eclipse to store credentials.


## Step #5: First Test Commit (Everyone)


Before real work begins, test Git.


1. Make a **small change** (e.g., edit a comment)
2. Right-click project → `Team → Commit`
3. Verify the file appears under **Staged Changes**
4. Commit and Push


This confirms your setup works.

---


## Step #6: Pull Before You Code


Before starting work **every time**:


1. Right-click project → `Team → Pull`


This prevents most merge conflicts.


---

# Step #7: Branching Rules (Recommended)

This is to avoid a Merge Conflict

Rules:
- ❌ No direct pushes to `main`
- ✅ Each feature gets its own branch


Example branch names:
- `feature-player-movement`
- `feature-enemies`
- `ui-scoreboard`


Workflow:
1. Create a branch
2. Code
3. Commit and push the branch
4. Open a Pull Request on GitHub
5. Merge into `main`


# Step 8: Cause a Merge Conflict

When *everyone* in your team

1. Edit the same line of code in a different way.  Say add your name
   to the println.
2. Attempt to commit and push.
3. The first person who does it should succeed.  The rest should get
   a "rejected non-fast-forward" error.

For one of those those who failed: 

1. Right click on the project folder and select team > Pull
2. You should see a message about conflict and things will look sort
   of scary
3. Look at the edited file.  You should see that both versions of the
   code are there plus some <<<<< ===== >>>> lines
4. Figure out what the *combination* of the changes ought to be
   (probably all your names in the println) and edit the file to be
   correct, deleting all unnecessary stuff
5. Test your code and make sure that everything works as expected
6. Right click on the project folder and select Team > Commit
7. Manually move all your files into "Staged changes" with the +
8. Commit and push
9. Now have the original committer pull and they should have the
    merged version too
10. If they are any other members of year team, have them do step 4
    onward
    


# Good Advice for Minimizing Merge Conflicts

* [Pair program whenever possible](https://rose-hulman.hosted.panopto.com/Panopto/Pages/Viewer.aspx?id=ddab27fc-a8a4-4cd0-a8f8-abaf013a3f22)
* Always do a Team Pull before you begin programming
* Always to a Team Commit, then Pull, then Push when you finish
* In Eclipse: Right Click -> Replace With -> HEAD Revision is a nice way to remove 
  your local changes before doing a Team->Pull if you don't care about
  the local version of your code and just want to get your partner's version
* If you do have to resolve a merge conflict, remember you must
  accommodate *both* changes 

# Git bash (Command Line)

* A more advanced and full feature program can be used to use [Git for Windows](https://gitforwindows.org/)
* MacOS and Linux have terminal/consoles that can interact with git natively
* There might be times when using these tools will be easier than Eclipse alone
* You are welcome to install it, but in most cases it should not be required
* More about git: [git-handbook](https://guides.github.com/introduction/git-handbook/)

# GitHub Desktop (GUI Application)
* A more intuitive interface than Eclipse and does not require command line familiarity
* Download here [https://desktop.github.com/](https://desktop.github.com/)
