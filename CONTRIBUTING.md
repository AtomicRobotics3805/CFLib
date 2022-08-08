# Contributing to CFLib

First off, thanks for taking the time to look into contributing!

If you're new to open source software, we'll take a moment to explain it.

CFLib is an open source project. That means anyone can add features, fix bugs, edit code, or do [almost] whatever they want.

That's awesome, right!?

Even better is that they can actually contribute those changes *to the source* and benefit everyone who uses it!

Now that everyone's on the same page about what contributing means, let's get into the content. 

The following is a set of guidelines for contributing to CFLib. Most of these are guidelines, not rules. Use your best judgement.

Also note that this file is 

## 1. Gracious Professionalism!!!

Any contributor found to be not using Gracious Professionalism when contributing, or discriminating, harassing, or not being welcoming to any individual or group, will not be allowed to contribute, and will have their credits and name be removed from FTC Layer.

## 2. Keep it clean!

FTC Layer uses [Oracle naming conventions](https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html), If you aren't using [Oracle naming conventions](https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html), we ask you rewrite the code to follow those conventions.

Do use comments, they aren't necessary for all functions or variables, but use them as a way to explain complicated pieces of code.

## 3. Metric, not customary.
FTC Layer uses metric, not customary, make sure functions use metric. Although conversion functions are allowed, please use metric as the default.
This applies to degrees too. Degrees are a lot more user-friendly, but radian function equivalents are allowed.
## 4. Branch names
When naming the branch you are pushing to, use the following.
`implement_WhatIImplemented` - Use when Implementing or adding a feture
`fix_WhatIFixed` - Use when fixing a bug
`other_SomethingElse` - When doing something not described above

## 5. Pull requests
Make a pull request to `dev`, not `master`.

**_I REPEAT, NOT `master`._**

Make sure you are comparing `implement_YourBranch` and using `dev` as the base.
All pull requests to `master` will be closed.

After `dev` is proven to be stable, we will pull `dev` into `master`. 

## 6. Use Javadoc
Javadoc is a standard way for us to write documentation for our code. Most IDE's can do it automatically. While we would like for you to write it, its no biggie and we can just put it in to our IDEs and generate it.