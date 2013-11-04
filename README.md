#[Stream Notify][BukkitDev]


Automatically checks if configured streams are live an if so broadcasts a message to you players. Download from [BukkitDev] for your server today!


##Commands

###`/streamnotify`
Lists all active streams for the server configured list.

###`/streamnotify <player>`
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
How long (in seconds) between each broadcast of online streamers.

####Message Start
What the broadcast starts with, before listing each online stream. Supports any Minecraft [Color Code].

####URL Color
The [Color Code]\(s) that will prefix each stream entry in the broadcast.

###Player Check
Each one of these is able to use the keyword `%channel%` which will get replaced by the channel the user is trying to check.


[BukkitDev]: http://dev.bukkit.org/bukkit-plugins/streamnotify/
[Color Code]: http://minecraft.gamepedia.com/Formatting_codes