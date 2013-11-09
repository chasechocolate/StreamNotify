#[Stream Notify][BukkitDev]


Automatically checks if configured streams are live an if so broadcasts a message to you players. Download from [BukkitDev] for your server today!


##Commands

####`/streamnotify`
Lists all active streams for the server configured list.

####`/streamnotify <player>`
Checks if `<player>` is currently streaming. Will only check for streams that the server has allowed.

##Configuration
###Channels
A list of all the channels you want to check.

```
channels:
- chasechocolate
```

###Broadcast
####Delay
How long (in seconds) between each broadcast of online streamers. This is also the delay for the stream status refresh.

####Message Start
What the broadcast starts with, before listing each online stream. Supports [Color Code]s.

####URL Color
The [Color Code]\(s) that will prefix each stream entry in the broadcast.

###Player Check
Each one of these is able to use the keyword `%channel%` which will get replaced by the channel the user is trying to check. They also all support [Color Code]s

####Online
The message displayed to the user when the specified streamer is online. Can also use the keyword `%url%` to get the stream url for player to click on.

####Offline
The message displayed to the user when the specified streamer is offline.

####All Offline
The message displayed to the user if all of the streamers are offline.

####Not Listed
The message displayed to the user if the specified player is not part of your channel list.


[BukkitDev]: http://dev.bukkit.org/bukkit-plugins/streamnotify/
[Color Code]: http://minecraft.gamepedia.com/Formatting_codes