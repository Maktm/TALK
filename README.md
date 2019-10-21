# TALK
Android based application for tracking your mental health

## Setting up the project
Install `git` if you do not already have it installed from [here](https://git-scm.com/).

Once installed, open a terminal and run the following command to clone the repository into the current directory. You may be asked to enter your username/password. If so, just enter your GitHub username and password.

`git clone https://www.github.com/maktm/TALK.git`

This will create a new directory named `TALK`. To import the project into Android Studio, open Android Studio then hit `File` > `Open` then choose the new directory that was created.

## Set up a virtual device using AVD
To set up a new virtual device, open Android Studio then choose the drop-down menu for AVD devices next to the 'app' and choose 'Open AVD Manager'. From the AVD Manager, choose `Create Virtual Device` then choose a device to use (e.g. Pixel 3). After hitting `Next`, switch to the `Other Images` tab and choose `Download` next to the first option.

After agreeing then installing the image, hit `Next` then `Finish`.

## Running the project
Once you've set up a virtual device, you can hit `Run` > `Run` and it will start the chosen device, install the app, then open it.

## Authors
* Saeed Suleiman
* Charlotte Phillips
* Aishwarya Anand
* Ukoh Ndukwo
* Michael Kiros

## TODO
All of these TODOs are specific to this branch (i.e. UI improvement, [issue #5](https://github.com/Maktm/TALK/issues/5)):

* Create an activity for when you open the app for the first time
* Add the transitions for the main menu