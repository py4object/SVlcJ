svlcj-player
===========

Svlcj is  built on  the top off [vlcj-player] (https://github.com/caprica/vlcj-player)

The main goal of the project is to provide a platform independent media player that 
that can be synchronized using Server/Client model  

Screenshot
----------

![svlcj-player](https://github.com/py4object/SVlcJ/blob/master/Screenshot.png " svlcj-player")

Features
--------
 - synchronizable 
 - audio player
 - video player
 - full-screen
 - audio equalizer
 - video adjustments
 - title selection
 - chapter navigation
 - audio track selection
 - video track selection
 - subtitle track selection
 - load external subtitle file
 - change audio device
 - change playback speed
 - capture and display native logs
 - capture and display video surface debug messages (e.g. to test mouse and keyboard events still work)
 - volume controls
 - mute
 - zoom/scale
 - aspect ratio
 - crop
 - always on top
 - video snapshots
 - redirect native output streams (on Linux)

...and a whole bunch of other nifty stuff.
 

Status
------

This project is currently a work-in-progress.

If you execute "mvn install" or "mvn package", you will get a distribution
package that you can unpack. This will give you the vlcj-player application jar
and all of the dependencies - you can simply execute `java -jar svlcj-player-1.0.0-SNAPSHOT.jar`
and the application should start.

On the other hand, just run it from an Eclipse project.

License
-------

The vlcj-player project is provided under the GPL, version 3 or later.
